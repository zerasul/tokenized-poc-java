package es.iobuilder.tokenizer.accountsservice.application.rest.input;


import lombok.Data;

/**
 * DEposit Intput. Minimal INformation for creating a new Deposit
 */
@Data
public class DepositInput{

    /**
     * Destination Account Number
     */
    private String destinationNumber;

    /**
     * Deposit Amount
     */
    private Double amount;

    /**
     * Deposit Currency
     */
    private String currency;
}