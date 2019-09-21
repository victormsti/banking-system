package com.example.bankingsystem.service;

import java.util.List;

import com.example.bankingsystem.entity.Customer;

public interface CustomerService {

	public Customer findById(int id);

	public List<Customer> findAll();

	public Customer save(Customer customer);
	
	public void delete(Customer customer);
}
