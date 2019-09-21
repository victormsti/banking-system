package com.example.bankingsystem.service;

import java.util.List;

import com.example.bankingsystem.entity.Transaction;

public interface TransactionService {
	
	public Transaction findById(int id);

	public List<Transaction> findAll();

	public Transaction save(Transaction transaction);

}
