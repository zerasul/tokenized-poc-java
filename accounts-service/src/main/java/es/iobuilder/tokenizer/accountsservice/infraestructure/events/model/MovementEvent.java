package es.iobuilder.tokenizer.accountsservice.infraestructure.events.model;

import es.iobuilder.tokenizer.accountsservice.domain.Movement;
import es.iobuilder.tokenizer.accountsservice.infraestructure.db.model.MovementEntity;
import lombok.Data;
import org.springframework.context.ApplicationEvent;


public class MovementEvent extends ApplicationEvent {

    private MovementEntity movement;

    public MovementEvent(Object source, MovementEntity entity) {
        super(source);
    }

    public MovementEntity getMovement(){
        return this.movement;
    }
}
