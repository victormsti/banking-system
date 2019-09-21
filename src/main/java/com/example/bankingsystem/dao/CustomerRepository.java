package com.example.bankingsystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bankingsystem.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

}
