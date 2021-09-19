package es.iobuilder.tokenizer.accountsservice.infraestructure.events.publisher;

import es.iobuilder.tokenizer.accountsservice.infraestructure.db.model.MovementEntity;
import es.iobuilder.tokenizer.accountsservice.infraestructure.events.model.MovementEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * Movement Publisher. This Components allows to Publish de The Movement Events.
 */
@Component
public class MovementPublisher {

    /**
     * Application Event Publisher
     */
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    /**
     * Publish a new Movement Event
     * @param entity Movement Entity
     */
    public void publishMovementEvent(MovementEntity entity){

        MovementEvent event= new MovementEvent(this, entity);
        applicationEventPublisher.publishEvent(event);
    }
}
