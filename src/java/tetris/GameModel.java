package tetris;

import event.GameActionEvent;
import event.GameActionListener;
import org.jetbrains.annotations.NotNull;
import tetris.shapes.AbstractShape;
import tetris.shapes.ShapeStorage;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Map;

public class GameModel {


    public int getPoints() {
        return points;
    }

    private int points = 0;

    public Glass getGlass() {
        return glass;
    }

    private final Glass glass;
    private final static int numberOfShapes = 2;

    public AbstractShape getCurrentShape() {
        return shapes[0];
    }

    private final AbstractShape[] shapes = new AbstractShape[numberOfShapes];

    private final ShapeStorage storage;

    public GameModel(int width, int height, GameActionListener l) {
        glass = new Glass(height, width);

        if (l != null) {
            addGameActionListener(l);
        }

        storage = new ShapeStorage(width, height);
        shapes[0] = chooseShape();
        shapes[0].setShapeOnGlass(glass);
        shapes[1] = chooseShape();
        fireNextShapeUpdated(shapes[1].clone());
    }

    public boolean makeOneStep() {
        if (!shapes[0].move(Direction.down())) {
            scorePoints();
            Point2D newCenter = new Point2D.Double(shapes[1].getRotationCenter().getX(), shapes[1].getRotationCenter().getY() - 1);
            Map<Point2D, Piece> newPos = shapes[1].getVectors();
            if (shapes[1].setShapeOnGlass(glass) && shapes[1].canMove(newCenter, newPos)) {
                moveShapeQueue();
            } else {
                fireGameIsOver();
                return false;
            }
        }
        return true;
    }

    private AbstractShape chooseShape() {
        int id = (int) (Math.random() * storage.getStorageSize());
        try {
            return storage.getById(id);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("Невозможно найти заданную фигурку");
    }

    private void scorePoints() {
        if (glass.getFilledRows() != 0) {
            points += glass.getFilledRows() * 50;
            glass.moveRows();
            fireScoresUpdated();
        }
    }

    private void moveShapeQueue() {
        System.arraycopy(shapes, 1, shapes, 0, numberOfShapes - 1);
        shapes[numberOfShapes - 1] = chooseShape();
        fireNextShapeUpdated(shapes[1].clone());
    }

    //-----------------------------------------------------
    private final ArrayList<GameActionListener> gameActionListeners = new ArrayList<>();

    public void addGameActionListener(@NotNull GameActionListener listener) {
        gameActionListeners.add(listener);
    }

    private void fireScoresUpdated() {
        for (GameActionListener listener : gameActionListeners) {
            GameActionEvent event = new GameActionEvent(listener);
            listener.scoresUpdated(event);
        }
    }

    private void fireGameIsOver() {
        for (GameActionListener listener : gameActionListeners) {
            GameActionEvent event = new GameActionEvent(listener);
            listener.gameIsOver(event);
        }
    }

    private void fireNextShapeUpdated(AbstractShape shape) {
        for (GameActionListener listener : gameActionListeners) {
            GameActionEvent event = new GameActionEvent(listener);
            listener.nextShapeUpdated(event, shape);
        }
    }

}