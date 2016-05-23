package com.dc.spring.be.xml;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class ApplicationBeXML 
{
    public static void main( String[] args )
    {
        
        try(AbstractApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml")){
        	System.out.println("XML Configruations done!");
        	
        	
        }
    }
}
