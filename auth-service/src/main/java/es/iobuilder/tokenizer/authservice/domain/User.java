package es.iobuilder.tokenizer.authservice.domain;

import lombok.Data;

/**
 * User Domain Class
 */
@Data
public class User {

    /**
     * ID
     */
    private Long id;

    /**
     * User Name
     */
    private String name;

    /**
     * User Encrypted Password
     */
    private String password;

    /**
     * User's Email
     */
    private String email;

    /**
     * User's Phone
     */
    private String phone;

}
