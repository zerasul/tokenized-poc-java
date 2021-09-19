package es.iobuilder.tokenizer.accountsservice.infraestructure.events.publisher;

import es.iobuilder.tokenizer.accountsservice.infraestructure.db.model.MovementEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class MovementPublisherTest {

    @MockBean
    ApplicationEventPublisher applicationEventPublisher;

    @SpyBean
    MovementPublisher movementPublisher;

    @Test
    public void testPublishEvent(){
        movementPublisher.publishMovementEvent(MovementEntity.builder().currency("BTC").build());


    }
}