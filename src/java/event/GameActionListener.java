package event;

import org.jetbrains.annotations.NotNull;
import tetris.shapes.AbstractShape;

import java.util.EventListener;

public interface GameActionListener extends EventListener {

    void scoresUpdated(@NotNull GameActionEvent event);

    void gameIsOver(@NotNull GameActionEvent event);

    void nextShapeUpdated(@NotNull GameActionEvent event, @NotNull AbstractShape shape);
}
