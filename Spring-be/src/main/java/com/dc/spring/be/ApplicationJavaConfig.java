package com.dc.spring.be;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.dc.spring.be.configurations.RBEConfiguration;
import com.dc.spring.be.entities.Employee;
import com.dc.spring.be.repository.EmployeeRepository;

/**
 * Hello world!
 *
 */
public class ApplicationJavaConfig 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
        try(AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(RBEConfiguration.class)){
        	
        	System.out.println(" Context Ready ");
        	
        	EmployeeRepository repository = context.getBean(EmployeeRepository.class);
        	List<Employee> employees = repository.findAll();
        	
        	for (Employee employee : employees) {
        		System.out.println(employee);
			}
        	
        };
        
    }
}
