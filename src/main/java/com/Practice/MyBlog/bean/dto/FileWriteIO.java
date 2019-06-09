package com.Practice.MyBlog.bean.dto;

public class FileWriteIO {
	
	 private int price;
	 private int count;
	 private String orderDate;
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getCount() {
		return count;
	}
	
	public void setCount(int count) {
		this.count = count;
	}
	
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	@Override
	public String toString() {
		return "FileWriteIO [price=" + price + ", count=" + count + "]";
	}
}
