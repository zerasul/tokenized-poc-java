package es.iobuilder.tokenizer.accountsservice.application.rest.mapper;

import es.iobuilder.tokenizer.accountsservice.application.rest.dto.MovementDTO;
import es.iobuilder.tokenizer.accountsservice.domain.Movement;
import org.mapstruct.Mapper;

/**
 *  Movement DTO/Domain Mapper
 */
@Mapper
public interface MovementMapper {

    /**
     * Map Movement DTO to Movement Domain Object
     * @param dto Movement DTO OBject
     * @return Movement Domain Object
     */
    Movement toDomain(MovementDTO dto);

    /**
     * Map Movement DOmain Object to Movement DTO.
     * @param domain MovementDomain Object
     * @return Movement DTO Object
     */
    MovementDTO toDTO(Movement domain);
}
