package es.iobuilder.tokenizer.accountsservice.domain.exceptions;

/**
 * Account Not Found Exception
 */
public class AccountNotFoundException extends Exception{

    /**
     * Default Constructor
     */
    public AccountNotFoundException(){
        super("Account not found");
    }


}
