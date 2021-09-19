package es.iobuilder.tokenizer.accountsservice.domain.exceptions;


/**
 * Movement Not found Exception
 */
public class MovementNotFoundException  extends Exception{

    /**
     * Default Constructor
     */
    public MovementNotFoundException(){
        super("Not Found");
    }
}
