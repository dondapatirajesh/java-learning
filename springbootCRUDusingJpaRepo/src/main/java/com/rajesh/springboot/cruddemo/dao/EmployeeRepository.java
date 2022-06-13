package com.rajesh.springboot.cruddemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rajesh.springboot.cruddemo.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
