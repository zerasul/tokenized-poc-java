package es.iobuilder.tokenizer.authservice.infrastructure.repositories;

import es.iobuilder.tokenizer.authservice.infrastructure.db.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * User Repository. Allows DAO Operations for User Entity.
 */
public interface UserRepository extends JpaRepository<UserEntity, Long> {


    /**
     * Find a User from his user's name
     * @param name user's name
     * @return User DBO Entity or null.
     */
    UserEntity findUserByName(String name);
}
