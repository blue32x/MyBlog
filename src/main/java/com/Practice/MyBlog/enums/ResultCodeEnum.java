package com.Practice.MyBlog.enums;

public enum ResultCodeEnum {
	
	ERROR ("errorCode","9999"),
	NORMAL("normalCode","0000")
	; 	//mybatis 설정 경로
	
	private String name;
	private String value;

	
	ResultCodeEnum(String name, String value)
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
