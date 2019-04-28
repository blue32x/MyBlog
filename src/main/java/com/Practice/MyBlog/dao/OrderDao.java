package com.Practice.MyBlog.dao;

import java.util.List;

import com.Practice.MyBlog.service.dto.OrderContentsIO;

public interface OrderDao {
	
	public List<OrderContentsIO> inqueryOrderContents(OrderContentsIO orderContentsIO);
	public void insertOrder(OrderContentsIO orderContentsIO);
	
}
