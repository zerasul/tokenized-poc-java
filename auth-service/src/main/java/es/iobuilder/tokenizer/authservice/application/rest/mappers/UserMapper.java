package es.iobuilder.tokenizer.authservice.application.rest.mappers;

import es.iobuilder.tokenizer.authservice.application.rest.dto.UserDTO;
import es.iobuilder.tokenizer.authservice.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;


/**
 * User Mapper. Convert DTO objects to Domain Object
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    /**
     * Convert Domain User Object to DTO User Object
     * @param user Domain user Object
     * @return DTO User Object
     */
    UserDTO toDto(User user);

    /**
     * Convcert DTO User Object to Domain Object
     * @param userDto DTO User Object
     * @return Domain user Object
     */
    User toDomain(UserDTO userDto);
}
