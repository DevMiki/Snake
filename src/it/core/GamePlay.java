package it.core;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.util.stream.IntStream;

public class GamePlay extends JPanel implements ActionListener, KeyListener {

    private int[] snakeXPieceLength = new int[750];
    private int[] snakeYPieceLength = new int[750];
    private int lengthOfSnake = 3;

    private boolean left = false;
    private boolean right = false;
    private boolean up = false;
    private boolean down = false;

    private ImageIcon rightMouth;
    private ImageIcon leftMouth;
    private ImageIcon upMouth;
    private ImageIcon downMouth;

    private Timer timer;
    private int delay = 1000;
    private ImageIcon snakeImage;
    int moves = 0;

    private static final int[] enemyXPosition = IntStream.iterate(25, i -> i + 25).limit(34).toArray();
    private static final int[] enemyYPosition = IntStream.iterate(75, i -> i + 25).limit(23).toArray();

    private Random random = new Random();
    private int xPos = random.nextInt(34);
    private int yPos = random.nextInt(23);

    public GamePlay(){
        this.addKeyListener(this);
        this.setFocusable(true);
        this.setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay,this);
        timer.start();
    }


    @Override
    public void paint(Graphics graphics){

        //set default position for the snake and checks if the game has already started
        if(moves == 0){
            snakeXPieceLength[2] = 100;
            snakeXPieceLength[1] = 100;
            snakeXPieceLength[0] = 100;

            snakeYPieceLength[2] = 50;
            snakeYPieceLength[1] = 75;
            snakeYPieceLength[0] = 100;
        }
        //draw title image border
        graphics.setColor(Color.white);
        graphics.drawRect(24,10,851,55);

        //draw the title image
        ImageIcon titleImage = new ImageIcon("snaketitle.jpg");
        titleImage.paintIcon(this,graphics,25,11);

        //draw border for gameplay
        graphics.setColor(Color.WHITE);
        graphics.drawRect(24,74,851,577);

        //draw background for the gameplay
        graphics.setColor(Color.black);
        graphics.fillRect(25,75,850,575);

        rightMouth = new ImageIcon("rightmouth.png");
        rightMouth.paintIcon(this,graphics, snakeXPieceLength[0], snakeYPieceLength[0]);

        for(int pieceOfSnakeLength = 0; pieceOfSnakeLength<lengthOfSnake; pieceOfSnakeLength++) {
            if(pieceOfSnakeLength==0 && right){
                rightMouth = new ImageIcon("rightmouth.png");
                rightMouth.paintIcon(this,graphics, snakeXPieceLength[pieceOfSnakeLength], snakeYPieceLength[pieceOfSnakeLength]);
            }
            if(pieceOfSnakeLength==0 && left){
                leftMouth = new ImageIcon("leftmouth.png");
                leftMouth.paintIcon(this,graphics, snakeXPieceLength[pieceOfSnakeLength], snakeYPieceLength[pieceOfSnakeLength]);
            }
            if(pieceOfSnakeLength==0 && up){
                upMouth = new ImageIcon("upmouth.png");
                upMouth.paintIcon(this,graphics, snakeXPieceLength[pieceOfSnakeLength], snakeYPieceLength[pieceOfSnakeLength]);
            }
            if(pieceOfSnakeLength==0 && down){
                downMouth = new ImageIcon("downmouth.png");
                downMouth.paintIcon(this,graphics, snakeXPieceLength[pieceOfSnakeLength], snakeYPieceLength[pieceOfSnakeLength]);
            }
            if(pieceOfSnakeLength!=0) {
                snakeImage = new ImageIcon("snakeimage.png");
                snakeImage.paintIcon(this,graphics, snakeXPieceLength[pieceOfSnakeLength], snakeYPieceLength[pieceOfSnakeLength]);
            }
        }

        ImageIcon enemyImage = new ImageIcon("enemy.png");
        if((enemyXPosition[xPos] == snakeXPieceLength[0] && enemyYPosition[yPos] == snakeYPieceLength[0])){
           lengthOfSnake++;
           xPos = random.nextInt(34);
           yPos = random.nextInt(23);
        }
        enemyImage.paintIcon(this,graphics,enemyXPosition[xPos],enemyYPosition[yPos]);

        graphics.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(right){
            for (int r = lengthOfSnake - 1; r >= 0; r--) {
                snakeYPieceLength[r + 1] = snakeYPieceLength[r];
            }
            for (int r = lengthOfSnake; r >= 0; r--) {
                if (r == 0) {
                    snakeXPieceLength[r] = snakeXPieceLength[r] + 25;
                } else {
                    snakeXPieceLength[r] = snakeXPieceLength[r - 1];
                }
                if (snakeXPieceLength[r] > 850) {
                    snakeXPieceLength[r] = 25;
                }
                snakeXPieceLength[lengthOfSnake - 1] = snakeXPieceLength[0];

            }
            repaint();
        }
        if(left){
            for(int r = lengthOfSnake; r>=0;r--){
                snakeYPieceLength[r+1] = snakeYPieceLength[r];
            }
            for(int r = lengthOfSnake; r>=0;r--){
                if(r==0){
                    snakeXPieceLength[r] = snakeXPieceLength[r] - 25;
                } else {
                    snakeXPieceLength[r] = snakeXPieceLength[r-1];
                }
                if(snakeXPieceLength[r] < 25){
                    snakeXPieceLength[r] = 850;
                }
            }
            repaint();
        }
        if(up){
            for(int r = lengthOfSnake; r>=0;r--){
                snakeXPieceLength[r+1] = snakeXPieceLength[r];
            }
            for(int r = lengthOfSnake; r>=0;r--){
                if(r==0){
                    snakeYPieceLength[r] = snakeYPieceLength[r] - 25;
                } else {
                    snakeYPieceLength[r] = snakeYPieceLength[r-1];
                }
                if(snakeYPieceLength[r] < 75){
                    snakeYPieceLength[r] = 625;
                }
            }
            repaint();
        }
        if(down){
            for(int r = lengthOfSnake; r>=0;r--){
                snakeXPieceLength[r+1] = snakeXPieceLength[r];
            }
            for(int r = lengthOfSnake; r>=0;r--){
                if(r==0){
                    snakeYPieceLength[r] = snakeYPieceLength[r] + 25;
                } else {
                    snakeYPieceLength[r] = snakeYPieceLength[r-1];
                }
                if(snakeYPieceLength[r] > 625){
                    snakeYPieceLength[r] = 75;
                }
            }
            repaint();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            moves++;
            right = true;
            if (left) {
                right = false;
                left = true;
            }
            up = false;
            down = false;
        }

        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            moves++;
            left = true;
            if (right) {
                left = false;
                right = true;
            }
            up = false;
            down = false;
        }

        if(e.getKeyCode() == KeyEvent.VK_UP){
            moves++;
            up = true;
            if (down) {
                up = false;
                down = true;
            }
            left = false;
            right = false;
        }


        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            moves++;
            down = true;
            if (up) {
                down = false;
                up = true;
            }
            left = false;
            right = false;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
