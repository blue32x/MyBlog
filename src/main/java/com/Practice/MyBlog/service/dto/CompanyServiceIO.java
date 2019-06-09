package com.Practice.MyBlog.service.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CompanyServiceIO implements Serializable{
	
	private String companyId;  			//company를 유일하게 식별할 수 있는 유일한 값
	private String companyNm;  			//company 이름
	private String companyTelNbr;		//company 전화번호
	private String rsltCd;
	private String lastChngTmstmp; 		//최종 수정일자
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getCompanyNm() {
		return companyNm;
	}
	public void setCompanyNm(String companyNm) {
		this.companyNm = companyNm;
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
	
	@Override
	public String toString() {
		return "CompanyServiceIO [companyId=" + companyId + ", companyNm=" + companyNm + ", companyTelNbr="
				+ companyTelNbr + ", lastChngTmstmp=" + lastChngTmstmp + "]";
	}
	

}
