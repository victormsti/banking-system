package com.example.bankingsystem.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.bankingsystem.entity.Account;
import com.example.bankingsystem.entity.AccountType;
import com.example.bankingsystem.service.AccountService;

@RestController
@RequestMapping("/api/account")
public class AccountRestController {

	@Autowired
	AccountService accountService;

	@CrossOrigin
	@RequestMapping(value = "single_account/create", method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Account createSingleAccount(@RequestBody Account account) {

		account.setAccountType(AccountType.singleAccount);
		accountService.save(account);

		return account;

	}
	
	@CrossOrigin
	@RequestMapping(value = "joint_account/create", method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Account> createJointAccount(@RequestBody List<Account> accounts) {

		for (Account account : accounts) {
			account.setAccountType(AccountType.jointAccount);
			accountService.save(account);
		}
		return accounts;

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
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Account findById(@RequestParam(name = "id", required = true) String id){
		return accountService.findById(Integer.parseInt(id));
	}
}
