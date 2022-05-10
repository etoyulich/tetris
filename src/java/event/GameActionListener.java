package event;

import org.jetbrains.annotations.NotNull;
import tetris.Shape;

import java.util.EventListener;

public interface GameActionListener extends EventListener {

    void scoresUpdated(@NotNull GameActionEvent event);

    void shapeIsUpdated(@NotNull GameActionEvent event, @NotNull Shape shape);

    void gameIsOver(@NotNull GameActionEvent event);

    void nextShapeUpdated(@NotNull GameActionEvent event, @NotNull Shape shape);
}
