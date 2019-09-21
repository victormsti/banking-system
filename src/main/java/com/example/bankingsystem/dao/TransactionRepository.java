package com.example.bankingsystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bankingsystem.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer>{

}
