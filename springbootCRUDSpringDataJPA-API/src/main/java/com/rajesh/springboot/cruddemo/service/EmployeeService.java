package com.rajesh.springboot.cruddemo.service;

import java.util.List;
import java.util.Optional;

import com.rajesh.springboot.cruddemo.entity.Employee;

public interface EmployeeService {

	public List<Employee> findAll();
	
	public Employee findById(int id);
	
	public void save(Employee employee);
	
	public void deleteById(int id);
}