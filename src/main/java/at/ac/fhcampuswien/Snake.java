package at.ac.fhcampuswien;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.*;
import javafx.scene.shape.Rectangle;
import org.w3c.dom.css.Rect;

public class Snake extends Node {
    // Variables
    private static int scale = 20;
    private static int startSize = 2;
    public final int gridSize = App.GameSize / scale;
    public static int nowSnakeSize = startSize;
    private Rectangle[] snakeLengthArr = new Rectangle[nowSnakeSize];

    //GUI
    private static Color headColor = Color.rgb(3, 160, 98);
    private static Color bodyColor = Color.rgb(209, 250, 113);

    private char direction = 'R';

    //Conctuctor for Snake Object
    public Snake() {
        for (int i = 0; i < nowSnakeSize; i++) {
            //Rectangle erzeugen und befüllen

            Rectangle bodyPart = new Rectangle();

            //Farbe der Snake festlegen
            if (i == 0) {
                bodyPart.setFill(headColor);
            } else {
                bodyPart.setFill(bodyColor);
            }

            //Höhe und Breite der Rectangels
            bodyPart.setHeight(gridSize - 1);
            bodyPart.setWidth(gridSize - 1);

            bodyPart.setX(gridSize * (nowSnakeSize - 1) - i * gridSize);

            snakeLengthArr[i] = bodyPart;
        }
    }

    public Rectangle[] getSnakeLengthArr() {
        return snakeLengthArr;
    }

    public Rectangle getHead(){
        return this.getSnakeLengthArr()[0];
    }

    public char getDirection() {
        return direction;
    }

    public void setDirection(char direction) {
        this.direction = direction;
    }


    //add a new BodyPart to the snake Array

    /*
    NOCH PROBLEMATISCH
     */
    public void addBodyPart(Pane pane) {
        Rectangle[] snakeNextSizeArray = new Rectangle[snakeLengthArr.length + 1];

        System.arraycopy(snakeLengthArr, 0, snakeNextSizeArray, 0, snakeLengthArr.length);

        /*Long Version from Arraycopy
        for (int i = 0; i < snakeLengthArr.length; i++) {
            snakeNextSizeArray[i] = snakeLengthArr[i];
        }

         */


        Rectangle bodyPart = new Rectangle();
        bodyPart.setFill(Color.rgb(255,255,255));
        bodyPart.setWidth(gridSize - 1);
        bodyPart.setHeight(gridSize - 1);

        //so this rectangle is out of the GameBoard, when it's created
        bodyPart.setX(- gridSize);
        bodyPart.setY(- gridSize);

        snakeNextSizeArray[snakeLengthArr.length] = bodyPart;

        //overwrite whole variable
        snakeLengthArr = snakeNextSizeArray;

        pane.getChildren().addAll(bodyPart);
    }

    public void moveSnake(){
        //Body
        for (int i = this.getSnakeLengthArr().length - 1; i > 0 ; i--) {
            this.getSnakeLengthArr()[i].setX(getSnakeLengthArr()[i-1].getX());
            this.getSnakeLengthArr()[i].setY(getSnakeLengthArr()[i-1].getY());
        }

        //Head
        switch(direction){
            case 'R':
                this.getHead().setX(this.getHead().getX() + gridSize);
                break;
            case 'L':
                this.getHead().setX(this.getHead().getX() - gridSize);
                break;
            case 'U':
                this.getHead().setY(this.getHead().getY() - gridSize);
                break;
            case 'D':
                this.getHead().setY(this.getHead().getY() + gridSize);
                break;
        }



    }

}
