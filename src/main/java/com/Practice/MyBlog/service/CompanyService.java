package com.Practice.MyBlog.service;

import java.io.IOException;
import java.util.List;

import com.Practice.MyBlog.service.dto.CompanyServiceIO;

public interface CompanyService {
	
	public CompanyServiceIO insertCompany(CompanyServiceIO companyServiceIO) throws IOException;
	public CompanyServiceIO updateCompany(CompanyServiceIO companyServiceIO);
	public CompanyServiceIO deleteCompany(CompanyServiceIO companyServiceIO);
	public List<CompanyServiceIO> getCompany(CompanyServiceIO companyServiceIO)throws IOException;
}
