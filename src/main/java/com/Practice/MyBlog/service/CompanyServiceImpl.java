package com.Practice.MyBlog.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Practice.MyBlog.bean.CommonBean;
import com.Practice.MyBlog.bean.DataSourceBean;
import com.Practice.MyBlog.dao.CompanyDao;
import com.Practice.MyBlog.service.dto.CompanyServiceIO;

@Service("CompanyService")
public class CompanyServiceImpl implements CompanyService {
	private static final Logger logger = LoggerFactory.getLogger(CompanyServiceImpl.class);
	@Autowired
	private CompanyDao companyDao;
	@Autowired
	private CommonBean cmmonBean;   
	@Autowired
	private DataSourceBean dsBean;
	
	public CompanyServiceIO insertCompany(CompanyServiceIO companyServiceIO) throws IOException {
		// TODO Auto-generated method stu
		/*
		 * companyId 채번
		 */
		companyServiceIO.setCompanyId(cmmonBean.getCompanyId());
		companyServiceIO.setLastChngTmstmp(cmmonBean.getTmstmp());
		
		
		List<CompanyServiceIO> results = new ArrayList<CompanyServiceIO>();
		SqlSession session = dsBean.getSessionFactory().openSession();
		try {
			  CompanyDao mapper = session.getMapper(CompanyDao.class);
			  
			  mapper.insert(companyServiceIO);
			  session.commit();
			//  return results;
		}catch(Exception e)
		{
			logger.info("{}",e);
		}
		finally {
		  session.close();
		}
		
		return companyServiceIO;
	}

	public CompanyServiceIO updateCompany(CompanyServiceIO companyServiceIO) {
		// TODO Auto-generated method stub
		return companyDao.update(companyServiceIO);
	}

	public CompanyServiceIO deleteCompany(CompanyServiceIO companyServiceIO) {
		// TODO Auto-generated method stub
		return companyDao.delete(companyServiceIO);
	}
	

	public List<CompanyServiceIO> getCompany(CompanyServiceIO companyServiceIO) throws IOException {
		// TODO Auto-generated method stub
		
		List<CompanyServiceIO> results = new ArrayList<CompanyServiceIO>();
		SqlSession session = dsBean.getSessionFactory().openSession();
		try {
			  CompanyDao mapper = session.getMapper(CompanyDao.class);
			  
			  results = (List<CompanyServiceIO>)mapper.get(companyServiceIO);
			  
			//  return results;
		} finally {
		  session.close();
		}
		
		
		return results;
		
	
	}
}
