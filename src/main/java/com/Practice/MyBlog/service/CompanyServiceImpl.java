package com.Practice.MyBlog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Practice.MyBlog.bean.CommonBean;
import com.Practice.MyBlog.dao.CompanyDao;
import com.Practice.MyBlog.service.dto.CompanyServiceIO;

@Service("CompanyService")
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyDao companyDao;
	@Autowired
	private CommonBean cmmonBean;   
	
	public CompanyServiceIO insertCompany(CompanyServiceIO companyServiceIO) {
		// TODO Auto-generated method stu
		/*
		 * companyId 채번
		 */
		companyServiceIO.setCompanyId(cmmonBean.getCompanyId());
		companyServiceIO.setLastChngTmstmp(cmmonBean.getTmstmp());
		return companyDao.insert(companyServiceIO);
	}

	public CompanyServiceIO updateCompany(CompanyServiceIO companyServiceIO) {
		// TODO Auto-generated method stub
		return companyDao.update(companyServiceIO);
	}

	public CompanyServiceIO deleteCompany(CompanyServiceIO companyServiceIO) {
		// TODO Auto-generated method stub
		return companyDao.delete(companyServiceIO);
	}
	

	public List<CompanyServiceIO> getCompany(CompanyServiceIO companyServiceIO) {
		// TODO Auto-generated method stub
		return companyDao.getAll();
	}

}
