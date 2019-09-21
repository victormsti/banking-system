package com.example.bankingsystem;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.bankingsystem.entity.Account;
import com.example.bankingsystem.service.AccountService;

public class AccountTest{

	@Autowired
	AccountService accountService;
	
	List<Account> accounts;
	@Test
	public void testFindAll() {
		accounts = accountService.findAll();
		
		assertEquals(accounts != null && accounts.size() != 0, accounts != null && accounts.size() != 0);
	}

}
