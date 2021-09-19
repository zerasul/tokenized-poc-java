package es.iobuilder.tokenizer.accountsservice.application.rest.controller;

import es.iobuilder.tokenizer.accountsservice.application.rest.dto.MovementDTO;
import es.iobuilder.tokenizer.accountsservice.application.rest.input.TransferInput;
import es.iobuilder.tokenizer.accountsservice.application.rest.mapper.MovementMapper;
import es.iobuilder.tokenizer.accountsservice.domain.Movement;
import es.iobuilder.tokenizer.accountsservice.domain.exceptions.AccountNotFoundException;
import es.iobuilder.tokenizer.accountsservice.domain.exceptions.MovementNotFoundException;
import es.iobuilder.tokenizer.accountsservice.domain.service.MovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * Transfer Controller. EntryPoint for the Transfers
 */
@RestController
public class TransferController {

    /**
     * {@link MovementService} Object
     */
    private MovementService movementService;

    /**
     * {@link MovementMapper} Object. DBO/Domain Mapper
     */
    private MovementMapper movementMapper;

    /**
     * Constructor
     * @param movementService {@link MovementService} Object
     * @param movementMapper {@link MovementMapper} Object.
     */
    @Autowired
    public TransferController(MovementService movementService, MovementMapper movementMapper){
        this.movementService=movementService;
        this.movementMapper=movementMapper;
    }


    /**
     * Create a new Transfer
     * @param input {@link TransferInput} Object
     * @return A new MovementDTO Object with the minimal information. The Creation of the transfer is asynchronous.
     */
    @PostMapping("/transfer")
    public ResponseEntity<MovementDTO> createTransfer(@RequestBody TransferInput input){

        //This is only for demostration pruposes.
        // Of course we need to validate all the information and the user's account information.
        if(Objects.isNull(input)){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        if(Objects.isNull(input.getCurrency())){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }


        try {
            Movement movement=movementService.createTransfer(input.getAmount(),input.getOriginAccount(),input.getDestinationAccount(),input.getCurrency());
            return new ResponseEntity<>(movementMapper.toDTO(movement), HttpStatus.CREATED);
        } catch (AccountNotFoundException e) {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }

    }

    /**
     * Get an existing Transfer
     * @param id Transfer Id
     * @return MovementDTO
     */
    @GetMapping("/transfer/{id}")
    public ResponseEntity<MovementDTO> getTransfer(@PathVariable("id")Long id){

        try {
            Movement movement=movementService.getMovement(id);
            return new ResponseEntity<>(movementMapper.toDTO(movement), HttpStatus.OK);
        } catch (MovementNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
