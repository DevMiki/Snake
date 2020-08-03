package it.core.snake;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class SnakeSpace {
    private int x;
    private int y;
    private int w;
    private int h;


    public static SnakeSpace of(int x, int y, int w, int h) {
        final SnakeSpace snakeSpace = new SnakeSpace();
        snakeSpace.x = x;
        snakeSpace.y = y;
        snakeSpace.w = w;
        snakeSpace.h = h;
        return snakeSpace;
    }


    public void draw(Graphics graphics) {
        //draw background for the gameplay
        graphics.setColor(Color.black);
        graphics.fillRect(x,y,w,h);

    }

    public SnakePiece computeCoordsInSpace(int snakePieceX, int snakePieceY) {

        if (snakePieceX < x) {
            snakePieceX = w;
        } else if (snakePieceX > w) {
            snakePieceX = x;
        }
        if (snakePieceY < y) {
            try {
                //in this case I need to shift of exactly 25px
                BufferedImage image = ImageIO.read(new File("upmouth.png"));
                snakePieceY = h + y - image.getHeight();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (snakePieceY >= h + y) {
            snakePieceY = y;
        }

        return new SnakePiece(snakePieceX, snakePieceY);
    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }
}
