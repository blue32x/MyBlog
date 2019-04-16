package com.Practice.MyBlog.controller.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class OrderManageIO  implements Serializable {

	private String companyNm;
	private String telNbr;
	private String startDt;
	private String endDt;
	
	public String getCompanyNm() {
		return companyNm;
	}
	public void setCompanyNm(String companyNm) {
		this.companyNm = companyNm;
	}
	public String getTelNbr() {
		return telNbr;
	}
	public void setTelNbr(String telNbr) {
		this.telNbr = telNbr;
	}
	public String getStartDt() {
		return startDt;
	}
	public void setStartDt(String startDt) {
		this.startDt = startDt;
	}
	public String getEndDt() {
		return endDt;
	}
	public void setEndDt(String endDt) {
		this.endDt = endDt;
	}
	@Override
	public String toString() {
		return "OrderManageIO [companyNm=" + companyNm + ", telNbr=" + telNbr + ", startDt=" + startDt + ", endDt="
				+ endDt + "]";
	}
	
	
}
