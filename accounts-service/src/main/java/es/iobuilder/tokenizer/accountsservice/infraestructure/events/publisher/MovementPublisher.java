package es.iobuilder.tokenizer.accountsservice.infraestructure.events.publisher;

import es.iobuilder.tokenizer.accountsservice.infraestructure.db.model.MovementEntity;
import es.iobuilder.tokenizer.accountsservice.infraestructure.events.model.MovementEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class MovementPublisher {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publishMovementEvent(MovementEntity entity){

        MovementEvent event= new MovementEvent(this, entity);
        applicationEventPublisher.publishEvent(event);
    }
}
