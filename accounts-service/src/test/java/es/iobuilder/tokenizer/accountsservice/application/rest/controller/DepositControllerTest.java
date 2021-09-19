package es.iobuilder.tokenizer.accountsservice.application.rest.controller;

import es.iobuilder.tokenizer.accountsservice.application.rest.dto.MovementDTO;
import es.iobuilder.tokenizer.accountsservice.application.rest.input.DepositInput;
import es.iobuilder.tokenizer.accountsservice.application.rest.mapper.MovementMapper;
import es.iobuilder.tokenizer.accountsservice.domain.Movement;
import es.iobuilder.tokenizer.accountsservice.domain.exceptions.AccountNotFoundException;
import es.iobuilder.tokenizer.accountsservice.domain.service.MovementService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class DepositControllerTest {

    @MockBean
    private MovementService movementService;

    @MockBean
    private MovementMapper movementMapper;

    @SpyBean
    DepositController depositController;

    @Test
    public void CreateDepositNullInput(){

        ResponseEntity<MovementDTO> result = depositController.createDeposit(null);
        assertNotNull(result);
        assertNull(result.getBody());
        assertEquals(400, result.getStatusCodeValue());

    }

    @Test
    public void CreateDepositNullAmount(){
        DepositInput input = new DepositInput();
        input.setCurrency("BTC");
        input.setDestinationNumber("11111");
        ResponseEntity<MovementDTO> result = depositController.createDeposit(input);
        assertNotNull(result);
        assertNull(result.getBody());
        assertEquals(400, result.getStatusCodeValue());

    }

    @Test
    public void CreateDepositNullCurrency(){
        DepositInput input = new DepositInput();
        input.setAmount(2.2d);
        input.setDestinationNumber("11111");
        ResponseEntity<MovementDTO> result = depositController.createDeposit(input);
        assertNotNull(result);
        assertNull(result.getBody());
        assertEquals(400, result.getStatusCodeValue());

    }

    @Test
    public void CreateDepositNullAccountNumber(){
        DepositInput input = new DepositInput();
        input.setAmount(2.2d);
        input.setCurrency("BTC");
        ResponseEntity<MovementDTO> result = depositController.createDeposit(input);
        assertNotNull(result);
        assertNull(result.getBody());
        assertEquals(400, result.getStatusCodeValue());

    }

    @Test
    public void CreateDeposit()throws Exception{
        DepositInput input = new DepositInput();
        input.setAmount(2.2d);
        input.setCurrency("BTC");
        input.setDestinationNumber("11111");
        Movement m = new Movement();
        m.setCurrency("BTC");
        Mockito.when(movementService.createDeposit(Mockito.anyDouble(),Mockito.anyString(),Mockito.anyString())).thenReturn(m);
        MovementDTO dto = new MovementDTO();
        dto.setCurrency("BTC");
        Mockito.when(movementMapper.toDTO(Mockito.any(Movement.class))).thenReturn(dto);
        ResponseEntity<MovementDTO> result = depositController.createDeposit(input);
        assertNotNull(result);
        assertNotNull(result.getBody());
        assertEquals("BTC",result.getBody().getCurrency());
        assertEquals(201, result.getStatusCodeValue());

    }

    @Test
    public void CreateDepositThrowException()throws Exception{
        DepositInput input = new DepositInput();
        input.setAmount(2.2d);
        input.setCurrency("BTC");
        input.setDestinationNumber("11111");
        Movement m = new Movement();
        m.setCurrency("BTC");
        Mockito.when(movementService.createDeposit(Mockito.anyDouble(),Mockito.anyString(),Mockito.anyString())).thenThrow(AccountNotFoundException.class);
        ResponseEntity<MovementDTO> result = depositController.createDeposit(input);
        assertNotNull(result);
        assertNull(result.getBody());
        assertEquals(400,result.getStatusCodeValue());
    }


}