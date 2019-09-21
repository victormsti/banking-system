package com.example.bankingsystem.controller;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.bankingsystem.AbstractTest;
import com.example.bankingsystem.BankingSystemApplication;
import com.example.bankingsystem.entity.Account;
import com.example.bankingsystem.entity.AccountType;
import com.example.bankingsystem.entity.Customer;
import com.example.bankingsystem.service.AccountService;
import com.example.bankingsystem.service.CustomerService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BankingSystemApplication.class)
@AutoConfigureMockMvc
public class AccountRestControllerTest extends AbstractTest{

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AccountService accountService;
	
	String URI;
	Account account;
	List<Account> accounts;
	
	@Test
	public void testCreateSingleAccount() throws Exception {
		URI = "/api/account/single_account/create";

		account = new Account();
		account.setAccountBalance(new BigDecimal(5000));
		account.setInterestRate(new BigDecimal(0.10));
		account.setLastAccess(new Date());
		account.setAccountType(AccountType.singleAccount);

		accounts = new ArrayList<Account>();
		accounts.add(account);

		String inputInJson = mapToJson(account);
		Mockito.when(accountService.save(Mockito.any(Account.class))).thenReturn(account);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URI).
				accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());

	}
	
	@Test
	public void testCreateJointAccount() throws Exception {
		URI = "/api/account/joint_account/create";
		
		accounts = new ArrayList<Account>();
		
		account = new Account();
		account.setAccountBalance(new BigDecimal(5000));
		account.setInterestRate(new BigDecimal(0.10));
		account.setLastAccess(new Date());
		account.setAccountType(AccountType.jointAccount);
		accounts.add(account);
		
		account = new Account();
		account.setAccountBalance(new BigDecimal(5000));
		account.setInterestRate(new BigDecimal(0.10));
		account.setLastAccess(new Date());
		account.setAccountType(AccountType.jointAccount);
		accounts.add(account);

		String inputInJson = mapToJson(accounts);
		Mockito.when(accountService.save(Mockito.any(Account.class))).thenReturn(account);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URI).
				accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());

	}
	
	@Test
	public void testFindAll() throws Exception {
		URI = "/api/account";
		
		accounts = new ArrayList<Account>();
		account = new Account();
		account.setAccountBalance(new BigDecimal(5000));
		account.setInterestRate(new BigDecimal(0.10));
		account.setLastAccess(new Date());
		account.setAccountType(AccountType.singleAccount);
		
		accounts = new ArrayList<Account>();
		accounts.add(account);
		
		account = new Account();
		account.setAccountBalance(new BigDecimal(10000));
		account.setInterestRate(new BigDecimal(0.12));
		account.setLastAccess(new Date());
		account.setAccountType(AccountType.jointAccount);
		accounts.add(account);
		
		account = new Account();
		account.setAccountBalance(new BigDecimal(12000));
		account.setInterestRate(new BigDecimal(0.14));
		account.setLastAccess(new Date());
		account.setAccountType(AccountType.singleAccount);
		accounts.add(account);

		Mockito.when(accountService.findAll()).thenReturn(accounts);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).
				accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

}
