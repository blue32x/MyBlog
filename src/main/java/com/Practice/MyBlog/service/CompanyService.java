package com.Practice.MyBlog.service;

import java.util.List;

import com.Practice.MyBlog.service.dto.CompanyServiceIO;

public interface CompanyService {
	
	public CompanyServiceIO insertCompany(CompanyServiceIO companyServiceIO);
	public CompanyServiceIO updateCompany(CompanyServiceIO companyServiceIO);
	public CompanyServiceIO deleteCompany(CompanyServiceIO companyServiceIO);
	public List<CompanyServiceIO> getCompany(CompanyServiceIO companyServiceIO);
}
