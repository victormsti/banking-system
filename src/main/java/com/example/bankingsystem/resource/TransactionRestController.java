package com.example.bankingsystem.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.bankingsystem.entity.Transaction;
import com.example.bankingsystem.service.AccountService;
import com.example.bankingsystem.service.CustomerService;
import com.example.bankingsystem.service.TransactionService;

@RestController
@RequestMapping("/api/transaction")
public class TransactionRestController {

	@Autowired
	TransactionService transactionService;
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	AccountService accountService;
	
	@CrossOrigin
	@RequestMapping(value = "/saving", method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Transaction savingTransaction(@RequestBody Transaction transaction) {
		
		return transactionService.save(transaction);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/transfer", method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Transaction transferTransaction(@RequestBody Transaction transaction) {
		
		transactionService.save(transaction);

		return transaction;
	}
	
	@CrossOrigin
	@RequestMapping(value = "/withdraw", method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Transaction withdrawTransaction(@RequestBody Transaction transaction) {
		
		transactionService.save(transaction);

		return transaction;
	}
	
	@CrossOrigin
	@RequestMapping(value = "check/{id}", method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Transaction checkTransaction(@PathVariable String id){
		return transactionService.findById(Integer.parseInt(id));
	}
	
}
