package es.iobuilder.tokenizer.authservice.application.rest.controller;

import es.iobuilder.tokenizer.authservice.application.rest.dto.UserDTO;
import es.iobuilder.tokenizer.authservice.application.rest.input.UserInput;
import es.iobuilder.tokenizer.authservice.application.rest.mappers.UserMapper;
import es.iobuilder.tokenizer.authservice.domain.User;
import es.iobuilder.tokenizer.authservice.domain.service.UserService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class UserControllerTest {

    @MockBean
    private UserService userService;

    @MockBean
    private UserMapper useradapterMapper;

    @SpyBean
    private UserController userController;




    @Test
    public void testCreateUser(){

        UserInput userInput = new UserInput();
        userInput.setUsername("test");
        userInput.setEmail("email1");
        userInput.setPassword("test1");
        userInput.setPhone("+34622222222");

        User user= new User();
        user.setName("test");

        UserDTO userDTO = new UserDTO();
        userDTO.setName("test");

        Mockito.when(useradapterMapper.toDto(Mockito.any(User.class))).thenReturn(userDTO);
        Mockito.when(userService.createUser(Mockito.anyString(),Mockito.anyString(),Mockito.anyString(),Mockito.anyString())).thenReturn(user);
        ResponseEntity<UserDTO> result = this.userController.registerUser(userInput);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(HttpStatus.CREATED,result.getStatusCode());
        Assertions.assertNotNull(result.getBody());
        Assertions.assertEquals("test",result.getBody().getName());
    }
}