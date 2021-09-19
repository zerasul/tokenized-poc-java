package es.iobuilder.tokenizer.accountsservice.infraestructure.repositories;

import es.iobuilder.tokenizer.accountsservice.infraestructure.db.model.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface AccountRepository extends JpaRepository<AccountEntity,Long> {

    AccountEntity findAccountByNumber(String number);
    List<AccountEntity> findByuser(Long user);

}
