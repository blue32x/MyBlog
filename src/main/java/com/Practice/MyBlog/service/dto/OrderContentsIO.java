package com.Practice.MyBlog.service.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class OrderContentsIO  implements Serializable{
	
	private String  orderStartDt;
	private String  orderId;
	private String 	orderDt;
	private String  companyNm;
	private String  companyId;
	private String  companyTelNbr;
	private Integer orderCnt;
	private String  orderEndDt;
	private Integer SumOfPrice ;
	private String rsltCd;
	private String lastChngTmstmp;
	public String getOrderStartDt() {
		return orderStartDt;
	}
	public void setOrderStartDt(String orderStartDt) {
		this.orderStartDt = orderStartDt;
	}
	public String getOrderDt() {
		return orderDt;
	}
	public void setOrderDt(String orderDt) {
		this.orderDt = orderDt;
	}
	public String getCompanyNm() {
		return companyNm;
	}
	public void setCompanyNm(String companyNm) {
		this.companyNm = companyNm;
	}
	public Integer getOrderCnt() {
		return orderCnt;
	}
	public void setOrderCnt(Integer orderCnt) {
		this.orderCnt = orderCnt;
	}
	public String getOrderEndDt() {
		return orderEndDt;
	}
	public void setOrderEndDt(String orderEndDt) {
		this.orderEndDt = orderEndDt;
	}
	public Integer getSumOfPrice() {
		return SumOfPrice;
	}
	public void setSumOfPrice(Integer sumOfPrice) {
		SumOfPrice = sumOfPrice;
	}
	
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getCompanyTelNbr() {
		return companyTelNbr;
	}
	public void setCompanyTelNbr(String companyTelNbr) {
		this.companyTelNbr = companyTelNbr;
	}
	
	
	public String getLastChngTmstmp() {
		return lastChngTmstmp;
	}
	public void setLastChngTmstmp(String lastChngTmstmp) {
		this.lastChngTmstmp = lastChngTmstmp;
	}
	
	
	public String getRsltCd() {
		return rsltCd;
	}
	public void setRsltCd(String rsltCd) {
		this.rsltCd = rsltCd;
	}
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	@Override
	public String toString() {
		return "OrderContentsIO [orderStartDt=" + orderStartDt + ", orderId=" + orderId + ", orderDt=" + orderDt
				+ ", companyNm=" + companyNm + ", companyId=" + companyId + ", companyTelNbr=" + companyTelNbr
				+ ", orderCnt=" + orderCnt + ", orderEndDt=" + orderEndDt + ", SumOfPrice=" + SumOfPrice + ", rsltCd="
				+ rsltCd + ", lastChngTmstmp=" + lastChngTmstmp + "]";
	}
	
	
	
	

}
