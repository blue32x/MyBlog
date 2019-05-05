package com.Practice.MyBlog.enums;

public enum BaseEnum {
	
	;
	private String name;
	private String value;
	
	BaseEnum(String name, String value)
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
