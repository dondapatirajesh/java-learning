package com.rajesh.springboot.cruddemo.dao;

import java.util.List;

import com.rajesh.springboot.cruddemo.entity.Employee;

public interface EmployeeDAO {

	public List<Employee> findAll();
	
	public Employee findById(int id);
	public void save(Employee employee);
	public String deleteById(int id);
}
