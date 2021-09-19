package es.iobuilder.tokenizer.authservice.infrastructure.db.mappers;

import es.iobuilder.tokenizer.authservice.infrastructure.db.model.UserEntity;
import es.iobuilder.tokenizer.authservice.domain.User;

import org.mapstruct.Mapper;

/**
 * User Domain to DBO Mapper
 */
@Mapper
public interface UserMapper {

    /**
     * Converts a User DBO Object to User domain Object
     * @param userEntity User DBO Object
     * @return User
     */
    User toDomain(UserEntity userEntity);

    /**
     * Converts a User Domain Object to User DBO Object
     * @param user User Domain Object
     * @return User DBO Object
     */
    UserEntity toDbo(User user);
}
