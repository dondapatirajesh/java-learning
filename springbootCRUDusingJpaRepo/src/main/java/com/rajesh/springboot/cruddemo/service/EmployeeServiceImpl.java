package com.rajesh.springboot.cruddemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rajesh.springboot.cruddemo.dao.EmployeeRepository;
import com.rajesh.springboot.cruddemo.entity.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepo;
	
	@Autowired
	public EmployeeServiceImpl(EmployeeRepository employeeRepo) {
		this.employeeRepo = employeeRepo;
	}
	
	@Override
	@Transactional
	public List<Employee> findAll() {
		return employeeRepo.findAll();
	}

	@Override
	@Transactional
	public Employee findById(int id) {
		Optional<Employee> result = employeeRepo.findById(id);
		Employee employee = null;
		if (result.isPresent()) {
			employee = result.get();
		} else {
			throw new RuntimeException("Didn't find employee with id: " + id);
		}
		return employee;
	}

	@Override
	@Transactional
	public void save(Employee employee) {
		employeeRepo.save(employee);
	}

	
	/**
	 * No need of @Transactional because JpaRepository provides 
	 * this functionality
	 */
	@Override
	@Transactional
	public void deleteById(int id) {
		employeeRepo.deleteById(id);
	}

}
