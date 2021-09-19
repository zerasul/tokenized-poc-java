package es.iobuilder.tokenizer.authservice.domain.service;

import es.iobuilder.tokenizer.authservice.domain.User;
import es.iobuilder.tokenizer.authservice.infrastructure.db.mappers.UserMapper;
import es.iobuilder.tokenizer.authservice.infrastructure.db.model.UserEntity;
import es.iobuilder.tokenizer.authservice.infrastructure.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
class UserServiceTest {

    @MockBean
    private BCryptPasswordEncoder passwordEncoder;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private UserMapper userMapper;

    @SpyBean
    private UserService userService;


    @Test
    public void testgetUserByName(){

        UserEntity entity = UserEntity.builder()
                .name("test").build();
        User user = new User();
        user.setName("test");
        Mockito.when(userRepository.findUserByName(Mockito.anyString())).thenReturn(entity);
        Mockito.when(userMapper.toDomain(Mockito.any(UserEntity.class))).thenReturn(user);
        User result = userService.getUserByName("test");
        assertNotNull(result);
        assertEquals("test",result.getName());
    }

    @Test
    public void testCreateUser(){

        Mockito.when(passwordEncoder.encode(Mockito.anyString())).thenReturn("1234");
        UserEntity userEntity = UserEntity.builder().name("test").build();
        Mockito.when(userRepository.save(Mockito.any(UserEntity.class))).thenReturn(userEntity);
        User user = new User();
        user.setName("test");
        Mockito.when(userMapper.toDomain(Mockito.any(UserEntity.class))).thenReturn(user);
        User result = userService.createUser("test","test1","aaa@aaa.com","1234");
        assertNotNull(result);
        assertEquals("test",result.getName());
    }
}