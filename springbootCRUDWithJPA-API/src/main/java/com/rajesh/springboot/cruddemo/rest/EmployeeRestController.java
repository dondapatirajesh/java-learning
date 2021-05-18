package com.rajesh.springboot.cruddemo.rest;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rajesh.springboot.cruddemo.dao.EmployeeDAO;
import com.rajesh.springboot.cruddemo.entity.Employee;

@RestController
@RequestMapping("/jpaapi")
public class EmployeeRestController {

	@Autowired
	private EmployeeDAO employeeService;
	
	@GetMapping("/employees")
	public List<Employee> findAll() {
		return employeeService.findAll();
	}
	
	@GetMapping("/employees/{employeeId}")
	public Employee getEmployee(@PathVariable("employeeId") int id) {
		return employeeService.findById(id);
	}
	
	@PostMapping("/employees")
	@Transactional
	public Employee save(@RequestBody Employee employee) {
		employee.setId(0);
		employeeService.save(employee);
		return employee;
	}
	
	@PutMapping("/employees")
	@Transactional
	public Employee updateEmployee(@RequestBody Employee employee) {
		employeeService.save(employee);
		return employee;
	}
	
	@DeleteMapping("/employees/{employeeId}")
	@Transactional
	public String deleteEmployee(@PathVariable("employeeId") int id) {
		String result = employeeService.deleteById(id);
		return result;
	}
}