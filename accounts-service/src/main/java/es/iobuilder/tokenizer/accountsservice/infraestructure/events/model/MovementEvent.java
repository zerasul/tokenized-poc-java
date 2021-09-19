package es.iobuilder.tokenizer.accountsservice.infraestructure.events.model;

import es.iobuilder.tokenizer.accountsservice.infraestructure.db.model.MovementEntity;
import org.springframework.context.ApplicationEvent;


/**
 * Movement Event . this is the creation Movement Event
 */
public class MovementEvent extends ApplicationEvent {

    /**
     * Movement Information
     * <strong>NOTE</strong> We use the DBO Object for demostration pruposes. We can create a new Object for the events.
     */
    private MovementEntity movement;

    /**
     * Default Constructor
     * @param source Source Object
     * @param entity Movement Information
     */
    public MovementEvent(Object source, MovementEntity entity) {
        super(source);
    }

    /**
     * Get the Movement Information
     * @return
     */
    public MovementEntity getMovement(){
        return this.movement;
    }
}
