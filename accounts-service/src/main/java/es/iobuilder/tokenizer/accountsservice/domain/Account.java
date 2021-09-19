package es.iobuilder.tokenizer.accountsservice.domain;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

/**
 * Domain Account Object
 */
@Data
public class Account {

    /**
     * ID
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
     * User's id
     */
    private Long user;

    /**
     * Creation Date
     */
    private LocalDate created;

    /**
     * Account's Movement List
     */
    private List<Movement> movementList;
}
