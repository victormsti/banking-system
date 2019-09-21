package com.example.bankingsystem.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

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
import com.example.bankingsystem.service.CustomerService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BankingSystemApplication.class)
@AutoConfigureMockMvc
public class CustomerRestControllerTest extends AbstractTest{

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CustomerService customerService;

	Customer customer;
	Account account;
	List<Account> accounts;
	List<Customer> customers;
	String URI;

	@Test
	public void testCreateCustomer() throws Exception {
		URI = "/api/customer/create";
		customer = new Customer();
		customer.setName("Victor");
		customer.setAddress("Unknown Avenue");

		account = new Account();
		account.setAccountBalance(new BigDecimal(5000));
		account.setInterestRate(new BigDecimal(0.10));
		account.setLastAccess(new Date());
		account.setAccountType(AccountType.singleAccount);

		accounts = new ArrayList<Account>();
		accounts.add(account);

		customer.setAccounts(accounts);

		String inputInJson = mapToJson(customer);
		Mockito.when(customerService.save(Mockito.any(Customer.class))).thenReturn(customer);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();

		
		String expectedJson = this.mapToJson(customer);
		String outputInJson = result.getResponse().getContentAsString();
		assertEquals(expectedJson, outputInJson);
		assertEquals(HttpStatus.OK.value(), response.getStatus());

	}

	@Test
	public void testUpdateCustomer() throws Exception {
		URI = "/api/customer/update";
		customer = new Customer();
		customer.setId(1);
		customer.setName("New Name");
		customer.setAddress("Unknown Avenue Updated");

		account = new Account();
		account.setAccountBalance(new BigDecimal(5000));
		account.setInterestRate(new BigDecimal(0.10));
		account.setLastAccess(new Date());
		account.setAccountType(AccountType.singleAccount);

		accounts = new ArrayList<Account>();
		accounts.add(account);

		customer.setAccounts(accounts);

		String inputInJson = mapToJson(customer);
		Mockito.when(customerService.save(Mockito.any(Customer.class))).thenReturn(customer);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();

		String expectedJson = mapToJson(customers);
		String outputInJson = result.getResponse().getContentAsString();

		assertNotEquals(expectedJson, outputInJson);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
	
	@Test
	public void testFindAll() throws Exception {
		URI = "/api/customer";
		
		customers = new ArrayList<Customer>();
		customer = new Customer();
		
		customer.setId(1);
		customer.setName("Victor");
		customer.setAddress("Unknown Avenue");

		account = new Account();
		account.setAccountBalance(new BigDecimal(5000));
		account.setInterestRate(new BigDecimal(0.10));
		account.setLastAccess(new Date());
		account.setAccountType(AccountType.singleAccount);
		
		accounts = new ArrayList<Account>();
		accounts.add(account);
		customer.setAccounts(accounts);
		
		customers.add(customer);

		accounts = new ArrayList<Account>();
		customer.setId(2);
		customer.setName("Mary");
		customer.setAddress("Brazil Avenue");

		// Testing a customer with more than one account
		customer = new Customer();

		account = new Account();
		account.setAccountBalance(new BigDecimal(10000));
		account.setInterestRate(new BigDecimal(0.12));
		account.setLastAccess(new Date());
		account.setAccountType(AccountType.singleAccount);
		accounts.add(account);
		
		account = new Account();
		account.setAccountBalance(new BigDecimal(12000));
		account.setInterestRate(new BigDecimal(0.14));
		account.setLastAccess(new Date());
		account.setAccountType(AccountType.singleAccount);
		accounts.add(account);
		
		customer.setAccounts(accounts);

		Mockito.when(customerService.findAll()).thenReturn(customers);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).
				accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		
		String expectedJson = mapToJson(customers);
		String outputInJson = result.getResponse().getContentAsString();
		assertEquals(expectedJson, outputInJson);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
}
