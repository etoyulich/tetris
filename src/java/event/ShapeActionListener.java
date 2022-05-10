package event;

import org.jetbrains.annotations.NotNull;
import tetris.Cell;

import java.util.EventListener;
import java.util.List;
import java.util.Optional;

public interface ShapeActionListener extends EventListener {
    void cellsCleared(@NotNull ShapeActionEvent event, @NotNull List<Optional<Cell>> cells);
    void cellsFilled(@NotNull ShapeActionEvent event, @NotNull List<Optional<Cell>> cells);
}
