package es.iobuilder.tokenizer.accountsservice.domain.service;

import es.iobuilder.tokenizer.accountsservice.domain.Account;
import es.iobuilder.tokenizer.accountsservice.domain.Movement;
import es.iobuilder.tokenizer.accountsservice.domain.exceptions.AccountNotFoundException;
import es.iobuilder.tokenizer.accountsservice.domain.exceptions.MovementNotFoundException;
import es.iobuilder.tokenizer.accountsservice.infraestructure.db.mapper.AccountMapper;
import es.iobuilder.tokenizer.accountsservice.infraestructure.db.mapper.MovementMapper;
import es.iobuilder.tokenizer.accountsservice.infraestructure.db.model.MovementEntity;
import es.iobuilder.tokenizer.accountsservice.infraestructure.events.publisher.MovementPublisher;
import es.iobuilder.tokenizer.accountsservice.infraestructure.repositories.MovementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

/**
 * Movement Service. Allow the operations with Account's MOvements (Deposits or Transfers).
 */
@Service
public class MovementService {

    /**
     * Movement Repository
     */
    private MovementRepository movementRepository;

    /**
     * Movement Mapper DBO/Domain Mapper
     */
    private MovementMapper movementMapper;

    /**
     * Movement Event Publisher
     */
    private MovementPublisher movementPublisher;

    /**
     * Account Service
     */
    private AccountService accountService;

    /**
     * Account Mapper. DBO to DOmain Mapper
     */
    private AccountMapper accountMapper;


    /**
     * Constructor
     * @param movementRepository Movement Repository
     * @param movementMapper Movement DBO/DOmain Mapper
     * @param movementPublisher Movement event Publisher Object
     * @param accountService Account Service
     * @param accountMapper Account DBO/DOmain Mapper
     */
    @Autowired
    public MovementService(MovementRepository movementRepository, MovementMapper movementMapper, MovementPublisher movementPublisher, AccountService accountService, AccountMapper accountMapper) {
        this.movementRepository = movementRepository;
        this.movementMapper = movementMapper;
        this.movementPublisher = movementPublisher;
        this.accountService = accountService;
        this.accountMapper = accountMapper;
    }

    /**
     * Creates a new Transfer
     * @param ammount amount
     * @param originAccountNumber origin Account number
     * @param destinationAccountNumber Destination Account Number
     * @param currency Currency
     * @return Created Movement with the minimal Information. The Operation is Asynchronous.
     * @throws AccountNotFoundException Throw this exception if the origin or destination account doesn't exists.
     */
    public Movement createTransfer(Double ammount, String originAccountNumber, String destinationAccountNumber, String currency)throws AccountNotFoundException{

        Account destinationAccount=accountService.getAccountByNumber(destinationAccountNumber);
        Account originAccount = accountService.getAccountByNumber(originAccountNumber);
        MovementEntity entity = MovementEntity.builder().
                amount(ammount)
                .originAccount(accountMapper.toDBO(originAccount))
                .destinationAccount(accountMapper.toDBO(destinationAccount))
                .transferDate(LocalDate.now())
                .currency(currency)
                .build();
        movementPublisher.publishMovementEvent(entity);

        return movementMapper.toDomain(entity);

    }
    /**
     * Creates a new Transfer
     * @param ammount amount
     * @param destinationAccount Destination Account Number
     * @param currency Currency
     * @return Created Movement with the minimal Information. The Operation is Asynchronous.
     * @throws AccountNotFoundException Throw this exception if the origin or destination account doesn't exists.
     */
    public Movement createDeposit(Double ammount, String destinationAccount, String currency)throws AccountNotFoundException {

        Account account=accountService.getAccountByNumber(destinationAccount);

        MovementEntity entity = MovementEntity.builder().
                amount(ammount)
                .originAccount(null)
                .destinationAccount(accountMapper.toDBO(account))
                .transferDate(LocalDate.now())
                .currency(currency)
        .build();
        movementPublisher.publishMovementEvent(entity);

        return movementMapper.toDomain(entity);
    }


    /**
     * Gets an existing movement
     * @param id Movement id
     * @return Movement Object
     * @throws MovementNotFoundException throw This Exception if the Movement doesn't exists.
     */
    public Movement getMovement(Long id) throws MovementNotFoundException {
        Optional<MovementEntity> entity = movementRepository.findById(id);
        if(entity.isEmpty()){
            throw  new MovementNotFoundException();
        }
        return movementMapper.toDomain(entity.get());
    }


}
