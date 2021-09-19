package es.iobuilder.tokenizer.authservice.application.rest.input;

import lombok.Data;


/**
 * User Input Class
 */
@Data
public class UserInput {

    /**
     * User's name
     */
    private String username;

    /**
     * User's password
     */
    private String password;

    /**
     * User's email
     */
    private String email;

    /**
     * User's Phone Number
     */
    private String phone;
}

