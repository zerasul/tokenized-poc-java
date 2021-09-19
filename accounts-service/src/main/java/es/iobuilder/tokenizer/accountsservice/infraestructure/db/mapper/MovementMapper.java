package es.iobuilder.tokenizer.accountsservice.infraestructure.db.mapper;

import es.iobuilder.tokenizer.accountsservice.domain.Movement;
import es.iobuilder.tokenizer.accountsservice.infraestructure.db.model.MovementEntity;
import org.mapstruct.Mapper;

/**
 * Movement Mapper. DBO/Domain Mapper
 */
@Mapper
public interface MovementMapper {

    /**
     * DBO to Domain Mapper
     * @param entity DBO OBject
     * @return Domain OBject
     */
    Movement toDomain(MovementEntity entity);

    /**
     * Domain to DBO Mapper
     * @param domain Domain OBject
     * @return DBO OBject
     */
    MovementEntity toDBO(Movement domain);
}
