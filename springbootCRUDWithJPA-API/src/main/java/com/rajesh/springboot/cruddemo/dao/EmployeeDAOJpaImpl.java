package com.rajesh.springboot.cruddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rajesh.springboot.cruddemo.entity.Employee;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO {

	@Autowired
	private EntityManager entityManager;

	@Override
	public List<Employee> findAll() {
		Query theQuery = entityManager.createQuery("from Employee");
		@SuppressWarnings("unchecked")
		List<Employee> employees = theQuery.getResultList();

		return employees;
	}

	@Override
	public Employee findById(int id) {
		Employee employee = entityManager.find(Employee.class, id);
		return employee;
	}

	@Override
	public void save(Employee employee) {
		Employee updatedEmployee = entityManager.merge(employee);
		employee.setId(updatedEmployee.getId());
		
	}

	@Override
	public String deleteById(int id) {
		Employee employee = findById(id);
		if (employee != null) {
			Query query = entityManager.createQuery("delete from Employee where id=:employeeId");
			query.setParameter("employeeId", id);
			query.executeUpdate();
			return "employee deleted with id: " + id;
		}
		return "employee not found with id: " + id;
	}

}