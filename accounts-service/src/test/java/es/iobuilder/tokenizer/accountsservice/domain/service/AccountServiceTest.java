package es.iobuilder.tokenizer.accountsservice.domain.service;

import es.iobuilder.tokenizer.accountsservice.application.rest.dto.AccountDTO;
import es.iobuilder.tokenizer.accountsservice.domain.Account;
import es.iobuilder.tokenizer.accountsservice.domain.exceptions.AccountNotFoundException;
import es.iobuilder.tokenizer.accountsservice.infraestructure.db.mapper.AccountMapper;
import es.iobuilder.tokenizer.accountsservice.infraestructure.db.model.AccountEntity;
import es.iobuilder.tokenizer.accountsservice.infraestructure.repositories.AccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class AccountServiceTest {

    @MockBean
    private AccountRepository accountRepository;

    @MockBean
    private AccountMapper accountMapper;

    @SpyBean
    private AccountService accountService;

    @Test
    public void testgetAccountFromuser(){

        AccountEntity entity = AccountEntity.builder().name("test").build();

        Mockito.when(accountRepository.findAccountByUser(Mockito.anyLong())).thenReturn(Collections.singletonList(entity));
        Account a = new Account();
        a.setName("test");
        Mockito.when(accountMapper.toDomain(Mockito.any(AccountEntity.class))).thenReturn(a);
        List<Account> result=accountService.getAccountsFromUser(1L);
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals("test",result.get(0).getName());
    }

    @Test
    public void testgetAccountFromNumber()throws Exception{

        AccountEntity entity = AccountEntity.builder().name("test").build();

        Mockito.when(accountRepository.findAccountByNumber(Mockito.anyString())).thenReturn(entity);
        Account a = new Account();
        a.setName("test");
        Mockito.when(accountMapper.toDomain(Mockito.any(AccountEntity.class))).thenReturn(a);
        Account result=accountService.getAccountByNumber("1111");
        assertNotNull(result);
        assertEquals("test",result.getName());
    }

    @Test
    public void testgetAccountFromNumberNull()throws Exception{

        AccountEntity entity = AccountEntity.builder().name("test").build();

        Mockito.when(accountRepository.findAccountByNumber(Mockito.anyString())).thenReturn(null);
        Account a = new Account();
        a.setName("test");
        Mockito.when(accountMapper.toDomain(Mockito.any(AccountEntity.class))).thenReturn(a);
        assertThrows(AccountNotFoundException.class,()->{
            accountService.getAccountByNumber("1111");

        });

    }

    @Test
    public void testCreateAccount(){
        AccountEntity entity = AccountEntity.builder().name("Test").build();
        Account a = new Account();
        a.setName("Test");
        Mockito.when(accountRepository.save(Mockito.any(AccountEntity.class))).thenReturn(entity);
        Mockito.when(accountMapper.toDomain(Mockito.any(AccountEntity.class))).thenReturn(a);
        Account result = accountService.createAccount("test","11111",1L);
        assertNotNull(result);
        assertEquals("Test",result.getName());
    }

    @Test
    public void testUpdateAccount()throws Exception{
        AccountEntity entity = AccountEntity.builder().name("Test").build();
        Account a = new Account();
        a.setName("Test");
        Mockito.when(accountRepository.findAccountByNumber(Mockito.anyString())).thenReturn(entity);
        Mockito.when(accountRepository.save(Mockito.any(AccountEntity.class))).thenReturn(entity);
        Mockito.when(accountMapper.toDomain(Mockito.any(AccountEntity.class))).thenReturn(a);
        Account result = accountService.updateAccount("test","11111",1L);
        assertNotNull(result);
        assertEquals("Test",result.getName());
    }

    @Test
    public void testUpdateAccountNUllName()throws Exception{
        AccountEntity entity = AccountEntity.builder().name("Test").build();
        Account a = new Account();
        a.setNumber("1111");
        Mockito.when(accountRepository.findAccountByNumber(Mockito.anyString())).thenReturn(entity);
        Mockito.when(accountRepository.save(Mockito.any(AccountEntity.class))).thenReturn(entity);
        Mockito.when(accountMapper.toDomain(Mockito.any(AccountEntity.class))).thenReturn(a);
        Account result = accountService.updateAccount(null,"11111",1L);
        assertNotNull(result);
        assertEquals("1111",result.getNumber());
    }



    @Test
    public void testUpdateAccountThrowException()throws Exception{
        AccountEntity entity = AccountEntity.builder().name("Test").build();
        Account a = new Account();
        a.setName("Test");
        Mockito.when(accountRepository.findAccountByNumber(Mockito.anyString())).thenReturn(null);
        Mockito.when(accountRepository.save(Mockito.any(AccountEntity.class))).thenReturn(entity);
        Mockito.when(accountMapper.toDomain(Mockito.any(AccountEntity.class))).thenReturn(a);
        assertThrows(AccountNotFoundException.class,()->{
            accountService.updateAccount("test","111",1L);

        });
    }

    @Test
    public void testDeleteAccount()throws Exception{
        AccountEntity entity = AccountEntity.builder().name("Test").build();
        Mockito.when(accountRepository.findAccountByNumber(Mockito.anyString())).thenReturn(entity);

        accountService.deleteAccount("11111");
    }

    @Test
    public void testDeleteAccountThrowException()throws Exception{
        AccountEntity entity = AccountEntity.builder().name("Test").build();
        Mockito.when(accountRepository.findAccountByNumber(Mockito.anyString())).thenReturn(null);

        assertThrows(AccountNotFoundException.class,()->{
            accountService.deleteAccount("1111");

        });
    }

}