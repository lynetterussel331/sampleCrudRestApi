package com.sample.springbootrestapiexample.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.springbootrestapiexample.Exception.ResourceNotFoundException;
import com.sample.springbootrestapiexample.model.Employee;
import com.sample.springbootrestapiexample.repository.EmployeeRepository;

@Service
public class EmployeeDao {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	public Employee save(Employee emp) {
		return employeeRepository.save(emp);
	}
	
	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}
	
	public Employee findOne(Long empid) {
		return employeeRepository.findById(empid)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", empid));
	}
	
	public void delete(Employee emp) {
		employeeRepository.delete(emp);
	}
	
}
