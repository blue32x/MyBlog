package com.Practice.MyBlog.service.dto;

public class ExcelServiceIO {
	
	private String companyNm;
	private String orderStartDt;
	private String orderEndDt;
	public String getCompanyNm() {
		return companyNm;
	}
	public void setCompanyNm(String companyNm) {
		this.companyNm = companyNm;
	}
	public String getOrderStartDt() {
		return orderStartDt;
	}
	public void setOrderStartDt(String orderStartDt) {
		this.orderStartDt = orderStartDt;
	}
	public String getOrderEndDt() {
		return orderEndDt;
	}
	public void setOrderEndDt(String orderEndDt) {
		this.orderEndDt = orderEndDt;
	}
	@Override
	public String toString() {
		return "ExcelServiceIO [companyNm=" + companyNm + ", orderStartDt=" + orderStartDt + ", orderEndDt="
				+ orderEndDt + "]";
	}
	
	
	

}
