package com.Practice.MyBlog.bean;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.jndi.JndiObjectFactoryBean;

public class DataSourceBean {
	
	@Autowired
	private ApplicationContext applicationContext;
	@Autowired
	private GenericXmlApplicationContext genericXmlApplicationContext;
	private JndiObjectFactoryBean datasource; 
	public DataSourceBean()
	{
//		datasource.getJndiName();
		genericXmlApplicationContext = new GenericXmlApplicationContext();
		genericXmlApplicationContext.load("spring/application-config.xml");
		
		this.datasource = (JndiObjectFactoryBean)genericXmlApplicationContext.getBean("dataSource",JndiObjectFactoryBean.class);
//		dataSource ds = (dataSource)genericXmlApplicationContext.getBean("dataSource");
////		DataSource ds = app
	}
	
	
	public DataSource getDataSource()
	{
	 return null;
	}
	

}
