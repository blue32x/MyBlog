package com.Practice.MyBlog.bean;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Practice.MyBlog.dao.CompanyDao;
import com.Practice.MyBlog.service.dto.CompanyServiceIO;


@Component
public class CommonBean {
	
	@Autowired
	private CompanyDao			companyDao; 
	
	/*
	 * 채번 API
	 * 1.멀티 쓰레드 환경일 경우 ?  --> SELECT FOR UPDATE, OR ORACLE SEQUENCE 
	 */
	public String getCompanyId() throws IOException
	{
		/*
		 * 9자리
		 * ex) 인덱스가 1인 경우  000000001 이렇게 채번됨
		 */
		String resource = "spring/mybatis-config.xml"; 
		int result =0;
		List<CompanyServiceIO> results = new ArrayList<CompanyServiceIO>();
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);	
		SqlSession session = sqlSessionFactory.openSession();
		try {
			  CompanyDao mapper = session.getMapper(CompanyDao.class);
			  
			  result = (int)mapper.selectForUpdate();
			  
			//  return results;
		} finally {
		  session.close();
		}
		
		
		int nextIdx = result+1;
		return String.format("%09d", nextIdx);  
	}
	
	public String getTmstmp()
	{
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyymmddhhmmss");		
		return sdf.format(date).toString();
	}
}
