package it.core.snake;


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Snake {

    public enum Direction {
        UP, DOWN, RIGHT, LEFT;

        private static Map<Integer, Direction> keyToDirection = Map.of(
                KeyEvent.VK_RIGHT,RIGHT,
                KeyEvent.VK_LEFT,LEFT,
                KeyEvent.VK_UP,UP,
                KeyEvent.VK_DOWN,DOWN);

        public static Direction fromKey(int key){
            return keyToDirection.get(key);
        }
    }

    private Direction direction;
    private List<SnakePiece> snakePieces;
    private SnakeSpace snakeSpace;

    public SnakeSpace getSnakeSpace() {
        return snakeSpace;
    }

    public void setSnakeSpace(SnakeSpace snakeSpace) {
        this.snakeSpace = snakeSpace;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public List<SnakePiece> getSnakePieces() {
        return snakePieces;
    }

    public void setSnakePieces(List<SnakePiece> snakePieces) {
        this.snakePieces = snakePieces;
    }

    public Snake(Direction direction, SnakeSpace snakeSpace) {
        this.direction = direction;
        this.snakeSpace = snakeSpace;
        setPiecesByDirection();
    }

    //makes move my snake
    private void setPiecesByDirection() {
        Function<Integer, SnakePiece> pieceFactory = null;
        switch (direction) {
            case UP:
                pieceFactory = num -> snakeSpace.computeCoordsInSpace(100, 50 + (25 * num));
                break;
            case DOWN:
                pieceFactory = num -> snakeSpace.computeCoordsInSpace(100, 100 - (25 * num));
                break;
            case RIGHT:
                pieceFactory = num -> snakeSpace.computeCoordsInSpace(100 - (25 * num), 100);
                break;
            case LEFT:
                pieceFactory = num -> snakeSpace.computeCoordsInSpace(50 + (25 * num), 100);
                break;
        }
        snakePieces = IntStream.range(1, 4).boxed().map(pieceFactory).collect(Collectors.toCollection(LinkedList::new));
    }


    public void draw(Component component, Graphics graphics) {
        snakePieces.forEach(snakePiece -> {
            toIcon(snakePiece).paintIcon(component, graphics, snakePiece.getX(), snakePiece.getY());
        });
    }

    private ImageIcon toIcon(SnakePiece p) {
        if (p == snakePieces.get(0)) {
            return new ImageIcon(direction.toString().toLowerCase().concat("mouth.png"));
        }
        return new ImageIcon("snakeimage.png");
    }

    public void move() {
        final SnakePiece head = snakePieces.get(0);
        SnakePiece newHead = null;

        switch (direction) {
            case RIGHT: {
                newHead = snakeSpace.computeCoordsInSpace(head.getX() + 25, head.getY());
                break;
            }
            case LEFT: {
                newHead = snakeSpace.computeCoordsInSpace(head.getX() - 25, head.getY());
                break;
            }
            case UP: {
                newHead = snakeSpace.computeCoordsInSpace(head.getX(), head.getY() - 25);
                break;
            }
            case DOWN: {
                newHead = snakeSpace.computeCoordsInSpace(head.getX(), head.getY() + 25);
                break;
            }
        }
        snakePieces.add(0, newHead);
        snakePieces.remove(snakePieces.size() - 1);
    }

    public boolean hasEaten(Enemy enemy){
        final SnakePiece head = snakePieces.get(0);
        return (enemy.getxPosition() == head.getX() && enemy.getyPosition() == head.getY());
    }

    public void grow(){
        final SnakePiece tail = snakePieces.get(snakePieces.size() - 1);
        snakePieces.add(new SnakePiece(tail.getX(), tail.getY()));
    }

    public boolean isAlive(){
        final boolean[] alive = {true};
        snakePieces.stream().skip(1).forEach(piece -> {
            if (snakePieces.get(0).getX() == piece.getX() && snakePieces.get(0).getY() == piece.getY()) {
                alive[0] = false;
            }
        });
        return alive[0];
    }

}
