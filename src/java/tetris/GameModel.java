package tetris;

import event.GameActionEvent;
import event.GameActionListener;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

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

    public Shape getCurrentShape(){
        return shapes[0];
    }

    private final Shape[] shapes = new Shape[numberOfShapes];


    public GameModel(int width, int height, GameActionListener l) {
        glass = new Glass(height, width);

        if(l != null){
            addGameActionListener(l);
        }

        shapes[0] = chooseShape();
        shapes[0].setShapeOnGlass(glass);
        shapes[1] = chooseShape();
        fireShapeIsUpdated(shapes[0]);
        fireNextShapeUpdated(new Shape(shapes[1]));
    }

    public boolean makeOneStep() {
        if (!shapes[0].move(Direction.down())) {
            scorePoints();
            if(shapes[1].setShapeOnGlass(glass) && shapes[1].canMove(Direction.down())){
                moveShapeQueue();
            }
            else {
                    fireGameIsOver();
                    return false;
            }
            fireShapeIsUpdated(shapes[0]);
        }
        return true;
    }

    private Shape chooseShape() {
        int a = (int) (Math.random() * 7);
        if (a == 0) {
            return Shape.generateI(glass.getWidth(), glass.getHeight());
        } else if (a == 1) {
            return Shape.generateJ(glass.getWidth(), glass.getHeight());
        } else if (a == 2) {
            return Shape.generateL(glass.getWidth(), glass.getHeight());
        } else if (a == 3) {
            return Shape.generateO(glass.getWidth(), glass.getHeight());
        } else if (a == 4) {
            return Shape.generateS(glass.getWidth(), glass.getHeight());
        } else if (a == 5) {
            return Shape.generateT(glass.getWidth(), glass.getHeight());
        } else {
            return Shape.generateZ(glass.getWidth(), glass.getHeight());
        }
    }

    private void scorePoints() {
        if(glass.getFilledRows() != 0){
            points += glass.getFilledRows() * 50;
            glass.moveRows();
            fireScoresUpdated();
        }
    }

    private void moveShapeQueue() {
        System.arraycopy(shapes, 1, shapes, 0, numberOfShapes - 1);
        shapes[numberOfShapes - 1] = chooseShape();
        fireNextShapeUpdated(new Shape(shapes[1]));
    }

    //-----------------------------------------------------
    private final ArrayList<GameActionListener> gameActionListeners = new ArrayList<>();

    public void addGameActionListener(@NotNull GameActionListener listener) {
        gameActionListeners.add(listener);
    }

    private void fireScoresUpdated(){
        for (GameActionListener listener : gameActionListeners) {
            GameActionEvent event = new GameActionEvent(listener);
            listener.scoresUpdated(event);
        }
    }

    private void fireShapeIsUpdated(Shape shape) {
        for (GameActionListener listener : gameActionListeners) {
            GameActionEvent event = new GameActionEvent(listener);
            listener.shapeIsUpdated(event, shape);
        }
    }

    private void fireGameIsOver() {
        for (GameActionListener listener : gameActionListeners) {
            GameActionEvent event = new GameActionEvent(listener);
            listener.gameIsOver(event);
        }
    }

    private void fireNextShapeUpdated(Shape shape){
        for (GameActionListener listener : gameActionListeners) {
            GameActionEvent event = new GameActionEvent(listener);
            Shape next = new Shape(shape);
            listener.nextShapeUpdated(event, next);
        }
    }

}