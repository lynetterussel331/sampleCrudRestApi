package com.sample.springbootrestapiexample.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sample.springbootrestapiexample.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
