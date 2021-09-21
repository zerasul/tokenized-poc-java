package es.iobuilder.tokenizer.accountsservice.application.rest.controller;

import es.iobuilder.tokenizer.accountsservice.application.rest.dto.MovementDTO;
import es.iobuilder.tokenizer.accountsservice.application.rest.input.DepositInput;
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
 * Deposit Controller. The entrypoint for creating account's desposit
 */
@RestController
public class DepositController {

    /**
     * Movement Service
     */
    private MovementService movementService;

    /**
     * Movement DTO/Domain Mapper
     */
    private MovementMapper movementMapper;


    /**
     * Constructor
     * @param movementService {@link MovementService} Object
     * @param movementMapper {@link MovementMapper} Object
     */
    @Autowired
    public DepositController(MovementService movementService, MovementMapper movementMapper){
        this.movementService=movementService;
        this.movementMapper=movementMapper;
    }

    /**
     * Get an existing Deposit
     * @param id Deposit Id
     * @return Movement Object
     */
    @GetMapping("/deposit/{id}")
    public ResponseEntity<MovementDTO> getDeposit(@PathVariable("id")Long id){
        
         try {
            Movement movement=movementService.getMovement(id);
            return new ResponseEntity<>(movementMapper.toDTO(movement), HttpStatus.OK);
        } catch (MovementNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Create a new Deposit
     * @param input {@link DepositInput} Object
     * @return MovementDTO Object with the deposit Information
     */
    @PostMapping("/deposit")
    public ResponseEntity<MovementDTO> createDeposit(@RequestBody DepositInput input){

        if(Objects.isNull(input)){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        if(Objects.isNull(input.getAmount()) || Objects.isNull(input.getCurrency())|| Objects.isNull(input.getDestinationNumber())){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        try {
            MovementDTO movement=movementMapper.toDTO(movementService.createDeposit(input.getAmount(),input.getDestinationNumber(),input.getCurrency()));
            return new ResponseEntity<>(movement,HttpStatus.CREATED);
        } catch (AccountNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }
}
