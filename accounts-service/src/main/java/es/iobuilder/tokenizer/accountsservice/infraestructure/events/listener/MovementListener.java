package es.iobuilder.tokenizer.accountsservice.infraestructure.events.listener;


import es.iobuilder.tokenizer.accountsservice.infraestructure.events.model.MovementEvent;
import es.iobuilder.tokenizer.accountsservice.infraestructure.repositories.MovementRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Movement Event listner. This class lisent the creation Movement Events and create a new Movement.
 */
@Slf4j
@Component
public class MovementListener  implements ApplicationListener<MovementEvent> {

    /**
     * Movement Repository
     */
    MovementRepository repository;

    /**
     * When a new Movement Event occurs this method is called
     * @param movementEvent Movement Event
     */
    @Override
    public void onApplicationEvent(MovementEvent movementEvent) {
        //This is only for demostration pruposes; we can use another implementations of asyncrunous events system.
        //we can use apache kafka or activeMQ implementations.
        //Of course we can use Tokenized MOney API or some Cryptocurrency wallets services.
        log.info("Creating a new Movement from account {} to account {}", movementEvent.getMovement().getOriginAccount().getNumber()
        , movementEvent.getMovement().getDestinationAccount().getNumber());

        repository.save(movementEvent.getMovement());
    }
}
