package es.iobuilder.tokenizer.authservice.application.security.service;

import es.iobuilder.tokenizer.authservice.domain.User;
import es.iobuilder.tokenizer.authservice.domain.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class AuthUserDetailServiceTest {

    @MockBean
    private UserService userService;

    @SpyBean
    private AuthUserDetailService authUserDetailService;

    @Test
    public void testLoadUserByUserName(){

        User user =new User();
        user.setName("test");

        Mockito.when(userService.getUserByName(Mockito.anyString())).thenReturn(user);

        UserDetails result=authUserDetailService.loadUserByUsername("test");

        assertNotNull(result);
        assertEquals("test", result.getUsername());
    }

    @Test
    public void testLoadUserNull(){
        User user =new User();
        user.setName("test");

        Mockito.when(userService.getUserByName(Mockito.anyString())).thenReturn(null);

        assertThrows(UsernameNotFoundException.class, () ->{
            UserDetails result=authUserDetailService.loadUserByUsername("test");
        });

    }

}