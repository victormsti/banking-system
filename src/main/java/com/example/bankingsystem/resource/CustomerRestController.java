package com.example.bankingsystem.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.bankingsystem.entity.Customer;
import com.example.bankingsystem.service.AccountService;
import com.example.bankingsystem.service.CustomerService;

@RestController
@RequestMapping("/api/customer")
public class CustomerRestController {

	@Autowired
	CustomerService customerService;

	@Autowired
	AccountService accountService;

	@CrossOrigin
	@RequestMapping(value = "/create", method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Customer createCustomer(@RequestBody Customer customer) {

		customerService.save(customer);

		return customer;

	}
	@CrossOrigin
	@RequestMapping(value = "/update", method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Customer updateCustomer(Customer customer) {

		return customerService.save(customer);
	}
	
	@RequestMapping(method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Customer> findAll(){
		return customerService.findAll();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Customer findById(@RequestParam(name = "id", required = true) String id){
		return customerService.findById(Integer.parseInt(id));
	}
}
