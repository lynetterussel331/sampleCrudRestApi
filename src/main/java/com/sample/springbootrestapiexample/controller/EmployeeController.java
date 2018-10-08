package com.sample.springbootrestapiexample.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.springbootrestapiexample.dao.EmployeeDao;
import com.sample.springbootrestapiexample.model.Employee;

@RestController
@RequestMapping("/company")
public class EmployeeController {
	
	@Autowired
	EmployeeDao employeeDAO;
	
	@PostMapping("/employees")
	public Employee createEmployee(@Valid @RequestBody Employee emp) {
		return employeeDAO.save(emp);
	}
	
	@GetMapping("/employees")
	public List<Employee> getAllEmployees() {
		return employeeDAO.findAll();
	}
	
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value="id") Long empId) {
		
		Employee employee = employeeDAO.findOne(empId);
	
		if (employee == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok().body(employee);
	}
	
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value="id") Long empId,@Valid @RequestBody Employee empDetails) {
		
		Employee employee = employeeDAO.findOne(empId);
		
		if (employee == null) {
			return ResponseEntity.notFound().build();
		}
		
		employee.setId(empId);
		employee.setName(empDetails.getName());
		employee.setDesignation(empDetails.getDesignation());
		employee.setExpertise(empDetails.getExpertise());
		Employee updatedEmployee = employeeDAO.save(employee);
		
		return ResponseEntity.ok().body(updatedEmployee);
		
	}
	
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Employee> deleteEmployee(@PathVariable(value = "id") Long empId) {
		
		Employee employee = employeeDAO.findOne(empId);
		
		if (employee != null) {
			employeeDAO.delete(employee);
		} else {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok().build();
		
	}
	
}
