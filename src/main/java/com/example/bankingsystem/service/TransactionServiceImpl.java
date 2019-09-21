package com.example.bankingsystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bankingsystem.dao.TransactionRepository;
import com.example.bankingsystem.entity.Transaction;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	TransactionRepository transactionRepository;

	@Override
	public Transaction findById(int id) {
		Transaction transaction = null;

		Optional<Transaction> result = transactionRepository.findById(id);

		if (result.isPresent()) {
			transaction = result.get();
		}

		else {
			throw new RuntimeException("Transaction not found. Id - " + id);
		}

		return transaction;
	}

	@Override
	public List<Transaction> findAll() {
		return transactionRepository.findAll();
	}

	@Override
	public void save(Transaction transaction) {
		transactionRepository.save(transaction);
	}

}
