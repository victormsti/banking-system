package com.example.bankingsystem.resource;

import java.math.BigDecimal;
import java.util.Date;
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
import com.example.bankingsystem.entity.Transaction;
import com.example.bankingsystem.entity.TransactionType;
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
	@RequestMapping(value = "/saving/create", method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Transaction savingTransaction(@RequestBody Transaction transaction) {
		
		return transactionService.save(transaction);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/transfer/create", method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Transaction transferTransaction(@RequestBody Transaction transaction) {
		
		transactionService.save(transaction);

		return transaction;
	}
	
	@RequestMapping(method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Transaction> findAll(){
		return transactionService.findAll();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Transaction checkTransaction(@RequestParam(name = "id", required = true) String id){
		return transactionService.findById(Integer.parseInt(id));
	}
}
