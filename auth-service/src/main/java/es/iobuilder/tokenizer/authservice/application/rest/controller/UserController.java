package es.iobuilder.tokenizer.authservice.application.rest.controller;

import es.iobuilder.tokenizer.authservice.application.rest.dto.UserDTO;
import es.iobuilder.tokenizer.authservice.application.rest.input.UserInput;
import es.iobuilder.tokenizer.authservice.application.rest.mappers.UserMapper;
import es.iobuilder.tokenizer.authservice.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * User Rest Controller. This is the main entrypoint for creating users.
 */
@RestController
@RequiredArgsConstructor
public class UserController {

    /**
     * User Service
     */
    private UserService userService;

    /**
     * User mapper dto to domain
     */

    private UserMapper useradapterMapper;



    @Autowired
    public UserController(UserService userService,UserMapper userMapper){
        this.userService=userService;
        this.useradapterMapper=userMapper;
    }


    /**
     * Create a new User
     * @param userInput {@link UserInput} object for create a new User
     * @return a new Created User with Http Code 204.
     */
    @PostMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
    public ResponseEntity<UserDTO> registerUser(@RequestBody UserInput userInput){

        return new ResponseEntity<>(useradapterMapper.toDto(userService.createUser(userInput.getUsername(),
                userInput.getPassword(),
                userInput.getEmail(),
                userInput.getPhone())), HttpStatus.CREATED);
    }
}
