package es.iobuilder.tokenizer.accountsservice.infraestructure.db.mapper;

import es.iobuilder.tokenizer.accountsservice.domain.Account;
import es.iobuilder.tokenizer.accountsservice.infraestructure.db.model.AccountEntity;
import org.mapstruct.Mapper;

/**
 * DBO to Domain Mapper
 */
@Mapper
public interface AccountMapper {

    /**
     * DBO Object to DOmain
     * @param accountDbo
     * @return
     */
    Account toDomain(AccountEntity accountDbo);

    /**
     * Domain to DBO Map
     * @param account Account DOmain OBject
     * @return Account DBO Object
     */
    AccountEntity toDBO(Account account);
}
