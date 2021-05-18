package com.luv2code.springdemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerRestController {

	@Autowired
	private CustomerService customerService;

	@GetMapping("/customers")
	public List<Customer> getCustomers() {
		return customerService.getCustomers();
	}

	@GetMapping("/customers/{customerId}")
	public Customer getCustomerById(@PathVariable("customerId") int customerId) {
		Customer customer = customerService.getCustomer(customerId);
		System.out.println(customer);
		if (customer == null) {
			System.out.println("no customer data found with id: " + customerId);
			throw new CustomerNotFoundException("Customer Not Found with ID: " + customerId);
		}
		return customer;
	}

	/**
	 * @RequestBody converts the HTTP request body to customer pojo and maps to
	 * @param customer 
	 * method setId(0) or setId(null) is used to insert new customer
	 *                 because Hibernate saveOrUpdate results into insert or update
	 *                 queries based on the provided data. If the data is present in
	 *                 the database, update query is executed else save query is executed.
	 * @return the saved customer with id
	 */
	@PostMapping("/customers")
	public Customer addCustomer(@RequestBody Customer customer) {
		customer.setId(0);
		System.out.println("Customer details before updating to database: " + customer);
		customerService.saveCustomer(customer);
		System.out.println("Customer details after updating to database: " + customer);
		return customer;
	}
	
	@PutMapping("/customers")
	public Customer updateCustomer(@RequestBody Customer customer) {
		customerService.saveCustomer(customer);
		return customer;
	}
	
	@DeleteMapping("/customers/{customerId}")
	public String deleteCustomer(@PathVariable("customerId") int customerrrrId) {
		Customer customer = customerService.getCustomer(customerrrrId);
		if (customer == null) {
			throw new CustomerNotFoundException("Customer not found with Id: " + customerrrrId);
		}
		customerService.deleteCustomer(customerrrrId);
		return "Customer deleted with id: " + customerrrrId;
	}
	
}