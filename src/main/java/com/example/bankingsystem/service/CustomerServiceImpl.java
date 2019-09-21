package com.example.bankingsystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bankingsystem.dao.CustomerRepository;
import com.example.bankingsystem.entity.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository;

	@Override
	public Customer findById(int id) {
		Customer customer = null;

		Optional<Customer> result = customerRepository.findById(id);

		if (result.isPresent()) {
			customer = result.get();
		}

		else {
			throw new RuntimeException("Customer not found. Id - " + id);
		}

		return customer;
	}

	@Override
	public List<Customer> findAll() {
		return customerRepository.findAll();
	}

	@Override
	public Customer save(Customer customer) {
		return customerRepository.save(customer);

	}
	
	@Override
	public void delete(Customer customer) {
		customerRepository.delete(customer);

	}

}
