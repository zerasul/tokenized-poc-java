package es.iobuilder.tokenizer.accountsservice.domain;

import java.time.LocalDate;
import lombok.Data;

/**
 * Movement DOmain Object
 */
@Data
public class Movement {

    /**
     * Id
     */
    private Long id;
    /**
     * Transfer Date
     */
    private LocalDate transferDate;
    /**
     * Origin Account
     */
    private Account originAccount;

    /**
     * Destination Account
     */
    private Account destinationAccount;

    /**
     * Amount
     */
    private Double amount;

    /**
     * Currency
     */
    private String currency;
}
