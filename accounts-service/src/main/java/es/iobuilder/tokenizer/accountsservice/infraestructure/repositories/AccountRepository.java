package es.iobuilder.tokenizer.accountsservice.infraestructure.repositories;

import es.iobuilder.tokenizer.accountsservice.infraestructure.db.model.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


/**
 * Account Repository
 */
public interface AccountRepository extends JpaRepository<AccountEntity,Long> {

    /**
     * Find an existing account from his number
     * @param number account number
     * @return existing account or null
     */
    AccountEntity findAccountByNumber(String number);

    /**
     * Find a list of accounts from his user id
     * @param user User id
     * @return List of Accounts
     */
    List<AccountEntity> findAccountByUser(Long user);

}
