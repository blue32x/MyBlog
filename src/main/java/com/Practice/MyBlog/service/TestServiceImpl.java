package com.Practice.MyBlog.service;

import static org.junit.Assert.assertEquals;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Practice.MyBlog.dao.TestDao;
import com.Practice.MyBlog.dao.TestDaoImpl;

@Service
public class TestServiceImpl implements TestService {

	private static final Logger logger = LoggerFactory.getLogger(TestServiceImpl.class);
	@Autowired
	TestDao testDao;
	
	public String test() {
		// TODO Auto-generated method stub
		logger.info("#SHCHOI# TEST SERVICE START");
		String rtnVal = testDao.selectAll();
		logger.info("#SHCHOI# TEST SERVICE END {}",rtnVal);
		return null;
	}

}
