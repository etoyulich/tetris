package event;

import org.jetbrains.annotations.NotNull;
import tetris.Cell;

import java.awt.event.ActionListener;
import java.util.EventListener;

public interface CellActionListener extends EventListener {
    void cellCleared(@NotNull CellActionEvent event, @NotNull Cell cell);
    void cellFilled(@NotNull CellActionEvent event, @NotNull Cell cell);
}
