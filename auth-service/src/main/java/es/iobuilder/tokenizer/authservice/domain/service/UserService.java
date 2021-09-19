package es.iobuilder.tokenizer.authservice.domain.service;

import es.iobuilder.tokenizer.authservice.domain.User;
import es.iobuilder.tokenizer.authservice.infrastructure.db.mappers.UserMapper;
import es.iobuilder.tokenizer.authservice.infrastructure.db.model.UserEntity;
import es.iobuilder.tokenizer.authservice.infrastructure.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


/**
 * Domain User Service. This is a domain class and have all the methods for get and create a new User
 */
@Service
public class UserService {

    /**
     * Password Encrypter
     */
    private BCryptPasswordEncoder passwordEncoder;

    /**
     * User Repository
     */
    private UserRepository userRepository;

    /**
     * User domain to DBO user Mapper
     */
    private UserMapper userMapper;


    @Autowired
    public UserService(BCryptPasswordEncoder passwordEncoder, UserRepository userRepository,UserMapper userMapper){
        this.passwordEncoder=passwordEncoder;
        this.userRepository=userRepository;
        this.userMapper=userMapper;
    }

    /**
     * Get a User from his Username
     * @param username Username
     * @return A valid user or null if there is not an user with this username
     */
    public User getUserByName(String username){

        UserEntity user =userRepository.findUserByName(username);

        return userMapper.toDomain(user);
    }

    /**
     * Creates a new User
     * @param username Username
     * @param password plain password. This will be encrypted
     * @param email User's Email
     * @param phone User's Phone
     * @return A valid Domain User.
     */
    public User createUser(String username, String password, String email, String phone){

        //Create a User DBO Entity. The password is encrypted
        UserEntity entity= UserEntity.builder()
                .name(username)
                .password(passwordEncoder.encode(password))
                .email(email)
                .phone(phone)
                .build();

        // Save in database a new user
        UserEntity savedEntity=userRepository.save(entity);

        // Mapping User DBO Object to User Domain Object
        return userMapper.toDomain(savedEntity);
    }
}
