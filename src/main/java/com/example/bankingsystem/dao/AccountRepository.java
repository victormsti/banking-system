package com.example.bankingsystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bankingsystem.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {

}
