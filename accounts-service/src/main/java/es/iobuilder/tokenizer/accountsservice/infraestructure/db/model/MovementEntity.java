package es.iobuilder.tokenizer.accountsservice.infraestructure.db.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import java.time.LocalDate;

/**
 * Entity Movement
 */
@Data
@Builder
@Entity
public class MovementEntity {

    /**
     * Id
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * Transfer Date
     */
    private LocalDate transferDate;

    /**
     * Origin Account
     */
    @JoinColumn
    private AccountEntity originAccount;

    /**
     * Destination Account
     */
    @JoinColumn
    private AccountEntity destinationAccount;

    /**
     * Amount
     */
    private Double amount;

    /**
     * Currency
     */
    private String currency;


}
