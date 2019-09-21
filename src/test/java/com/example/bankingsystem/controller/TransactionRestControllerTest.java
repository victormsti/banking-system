package com.example.bankingsystem.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

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
import com.example.bankingsystem.entity.Transaction;
import com.example.bankingsystem.entity.TransactionType;
import com.example.bankingsystem.service.CustomerService;
import com.example.bankingsystem.service.TransactionService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BankingSystemApplication.class)
@AutoConfigureMockMvc
public class TransactionRestControllerTest extends AbstractTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	TransactionService transactionService;

	Account account;
	Transaction transaction;
	List<Transaction> transactions;
	String URI;

	@Test
	public void testSavingTransaction() throws Exception {
		URI = "/api/transaction/saving/create";

		BigDecimal value = new BigDecimal(1000);

		account = new Account();
		account.setAccountBalance(new BigDecimal(5000));
		account.setInterestRate(new BigDecimal(0.10));
		account.setLastAccess(new Date());
		account.setAccountType(AccountType.singleAccount);
		// Applying the transaction
		account.setAccountBalance(account.getAccountBalance().subtract(value));

		transaction = new Transaction();
		transaction.setAccountOrigin(account);
		transaction.setAccountDestiny(account);
		transaction.setTransactionType(TransactionType.saving);
		transaction.setValue(value);
		transaction.setTransactionDate(new Date());

		String inputInJson = mapToJson(transaction);
		Mockito.when(transactionService.save(Mockito.any(Transaction.class))).thenReturn(transaction);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	@Test
	public void testTransferTransaction() throws Exception {
		URI = "/api/transaction/transfer/create";

		BigDecimal value = new BigDecimal(1000);
		transaction = new Transaction();
		account = new Account();
		account.setAccountBalance(new BigDecimal(5000));
		account.setInterestRate(new BigDecimal(0.10));
		account.setLastAccess(new Date());
		account.setAccountType(AccountType.singleAccount);
		// Applying the transaction value
		account.setAccountBalance(account.getAccountBalance().subtract(value));
		transaction.setAccountOrigin(account);
		
		account = new Account();
		account.setAccountBalance(new BigDecimal(5000));
		account.setInterestRate(new BigDecimal(0.10));
		account.setLastAccess(new Date());
		account.setAccountType(AccountType.singleAccount);
		// Applying the transaction value
		account.setAccountBalance(account.getAccountBalance().add(value));
		transaction.setAccountDestiny(account);

		transaction.setTransactionType(TransactionType.transfer);
		transaction.setValue(value);
		transaction.setTransactionDate(new Date());

		String inputInJson = mapToJson(transaction);
		Mockito.when(transactionService.save(Mockito.any(Transaction.class))).thenReturn(transaction);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
//
//	@Test
//	public void testFindAll() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testCheckTransaction() {
//		fail("Not yet implemented");
//	}

}
