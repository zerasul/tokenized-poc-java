package es.iobuilder.tokenizer.accountsservice.application.rest.input;

import lombok.Data;

/**
 * Account Input class with the minimal information for create a new Account
 */
@Data
public class AccountInput {

    /**
     * Account name
     */
    private String name;


    /**
     * Account Associated User
     */
    private Long user;
}
