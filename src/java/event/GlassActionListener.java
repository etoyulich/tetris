package event;

import org.jetbrains.annotations.NotNull;
import tetris.Glass;

import java.util.EventListener;

public interface GlassActionListener extends EventListener {
    void rowsCleared(@NotNull GlassActionEvent event, @NotNull Glass glass);
}
