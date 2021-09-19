package es.iobuilder.tokenizer.accountsservice.domain.service;


import es.iobuilder.tokenizer.accountsservice.domain.Account;
import es.iobuilder.tokenizer.accountsservice.domain.exceptions.AccountNotFoundException;
import es.iobuilder.tokenizer.accountsservice.infraestructure.db.mapper.AccountMapper;
import es.iobuilder.tokenizer.accountsservice.infraestructure.db.model.AccountEntity;
import es.iobuilder.tokenizer.accountsservice.infraestructure.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


/**
 * Account Service. This class allow the operations with the Account Entity.
 */
@Service
public class AccountService {

    /**
     * Account Repository
     */
    private AccountRepository accountRepository;

    /**
     * Account Mapper. DTO/Domain Mapper
     */
    private AccountMapper accountMapper;

    @Autowired
    public AccountService(AccountRepository accountRepository, AccountMapper accountMapper){
        this.accountMapper=accountMapper;
        this.accountRepository=accountRepository;
    }

    /**
     * Get Account From User
     * @param user User Id
     * @return Get the list of accounts from one user
     */
    public List<Account> getAccountsFromUser(Long user){

        List<AccountEntity> accountList = accountRepository.findByuser(user);

        //ONly for demostration pruposes; we can implement a new Mapper.

        return accountList.stream().map(accountEntity -> accountMapper.toDomain(accountEntity)).collect(Collectors.toList());
    }

    /**
     * Get an existing Account from his number
     * @param number Account Number
     * @return Existing Account
     * @throws AccountNotFoundException throws this exception if there is no account according to this number.
     */
    public Account getAccountByNumber(String number)throws AccountNotFoundException{

        return accountMapper.toDomain(getAccountEntityByNumber(number));

    }

    /**
     * Get an existing Account from his number
     * @param number Account Number
     * @return Existing Account
     * @throws AccountNotFoundException throws this exception if there is no account according to this number.
     */
    private AccountEntity getAccountEntityByNumber(String number) throws AccountNotFoundException{
        AccountEntity accountEntity= accountRepository.findAccountByNumber(number);
        if(Objects.isNull(accountEntity)){
            throw new AccountNotFoundException();
        }

        return accountEntity;
    }

    /**
     * Creates a new Account
     * @param name account name
     * @param number account number
     * @param user account User.
     * @return Created Account Object
     */
    public Account createAccount(String name, String number,Long user){

        AccountEntity entity = AccountEntity.builder().name(name)
                .number(number)
                .user(user)
                .created(LocalDate.now())
                .build();
        AccountEntity savedEntity = accountRepository.save(entity);

        return accountMapper.toDomain(savedEntity);
    }

    /**
     * Update an existing account
     * @param name Account name
     * @param number account Number
     * @param User account User
     * @return updated Account
     * @throws AccountNotFoundException throws this exceptions if the account number doesn't exists.
     */
    public Account updateAccount(String name,String number, Long User) throws AccountNotFoundException {

         AccountEntity entity= getAccountEntityByNumber(number);

         if(Objects.nonNull(name)){
             entity.setName(name);
         }

         if(Objects.nonNull(User)){
             entity.setUser(User);
         }
         return accountMapper.toDomain(accountRepository.save(entity));
    }

    /**
     * Delete an existing account
     * @param number Existing account number
     * @throws AccountNotFoundException throws this exception if the account number doesn't exists.
     */
    public void deleteAccount(String number)throws AccountNotFoundException{

        AccountEntity entity = getAccountEntityByNumber(number);
        //This is only as demostration pruposes...
        //IN a real app; we can make a logical delete.
        accountRepository.delete(entity);
    }
}
