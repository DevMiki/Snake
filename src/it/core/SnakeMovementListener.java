//package it.core;
//
//import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;
//
//public class SnakeMovementListener implements KeyListener {
//
//    private int moves = 0;
//
//    private boolean left = false;
//    private boolean right = false;
//    private boolean up = false;
//    private boolean down = false;
//
//    @Override
//    public void keyTyped(KeyEvent e) {
//
//    }
//
//    @Override
//    public void keyPressed(KeyEvent e) {
//        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
//            moves++;
//            right = true;
//            if(!left){
//                right = true;
//            } else {
//                right = false;
//                left = true;
//            }
//            up = false;
//            down = false;
//        }
//
//        if(e.getKeyCode() == KeyEvent.VK_LEFT){
//            moves++;
//            left = true;
//            if(!right){
//                left = true;
//            } else {
//                left = false;
//                right = true;
//            }
//            up = false;
//            down = false;
//        }
//
//        if(e.getKeyCode() == KeyEvent.VK_UP){
//            moves++;
//            up = true;
//            if(!down){
//                up = true;
//            } else {
//                up = false;
//                down = true;
//            }
//            left = false;
//            right = false;
//        }
//
//
//        if(e.getKeyCode() == KeyEvent.VK_DOWN){
//            moves++;
//            down = true;
//            if(!up){
//                down = true;
//            } else {
//                up = true;
//                down = false;
//            }
//            left = false;
//            right = false;
//        }
//    }
//
//    @Override
//    public void keyReleased(KeyEvent e) {
//
//    }
//}
