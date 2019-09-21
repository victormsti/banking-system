package com.example.bankingsystem;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runner.notification.RunListener;
import org.junit.runners.MethodSorters;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.bankingsystem.dao.CustomerRepository;
import com.example.bankingsystem.entity.Account;
import com.example.bankingsystem.entity.AccountType;
import com.example.bankingsystem.entity.Customer;
import com.example.bankingsystem.resource.CustomerRestController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@WebMvcTest(value = CustomerRestController.class)
public class CustomerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CustomerRepository customerService;

	Customer customer;
	Account account;
	List<Account> accounts;
	String URI;

	@Test
	@Order(1)
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

		String inputInJson = this.mapToJson(customer);
		Mockito.when(customerService.save(Mockito.any(Customer.class))).thenReturn(customer);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();

		String outputInJson = response.getContentAsString();

		assertEquals(outputInJson, inputInJson);
		assertEquals(HttpStatus.OK.value(), response.getStatus());

	}

	@Test
	@Order(2)
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

		String inputInJson = this.mapToJson(customer);
		Mockito.when(customerService.save(Mockito.any(Customer.class))).thenReturn(customer);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();

		String outputInJson = response.getContentAsString();

		assertNotEquals(outputInJson, inputInJson);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}
}
