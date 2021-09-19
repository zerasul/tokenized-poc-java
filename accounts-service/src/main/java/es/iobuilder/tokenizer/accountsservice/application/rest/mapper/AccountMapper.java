package es.iobuilder.tokenizer.accountsservice.application.rest.mapper;

import es.iobuilder.tokenizer.accountsservice.application.rest.dto.AccountDTO;
import es.iobuilder.tokenizer.accountsservice.domain.Account;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Account DTO/Domain Mapper
 */
@Mapper
public interface AccountMapper {


    /**
     * Map Account DTO to Domain
     * @param accountDto Account DTO
     * @return Account DOmain
     */
    Account toDomain(AccountDTO accountDto);

    /**
     * Map Domain Account Object to DTO Account Object
     * @param account Domain Account Object
     * @return DTO Account Object
     */
    AccountDTO toDTO(Account account);

    /**
     * Map a list of Account Domain Object to a list of Account DTO
     * @param accounts list of {@link Account} Objects
     * @return list of {@link AccountDTO}
     */
    default List<AccountDTO> toDTOMap(List<Account> accounts){
        return accounts.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
