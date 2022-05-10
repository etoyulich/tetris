package event;

import java.util.EventObject;

public class ShapeActionEvent extends EventObject {

    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public ShapeActionEvent(Object source) {
        super(source);
    }
}
