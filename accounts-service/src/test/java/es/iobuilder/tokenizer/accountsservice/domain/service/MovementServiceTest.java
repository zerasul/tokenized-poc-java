package es.iobuilder.tokenizer.accountsservice.domain.service;

import es.iobuilder.tokenizer.accountsservice.domain.Account;
import es.iobuilder.tokenizer.accountsservice.domain.Movement;
import es.iobuilder.tokenizer.accountsservice.domain.exceptions.MovementNotFoundException;
import es.iobuilder.tokenizer.accountsservice.infraestructure.db.mapper.AccountMapper;
import es.iobuilder.tokenizer.accountsservice.infraestructure.db.mapper.MovementMapper;
import es.iobuilder.tokenizer.accountsservice.infraestructure.db.model.AccountEntity;
import es.iobuilder.tokenizer.accountsservice.infraestructure.db.model.MovementEntity;
import es.iobuilder.tokenizer.accountsservice.infraestructure.events.publisher.MovementPublisher;
import es.iobuilder.tokenizer.accountsservice.infraestructure.repositories.MovementRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class MovementServiceTest {

    @MockBean
    private MovementRepository movementRepository;

    @MockBean
    private MovementMapper movementMapper;

    @MockBean
    private MovementPublisher movementPublisher;

    @MockBean
    private AccountService accountService;

    @MockBean
    private AccountMapper accountMapper;

    @SpyBean
    private MovementService movementService;

    @Test
    public void testGetMovement()throws Exception{

        MovementEntity m = MovementEntity.builder().currency("BTC").build();
        Mockito.when(movementRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(m));
        Movement movement = new Movement();
        movement.setCurrency("BTC");
        Mockito.when(movementMapper.toDomain(Mockito.any(MovementEntity.class))).thenReturn(movement);
        Movement result = movementService.getMovement(1L);
        assertNotNull(result);
        assertEquals("BTC",result.getCurrency());
    }

    @Test
    public void testGetMovementThrowException(){

        MovementEntity m = MovementEntity.builder().currency("BTC").build();
        Mockito.when(movementRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        Movement movement = new Movement();
        movement.setCurrency("BTC");
        Mockito.when(movementMapper.toDomain(Mockito.any(MovementEntity.class))).thenReturn(movement);
       assertThrows(MovementNotFoundException.class,() ->{
          movementService.getMovement(1L);
       });
    }

    @Test
    public void testcreateDeposit()throws Exception{
        MovementEntity m = MovementEntity.builder().currency("BTC").build();
        Account a = new Account();
        a.setName("Test");
        AccountEntity accountEntity = AccountEntity.builder().name("Test").build();
        Mockito.when(accountService.getAccountByNumber(Mockito.anyString())).thenReturn(a);
        Mockito.when(accountMapper.toDBO(Mockito.any(Account.class))).thenReturn(accountEntity);
        Movement movement= new Movement();
        movement.setCurrency("BTC");
        Mockito.when(movementMapper.toDomain(Mockito.any(MovementEntity.class))).thenReturn(movement);
        Movement result = movementService.createDeposit(2.2d,"1111","BTC");
        Mockito.verify(movementPublisher).publishMovementEvent(Mockito.any(MovementEntity.class));
        assertNotNull(result);
        assertEquals("BTC",result.getCurrency());
    }

    @Test
    public void testcreateTransfer()throws Exception{
        MovementEntity m = MovementEntity.builder().currency("BTC").build();
        Account a = new Account();
        a.setName("Test");
        AccountEntity accountEntity = AccountEntity.builder().name("Test").build();
        Mockito.when(accountService.getAccountByNumber(Mockito.anyString())).thenReturn(a);
        Mockito.when(accountMapper.toDBO(Mockito.any(Account.class))).thenReturn(accountEntity);
        Movement movement= new Movement();
        movement.setCurrency("BTC");
        Mockito.when(movementMapper.toDomain(Mockito.any(MovementEntity.class))).thenReturn(movement);
        Movement result = movementService.createTransfer(2.2d,"1111","1111","BTC");
        Mockito.verify(movementPublisher).publishMovementEvent(Mockito.any(MovementEntity.class));
        assertNotNull(result);
        assertEquals("BTC",result.getCurrency());
    }



}