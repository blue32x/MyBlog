package com.Practice.MyBlog.common;

import javax.naming.NamingException;

import org.junit.runners.model.InitializationError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jndi.JndiTemplate;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

public class ExtSpringJUnit4ClassRunner  extends SpringJUnit4ClassRunner{

	public ExtSpringJUnit4ClassRunner(Class<?> clazz) throws InitializationError {
		super(clazz);
		// TODO Auto-generated constructor stub
		try
		{
			binJndi();
		}
		catch(Exception e)
		{
			
		}
	}
	
	private void binJndi() throws IllegalStateException, NamingException
	{
		SimpleNamingContextBuilder builde = new SimpleNamingContextBuilder();
		builde.activate();
		
		
		JndiTemplate jt  =  new JndiTemplate();
		
		DriverManagerDataSource dm = new DriverManagerDataSource();
		
		dm.setDriverClassName("com.mysql.jdbc.Driver");
		dm.setUrl("jdbc:mysql://192.168.99.100:13306/mother?verifyServerCertificate=false&useSSL=true");
		dm.setUsername("root");
		dm.setPassword("devpassword");
		jt.bind("java:comp/env/jdbc/blablahDS", dm);

	}
	
	
}
