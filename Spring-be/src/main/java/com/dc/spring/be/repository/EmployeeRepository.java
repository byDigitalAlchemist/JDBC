package com.dc.spring.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dc.spring.be.entities.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	
}
