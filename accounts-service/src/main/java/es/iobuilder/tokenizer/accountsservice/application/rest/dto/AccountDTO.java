package es.iobuilder.tokenizer.accountsservice.application.rest.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

/**
 * Accounts DTO
 */
@Data
public class AccountDTO {

    /**
     * Id
     */
    private Long id;

    /**
     * Account Name
     */
    private String name;

    /**
     * Account Number
     */
    private String number;

    /**
     * Account User Id
     */
    private Long user;

    /**
     * Creation Date
     */
    private LocalDate created;

    /**
     * Account Movement List
     */
    private List<MovementDTO> movementList;
}
