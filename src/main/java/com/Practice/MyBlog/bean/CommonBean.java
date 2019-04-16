package com.Practice.MyBlog.bean;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Practice.MyBlog.dao.CompanyDao;


@Component
public class CommonBean {
	
	@Autowired
	private CompanyDao			companyDao; 
	
	/*
	 * 채번 API
	 * 1.멀티 쓰레드 환경일 경우 ?  --> SELECT FOR UPDATE, OR ORACLE SEQUENCE 
	 */
	public String getCompanyId()
	{
		/*
		 * 9자리
		 * ex) 인덱스가 1인 경우  000000001 이렇게 채번됨
		 */
		BigDecimal currentIdx =companyDao.selectForUpdate();
		BigDecimal nextIdx = currentIdx.add(BigDecimal.ONE);
		return String.format("%09d", Integer.parseInt(nextIdx.toString()));  
	}
	
	public String getTmstmp()
	{
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyymmddhhmmss");		
		return sdf.format(date).toString();
	}
}
