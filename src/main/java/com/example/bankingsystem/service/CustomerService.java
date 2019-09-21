package com.example.bankingsystem.service;

import java.util.List;

import com.example.bankingsystem.entity.Customer;

public interface CustomerService {

	public Customer findById(int id);

	public List<Customer> findAll();

	public void save(Customer customer);
}
