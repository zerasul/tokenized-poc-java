package es.iobuilder.tokenizer.accountsservice.application.rest.controller;

import es.iobuilder.tokenizer.accountsservice.application.rest.dto.AccountDTO;
import es.iobuilder.tokenizer.accountsservice.application.rest.input.AccountInput;
import es.iobuilder.tokenizer.accountsservice.application.rest.mapper.AccountMapper;
import es.iobuilder.tokenizer.accountsservice.domain.Account;
import es.iobuilder.tokenizer.accountsservice.domain.exceptions.AccountNotFoundException;
import es.iobuilder.tokenizer.accountsservice.domain.service.AccountService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class AccountsControllerTest {

    @MockBean
    private AccountService accountService;

    @MockBean
    private AccountMapper accountMapper;

    @SpyBean
    private AccountsController accountsController;

    @Test
    public void testgetUserAccounts(){

        Account a = new Account();
        a.setName("test");
        AccountDTO dto = new AccountDTO();
        dto.setName("test");
        Mockito.when(accountService.getAccountsFromUser(Mockito.anyLong())).thenReturn(Collections.singletonList(a));
        Mockito.when(accountMapper.toDTOMap(Mockito.anyList())).thenReturn(Collections.singletonList(dto));
       List<AccountDTO> result=accountsController.getUserAccounts(1L);
       assertNotNull(result);
       assertFalse(result.isEmpty());
       assertEquals(1,result.size());
       assertEquals("test",result.get(0).getName());
    }

    @Test
    public void testgetAccount()throws Exception{
        Account a = new Account();
        a.setName("test");
        AccountDTO dto = new AccountDTO();
        dto.setName("test");
        Mockito.when(accountService.getAccountByNumber(Mockito.anyString())).thenReturn(a);
        Mockito.when(accountMapper.toDTO(Mockito.any(Account.class))).thenReturn(dto);
        ResponseEntity<AccountDTO> result=accountsController.getAccount("11111");
        assertNotNull(result);
        assertEquals(200,result.getStatusCodeValue());
        assertNotNull(result.getBody());
        assertEquals("test",result.getBody().getName());
    }

    @Test
    public void testgetAccount404()throws Exception{
        Account a = new Account();
        a.setName("test");
        AccountDTO dto = new AccountDTO();
        dto.setName("test");
        Mockito.when(accountService.getAccountByNumber(Mockito.anyString())).thenThrow(AccountNotFoundException.class);
        Mockito.when(accountMapper.toDTO(Mockito.any(Account.class))).thenReturn(dto);
        ResponseEntity<AccountDTO> result=accountsController.getAccount("11111");
        assertNotNull(result);
        assertEquals(404,result.getStatusCodeValue());
        assertNull(result.getBody());
    }

    @Test
    public void testCreateAccountNullInput(){

        ResponseEntity<AccountDTO> result = accountsController.createAccount(null);
        assertNotNull(result);
        assertEquals(400,result.getStatusCodeValue());
        assertNull(result.getBody());
    }

    @Test
    public void testCreateAccountNullName(){
        AccountInput input = new AccountInput();
        input.setUser(1L);
        ResponseEntity<AccountDTO> result = accountsController.createAccount(input);
        assertNotNull(result);
        assertEquals(400,result.getStatusCodeValue());
        assertNull(result.getBody());
    }

    @Test
    public void testCreateAccountNullUser(){
        AccountInput input = new AccountInput();
        input.setName("test");
        ResponseEntity<AccountDTO> result = accountsController.createAccount(input);
        assertNotNull(result);
        assertEquals(400,result.getStatusCodeValue());
        assertNull(result.getBody());
    }

    @Test
    public void testCreateAccount(){
        AccountInput input = new AccountInput();
        input.setName("test");
        input.setUser(1L);
        Account a = new Account();
        a.setName("test");
        Mockito.when(accountService.createAccount(Mockito.anyString(),Mockito.anyString(),Mockito.anyLong())).thenReturn(a);
        AccountDTO dto = new AccountDTO();
        dto.setName("test");
        Mockito.when(accountMapper.toDTO(Mockito.any(Account.class))).thenReturn(dto);
        ResponseEntity<AccountDTO> result = accountsController.createAccount(input);
        assertNotNull(result);
        assertEquals(201,result.getStatusCodeValue());
        assertNotNull(result.getBody());
        assertEquals("test",result.getBody().getName());
    }

    @Test
    public void testUpdateAccountNullInput(){

        ResponseEntity<AccountDTO> result = accountsController.updateAccount(null,"1111");
        assertNotNull(result);
        assertNull(result.getBody());
        assertEquals(400, result.getStatusCodeValue());
    }

    @Test
    public void testUpdateAccountnullName()throws Exception{
        AccountInput input= new AccountInput();
        input.setUser(1L);
        Account a = new Account();
        a.setName("test");
        Mockito.when(accountService.getAccountByNumber(Mockito.anyString())).thenReturn(a);
        AccountDTO dto = new AccountDTO();
        dto.setName("test");
        Mockito.when(accountMapper.toDTO(Mockito.any(Account.class))).thenReturn(dto);
        Mockito.when(accountService.updateAccount(Mockito.anyString(),Mockito.anyString(),Mockito.anyLong())).thenReturn(a);
        ResponseEntity<AccountDTO> result = accountsController.updateAccount(input,"11111");
        assertNotNull(result);
        assertEquals(200,result.getStatusCodeValue());

    }

    @Test
    public void testUpdateAccountnullUser()throws Exception{
        AccountInput input= new AccountInput();
        input.setName("test");
        Account a = new Account();
        a.setName("test");
        Mockito.when(accountService.getAccountByNumber(Mockito.anyString())).thenReturn(a);
        AccountDTO dto = new AccountDTO();
        dto.setName("test");
        Mockito.when(accountMapper.toDTO(Mockito.any(Account.class))).thenReturn(dto);
        Mockito.when(accountService.updateAccount(Mockito.anyString(),Mockito.anyString(),Mockito.anyLong())).thenReturn(a);
        ResponseEntity<AccountDTO> result = accountsController.updateAccount(input,"11111");
        assertNotNull(result);
        assertEquals(200,result.getStatusCodeValue());

    }

    @Test
    public void testUpdateAccountnotExistingAccount()throws Exception{
        AccountInput input= new AccountInput();
        input.setName("test");
        Account a = new Account();
        a.setName("test");
        Mockito.when(accountService.getAccountByNumber(Mockito.anyString())).thenThrow(AccountNotFoundException.class);
        AccountDTO dto = new AccountDTO();
        dto.setName("test");
        Mockito.when(accountMapper.toDTO(Mockito.any(Account.class))).thenReturn(dto);
        Mockito.when(accountService.updateAccount(Mockito.anyString(),Mockito.anyString(),Mockito.anyLong())).thenReturn(a);
        ResponseEntity<AccountDTO> result = accountsController.updateAccount(input,"11111");
        assertNotNull(result);
        assertEquals(404,result.getStatusCodeValue());

    }

    @Test
    public void testDeleteAccount()throws Exception{

        ResponseEntity<String> result = accountsController.deleteAccount("1111");
        Mockito.verify(accountService).deleteAccount(Mockito.anyString());
        assertNotNull(result);
        assertEquals(200,result.getStatusCodeValue());

    }
}