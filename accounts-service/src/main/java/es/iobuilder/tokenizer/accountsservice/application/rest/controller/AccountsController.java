package es.iobuilder.tokenizer.accountsservice.application.rest.controller;

import es.iobuilder.tokenizer.accountsservice.application.rest.dto.AccountDTO;
import es.iobuilder.tokenizer.accountsservice.application.rest.input.AccountInput;
import es.iobuilder.tokenizer.accountsservice.application.rest.mapper.AccountMapper;
import es.iobuilder.tokenizer.accountsservice.domain.Account;
import es.iobuilder.tokenizer.accountsservice.domain.exceptions.AccountNotFoundException;
import es.iobuilder.tokenizer.accountsservice.domain.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Accounts Controller. This is the main entrypoint for the account Entity.
 */
@RestController
public class AccountsController {

    /**
     * Account Service
     */
    private AccountService accountService;

    /**
     * Account Mapper. DTO to Domain.
     */
    private AccountMapper accountMapper;

    /**
     * Constructor
     * @param accountService account Service
     * @param accountsMapper account DTO/domain mapper.
     */
    @Autowired
    public AccountsController(AccountService accountService, AccountMapper accountsMapper){
        this.accountService=accountService;
        this.accountMapper=accountsMapper;
    }

    /**
     * Get all the user's accounts
     * @param userId User Id
     * @return List of {@link AccountDTO} with all the user's accounts.
     */
    @GetMapping("/accounts/user/{userId}")
    public List<AccountDTO> getUserAccounts(@PathVariable Long userId) {

        List<Account> accounts = accountService.getAccountsFromUser(userId);
        return accountMapper.toDTOMap(accounts);
    }

    /**
     * Get an account from his number.
     * @param accountNumber account number
     * @return The Account information
     */
    @GetMapping(value = "/accounts/{number}")
    public ResponseEntity<AccountDTO> getAccount(@PathVariable("number") String accountNumber) {


        try {
            return new ResponseEntity<>(accountMapper.toDTO(accountService.getAccountByNumber(accountNumber)),
                    HttpStatus.OK);
        } catch (AccountNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Creates a new Account
     * @param input {@link AccountInput} Object
     * @return AccountDTO Object
     */
    @PostMapping(value = "/accounts/")
    public ResponseEntity<AccountDTO> createAccount(@RequestBody AccountInput input) {

        //Only for demostration purposes; the validation can be done with Auxiliary Classes or using annotations.
        if (Objects.isNull(input)) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        if (Objects.isNull(input.getName())) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        if (Objects.isNull(input.getUser())) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        //We use a generated random UUID as account Number; this is only for demostration pruposes.
        return new ResponseEntity<>(accountMapper.toDTO(accountService.createAccount(input.getName(), UUID.randomUUID().toString(), input.getUser())),
                HttpStatus.CREATED);


    }

    /**
     * Updates a Existint Account
     * @param input {@link AccountInput} Information
     * @param accountNumber account Number
     * @return Updated Account or not found if dosent exists
     */
    @PutMapping(value = "/accounts/{number}")
    public ResponseEntity<AccountDTO> updateAccount(@RequestBody AccountInput input, @PathVariable("number") String accountNumber) {

        //Only for demostration purposes; the validation can be done with Auxiliary Classes or using annotations.
        if (Objects.isNull(input)) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        Account account = null;
        try {
            account = accountService.getAccountByNumber(accountNumber);
            if (Objects.nonNull(input.getName())) {
                account.setName(input.getName());
            }

            if (Objects.isNull(input.getUser())) {
                account.setUser(input.getUser());
            }
            Account updatedAccount = accountService.updateAccount(account.getName(), accountNumber, account.getUser());

            return new ResponseEntity<>(accountMapper.toDTO(updatedAccount),HttpStatus.OK);
        } catch (AccountNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Deletes an existing account
     * @param accountNumber account number
     * @return OK or Not found if the account dosent exists.
     */
    @DeleteMapping("/accounts/{number}")
    public ResponseEntity<String> deleteAccount(@PathVariable("number") String accountNumber) {

        try {
            accountService.deleteAccount(accountNumber);
            return new ResponseEntity<>("OK",HttpStatus.OK);
        } catch (AccountNotFoundException e) {
           return new ResponseEntity<>("NOT_FOUND", HttpStatus.NOT_FOUND);
        }
    }
}
