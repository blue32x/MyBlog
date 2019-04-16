package com.Practice.MyBlog.dao;

import java.math.BigDecimal;
import java.util.List;

import com.Practice.MyBlog.service.dto.CompanyServiceIO;

public interface CompanyDao {
	public CompanyServiceIO insert(CompanyServiceIO companyServiceIO);
	public CompanyServiceIO update(CompanyServiceIO companyServiceIO);
	public List<CompanyServiceIO> get(CompanyServiceIO companyServiceIO); 
	public List<CompanyServiceIO> getAll(); 
	public CompanyServiceIO delete(CompanyServiceIO companyServiceIO);
	public BigDecimal selectForUpdate();

}
