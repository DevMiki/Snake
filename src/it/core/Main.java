package it.core;

import it.core.snake.SnakeGame;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        SnakeGame snakeGame = new SnakeGame();
        JFrame frame = new JFrame();
        frame.setBounds(10,10,905,700);
        frame.add(snakeGame);
        frame.getContentPane().setBackground(Color.darkGray);
        frame.setBackground(Color.darkGray);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

}
