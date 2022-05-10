package event;

import java.util.EventObject;

public class GlassActionEvent extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public GlassActionEvent(Object source) {
        super(source);
    }
}
