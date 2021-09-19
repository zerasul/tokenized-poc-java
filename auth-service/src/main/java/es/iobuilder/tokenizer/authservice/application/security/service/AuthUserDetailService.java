package es.iobuilder.tokenizer.authservice.application.security.service;

import es.iobuilder.tokenizer.authservice.application.security.model.UserDetail;
import es.iobuilder.tokenizer.authservice.domain.User;
import es.iobuilder.tokenizer.authservice.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Objects;

/**
 * This class allows to search a user for the authorization process.
 *
 * This is a Security Domain class; and is used by Spring Security Oauth Process for check a valid User
 */
@RequiredArgsConstructor
public class AuthUserDetailService implements UserDetailsService {

    /**
     * Domain User service for check the username
     */
    private final UserService userService;



    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        //Check the user name
        User user= userService.getUserByName(s);
        //If the user is null there is no a valid user.
        // The password verification is done by Spring security using BcrypPasswordEncoding process.
        if(Objects.isNull(user)){
            throw new UsernameNotFoundException("User not found");
        }
        return new UserDetail(user);
    }
}
