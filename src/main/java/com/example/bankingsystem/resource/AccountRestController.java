package com.example.bankingsystem.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.bankingsystem.entity.Account;
import com.example.bankingsystem.entity.Customer;
import com.example.bankingsystem.service.AccountService;
import com.example.bankingsystem.service.CustomerService;

@RestController
@RequestMapping("/api/account")
public class AccountRestController {

	@Autowired
	AccountService accountService;

	@Autowired
	CustomerService customerService;
	
	@CrossOrigin
	@RequestMapping(value = "single_account", method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Account createSingleAccount(@RequestBody Customer customer) {

		
		customerService.save(customer);

		return customer.getAccounts().get(0);

	}
	
	@CrossOrigin
	@RequestMapping(value = "joint_account", method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Account createJointAccount(@RequestBody List<Customer> customers) {

		for (Customer customer: customers) {
			customerService.save(customer);
		}
		return customers.get(0).getAccounts().get(0);

	}
	
	@CrossOrigin
	@RequestMapping(value = "/update", method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Account updateAccount(Account account) {

		return accountService.save(account);
	}
	
	@RequestMapping(method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Account> findAll(){
		return accountService.findAll();
	}
}
