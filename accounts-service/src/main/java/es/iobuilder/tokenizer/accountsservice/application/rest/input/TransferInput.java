package es.iobuilder.tokenizer.accountsservice.application.rest.input;

import lombok.Data;


/**
 * Transfer Input. Minimal INformation for create a new transfer
 */
@Data
public class TransferInput {

    /**
     * Transfer Amount
     */
    private Double amount;

    /**
     * Origin account Number. Must be an existing account
     */
    private String originAccount;

    /**
     * Destination account Number. Must be an existing account.
     */
    private String destinationAccount;
    private String currency;

}
