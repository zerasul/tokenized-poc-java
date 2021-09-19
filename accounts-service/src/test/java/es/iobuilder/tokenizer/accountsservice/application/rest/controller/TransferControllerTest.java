package es.iobuilder.tokenizer.accountsservice.application.rest.controller;

import es.iobuilder.tokenizer.accountsservice.application.rest.dto.MovementDTO;
import es.iobuilder.tokenizer.accountsservice.application.rest.input.AccountInput;
import es.iobuilder.tokenizer.accountsservice.application.rest.input.TransferInput;
import es.iobuilder.tokenizer.accountsservice.application.rest.mapper.MovementMapper;
import es.iobuilder.tokenizer.accountsservice.domain.Movement;
import es.iobuilder.tokenizer.accountsservice.domain.exceptions.AccountNotFoundException;
import es.iobuilder.tokenizer.accountsservice.domain.exceptions.MovementNotFoundException;
import es.iobuilder.tokenizer.accountsservice.domain.service.MovementService;
import org.apache.coyote.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({SpringExtension.class})
class TransferControllerTest {

    @MockBean
    MovementService movementService;

    @MockBean
    MovementMapper movementMapper;

    @SpyBean
    TransferController transferController;

    @Test
    public void testgetTransfer()throws Exception{

        Movement m = new Movement();
        m.setCurrency("BTC");
        Mockito.when(movementService.getMovement(Mockito.anyLong())).thenReturn(m);
        MovementDTO dto = new MovementDTO();
        dto.setCurrency("BTC");
        Mockito.when(movementMapper.toDTO(Mockito.any(Movement.class))).thenReturn(dto);
        ResponseEntity<MovementDTO> result = transferController.getTransfer(1L);
        assertNotNull(result);
        assertEquals(200, result.getStatusCodeValue());
        assertNotNull(result.getBody());
        assertEquals("BTC",result.getBody().getCurrency());
    }

    @Test
    public void testgetTransferThrowsException()throws Exception{

        Movement m = new Movement();
        m.setCurrency("BTC");
        Mockito.when(movementService.getMovement(Mockito.anyLong())).thenThrow(MovementNotFoundException.class);
        MovementDTO dto = new MovementDTO();
        dto.setCurrency("BTC");
        Mockito.when(movementMapper.toDTO(Mockito.any(Movement.class))).thenReturn(dto);
        ResponseEntity<MovementDTO> result = transferController.getTransfer(1L);
        assertNotNull(result);
        assertEquals(404,result.getStatusCodeValue());
        assertNull(result.getBody());

    }

    @Test
    public void testCreateTransferNullInput(){

        ResponseEntity<MovementDTO> result =transferController.createTransfer(null);
        assertNotNull(result);
        assertNull(result.getBody());
        assertEquals(400, result.getStatusCodeValue());
    }

    @Test
    public void testCreateTransferNullCurrency(){
        TransferInput input = new TransferInput();
        input.setAmount(2.2d);
        input.setDestinationAccount("11111");
        input.setOriginAccount("11111");

        ResponseEntity<MovementDTO> result =transferController.createTransfer(input);
        assertNotNull(result);
        assertEquals(400,result.getStatusCodeValue());
        assertNull(result.getBody());
    }

    @Test
    public void testCreateTransfer()throws Exception{
        TransferInput input = new TransferInput();
        input.setAmount(2.2d);
        input.setDestinationAccount("11111");
        input.setOriginAccount("11111");
        input.setCurrency("BTC");
        Movement m = new Movement();
        m.setCurrency("BTC");
        MovementDTO dto = new MovementDTO();
        dto.setCurrency("BTC");

        Mockito.when(movementService.createTransfer(Mockito.anyDouble(),Mockito.anyString(),Mockito.anyString(),Mockito.anyString())).thenReturn(m);
        Mockito.when(movementMapper.toDTO(Mockito.any(Movement.class))).thenReturn(dto);
        ResponseEntity<MovementDTO> result =transferController.createTransfer(input);
        assertNotNull(result);
        assertNotNull(result.getBody());
        assertEquals(201, result.getStatusCodeValue());
        assertEquals("BTC",result.getBody().getCurrency());
    }

    @Test
    public void testCreateTransferThrowsException()throws Exception{
        TransferInput input = new TransferInput();
        input.setAmount(2.2d);
        input.setDestinationAccount("11111");
        input.setOriginAccount("11111");
        input.setCurrency("BTC");
        Movement m = new Movement();
        m.setCurrency("BTC");
        MovementDTO dto = new MovementDTO();
        dto.setCurrency("BTC");

        Mockito.when(movementService.createTransfer(Mockito.anyDouble(),Mockito.anyString(),Mockito.anyString(),Mockito.anyString())).thenThrow(AccountNotFoundException.class);
        Mockito.when(movementMapper.toDTO(Mockito.any(Movement.class))).thenReturn(dto);
        ResponseEntity<MovementDTO> result =transferController.createTransfer(input);
        assertNotNull(result);
        assertNull(result.getBody());
        assertEquals(404, result.getStatusCodeValue());
    }
}