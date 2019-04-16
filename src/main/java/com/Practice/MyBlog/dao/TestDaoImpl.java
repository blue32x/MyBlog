package com.Practice.MyBlog.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.Practice.MyBlog.bean.DataSourceBean;


@Repository("TestDao")
public class TestDaoImpl implements TestDao {

	public String selectAll() {
		// TODO Auto-generated method stub
		return null;
	}



}
