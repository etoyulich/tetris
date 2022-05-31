package event;

import java.util.EventObject;

public class CellActionEvent extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public CellActionEvent(Object source) {
        super(source);
    }
}
