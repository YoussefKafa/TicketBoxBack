package com.project.tb.services;

import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.tb.dao.*;
import com.project.tb.models.*;
import com.project.tb.exceptions.EmployeeUniqueException;
import com.project.tb.exceptions.GameUniqueException;

@Service
public class EmployeeServices {
	@Autowired
	private EmployeeRepo employeeRepo;

	public Employee saveOrUpdate(final Employee employee) {
		try {
			return employeeRepo.save(employee);
		} catch (final Exception e) {
			throw new GameUniqueException("Employee  " + employee.getEmail() + " is already exists");
		}

	}

	public ArrayList<Employee> findAll() {
		Iterable<Employee> it = employeeRepo.findAll();
		ArrayList<Employee> employees = new ArrayList<Employee>();
		it.forEach(e -> employees.add(e));
		return employees;
	}

	// tested
	public Long count() {
		return employeeRepo.count();
	}

	// tested
	public Employee findById(Long empId) {
		Optional<Employee> employee = employeeRepo.findById(empId);
		Employee employee2 = employee.get();
		if (employee2 == null)
			throw new EmployeeUniqueException("Employee with id : " + empId + " does not exist");
		return employee2;
	}

	// tested
	public void deleteById(Long employeeId) {
		Optional<Employee> employee = employeeRepo.findById(employeeId);
		Employee employee2 = employee.get();
		if (employee == null)
			throw new EmployeeUniqueException("Employee with id " + employeeId + " cannot be found");
		employeeRepo.delete(employee2);
	}

	// tested
	public void deleteAll() {
		employeeRepo.deleteAll();
	}
}