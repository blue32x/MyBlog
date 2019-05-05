package com.Practice.MyBlog.enums;

public enum ConfigPath {
	
	MYBATIS_CONFIG ("MyBatisPath","spring/mybatis-config.xml"); 	//mybatis 설정 경로
	
	private String name;
	private String value;

	
	ConfigPath(String name, String value)
	{
		this.name  = name;
		this.value = value;
	}
	
	
	
	public String getName()
	{
		return this.name;
	}
	
	public String getValue()
	{
		return this.value;
	}
}
