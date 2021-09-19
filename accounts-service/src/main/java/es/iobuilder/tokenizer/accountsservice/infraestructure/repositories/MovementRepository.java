package es.iobuilder.tokenizer.accountsservice.infraestructure.repositories;

import es.iobuilder.tokenizer.accountsservice.infraestructure.db.model.MovementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface MovementRepository extends JpaRepository<MovementEntity, Long> {

    Optional<MovementEntity> findById(Long id);
}
