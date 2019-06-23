package com.Practice.MyBlog.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.Practice.MyBlog.service.dto.OrderContentsIO;


public interface OrderDao {
	
	public List<OrderContentsIO> 	inqueryOrderContents(OrderContentsIO orderContentsIO);
	public void 					insertOrder(OrderContentsIO orderContentsIO);
	public int   					selectOrderIdSeqPerCompanyId(OrderContentsIO orderContentsIO);
	public void						deleteOrder(OrderContentsIO orderContentsIO);
	public void						updateOrder(OrderContentsIO orderContentsIO);
}
