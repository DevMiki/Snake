package it.core.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


import static it.core.snake.Snake.Direction.*;

public class SnakeGame extends JPanel implements ActionListener, KeyListener {

    private Snake snake;
    private Enemy enemy;

    public SnakeGame() {
        this.setFocusable(true);
        this.addKeyListener(this);
        this.setFocusTraversalKeysEnabled(false);
        snake = new Snake(UP, SnakeSpace.of(25, 75, 850, 575));
        Timer timer = new Timer(125, this);
        timer.start();
        enemy = new Enemy(snake.getSnakeSpace());
    }


    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);

        //draw the title image
        ImageIcon titleImage = new ImageIcon("snaketitle.jpg");
        titleImage.paintIcon(this, graphics, 25, 11);
        //draw the space where the snake will move
        snake.getSnakeSpace().draw(graphics);
        //draw the actual snake
        snake.draw(this, graphics);

        enemy.draw(this,graphics);

    }




    @Override
    public void actionPerformed(ActionEvent e) {

        if(snake.isAlive()){
            snake.move();
        }
        if (snake.hasEaten(enemy)){
            snake.grow();
            enemy = new Enemy(snake.getSnakeSpace());
        }
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        snake.setDirection(fromKey(e.getKeyCode()));
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
