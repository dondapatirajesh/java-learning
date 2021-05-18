package com.rajesh.springboot.cruddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rajesh.springboot.cruddemo.entity.Employee;

@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO {

	@Autowired
	private EntityManager entityManager;

	@Override
	public List<Employee> findAll() {	

		// Get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);

		// create a query
		// using native Hibernate API
		Query<Employee> query = currentSession.createQuery("from Employee", Employee.class);
		// currentSession.createQuery("from Employee").executeUpdate();

		// execute query and get result list
		List<Employee> employees = query.getResultList();

		return employees;
	}

	@Override
	public Employee findById(int id) {
		Session session = entityManager.unwrap(Session.class);
		Employee employee = session.get(Employee.class, id);
		return employee;
	}

	@Override
	public void save(Employee employee) {
		Session session = entityManager.unwrap(Session.class);
		session.saveOrUpdate(employee);
	}

	@Override
	public void deleteById(int id) {
		Session session = entityManager.unwrap(Session.class);
		
		Query<?> query = session.createQuery("delete from Employee where id=:employeId");
		query.setParameter("employeeId", id);
		query.executeUpdate();
//		Employee employee = session.get(Employee.class, id);
//		if (employee != null) {
//			session.delete(id);			
//		}
//		else System.out.println("Employee not found with Id: " + id);
	}

}