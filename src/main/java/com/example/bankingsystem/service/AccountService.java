package com.example.bankingsystem.service;

import java.util.List;

import com.example.bankingsystem.entity.Account;

public interface AccountService {

	public Account findById(int id);
	
	public List<Account> findAll();
	
	public void save (Account account);
	
}
