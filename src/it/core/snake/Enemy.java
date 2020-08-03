package it.core.snake;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.stream.IntStream;

public class Enemy {

    private int xPosition;
    private int yPosition;

    public Enemy(SnakeSpace snakeSpace) {
        Random random = new Random();
        xPosition = snakeSpace.getX()+random.nextInt(snakeSpace.getW()/25)*25;
        yPosition = snakeSpace.getY()+random.nextInt(snakeSpace.getH()/25)*25;
    }

    public void draw(Component component, Graphics graphics){
        new ImageIcon("enemy.png").paintIcon(component,graphics,xPosition,yPosition);
    }

    public int getxPosition() {
        return xPosition;
    }

    public void setxPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }

    public void setyPosition(int yPosition) {
        this.yPosition = yPosition;
    }
}
