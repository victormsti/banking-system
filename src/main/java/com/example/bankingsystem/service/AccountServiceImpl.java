package com.example.bankingsystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bankingsystem.dao.AccountRepository;
import com.example.bankingsystem.entity.Account;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	AccountRepository accountRepository;

	@Override
	public Account findById(int id) {
		Account account = null;

		Optional<Account> result = accountRepository.findById(id);

		if (result.isPresent()) {
			account = result.get();
		}

		else {
			throw new RuntimeException("Account not found. Id - " + id);
		}

		return account;
	}

	@Override
	public List<Account> findAll() {
		return accountRepository.findAll();
	}

	@Override
	public void save(Account account) {
		accountRepository.save(account);
	}

}
