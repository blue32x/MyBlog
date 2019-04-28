package com.Practice.MyBlog.service;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLDataException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.Practice.MyBlog.dao.CompanyDao;
import com.Practice.MyBlog.dao.OrderDao;
import com.Practice.MyBlog.service.dto.CompanyServiceIO;
import com.Practice.MyBlog.service.dto.OrderContentsIO;

@Service("OrderService")
public class OrderServiceImpl implements OrderService{
	
	private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
	
	public void registOrder(OrderContentsIO orderContentsIO) throws IOException {
		// TODO Auto-generated method stub
		
		
		
		
		
		String resource = "spring/mybatis-config.xml";  
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);	
		SqlSession session = sqlSessionFactory.openSession();
		try {
			  /*
			   * 1. 입력 받은 업체명과  업체 전화 번호로부터  업체 id를 조회함
			   */
			  CompanyDao compMapper  = session.getMapper(CompanyDao.class);
			  CompanyServiceIO companyServiceIO = new CompanyServiceIO();
			  companyServiceIO.setCompanyNm(orderContentsIO.getCompanyNm());
			  companyServiceIO.setCompanyTelNbr(orderContentsIO.getCompanyTelNbr());
			  List<CompanyServiceIO> inqueryResults = compMapper.get(companyServiceIO);
//			  if(inqueryResults ==null || inqueryResults.size()>1 || inqueryResults.isEmpty())
//			  {
//				  throw new SQLDataException("too Many Rows or Empty Rows...");
//			  }
//			
			  
			  OrderDao mapper = session.getMapper(OrderDao.class);
			  
			  orderContentsIO.setCompanyId(inqueryResults.get(0).getCompanyId());
			  
			  mapper.insertOrder(orderContentsIO);
			  session.commit();
			//  return results;
		}catch(Exception e)
		{
			logger.info("{}",e);
		}
		finally {
		  session.close();
		}
	}
	
	/*
	 * 조회시작일, 조회종료일 , 업체명을 입력 받아.. 해당 업체의 특정 기간동안의 거래내역을 조회한다.
	 * (non-Javadoc)
	 * @see com.Practice.MyBlog.service.OrderService#getOrderContents(com.Practice.MyBlog.service.dto.OrderContentsIO)
	 */
	public List<OrderContentsIO> getOrderContents(OrderContentsIO orderContentsIO) throws IOException {
		// TODO Auto-generated method stub
		
		
		String resource = "spring/mybatis-config.xml";  
		List<OrderContentsIO> results = new ArrayList<OrderContentsIO>();
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);	
		SqlSession session = sqlSessionFactory.openSession();
		try {
			  OrderDao mapper = session.getMapper(OrderDao.class);
			  
			  results = (List<OrderContentsIO>)mapper.inqueryOrderContents(orderContentsIO);
			//  return results;
		}catch(Exception e)
		{
			logger.info("{}",e);
		}
		finally {
		  session.close();
		}
		
		return results;
	}

}
