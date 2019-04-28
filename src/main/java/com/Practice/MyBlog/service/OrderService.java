package com.Practice.MyBlog.service;

import java.io.IOException;
import java.util.List;

import com.Practice.MyBlog.service.dto.OrderContentsIO;

public interface OrderService {
	
	public void registOrder(OrderContentsIO orderContentsIO)  throws IOException;
	public List<OrderContentsIO> getOrderContents(OrderContentsIO orderContentsIO)  throws IOException;

}
