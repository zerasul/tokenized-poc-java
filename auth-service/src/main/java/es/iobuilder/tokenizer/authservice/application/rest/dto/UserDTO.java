package es.iobuilder.tokenizer.authservice.application.rest.dto;

import lombok.Data;

/**
 * User DTO class
 */
@Data
public class UserDTO {

    /**
     * Id
     */
    private Long id;

    /**
     * User name
     */
    private String name;


    /**
     * email
     */
    private String email;

    /**
     * User Phone Number
     */
    private String phone;

}
