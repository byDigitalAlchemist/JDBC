package com.dc.spring.be.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dc.spring.be.entities.Employee;
import com.dc.spring.be.repository.EmployeeRepository;

@Component
public class EmployeeService {

	@Autowired
	public EmployeeRepository repository;
	
	
	public List<Employee> getEmployees(){
		return repository.findAll();
	}
}
