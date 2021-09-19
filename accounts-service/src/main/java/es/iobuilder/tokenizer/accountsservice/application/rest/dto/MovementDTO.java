package es.iobuilder.tokenizer.accountsservice.application.rest.dto;

import es.iobuilder.tokenizer.accountsservice.infraestructure.db.model.AccountEntity;

import lombok.Data;
import java.time.LocalDate;


/**
 * Movement DTO
 */
@Data
public class MovementDTO {

    /**
     * ID
     */
    private Long id;

    /**
     * Transfer Date
     */
    private LocalDate transferDate;

    /**
     * Origin Account (can be null for deposits).
     */
    private AccountEntity originAccount;


    /**
     * Destination account (Must be an existing account).
     */
    private AccountEntity destinationAccount;


    /**
     * amount
     */
    private Double amount;

    /**
     * Currency
     */
    private String currency;
}
