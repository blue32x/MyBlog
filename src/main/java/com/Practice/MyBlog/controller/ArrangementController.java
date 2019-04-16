package com.Practice.MyBlog.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.mapping.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Practice.MyBlog.service.CompanyService;
import com.Practice.MyBlog.service.dto.CompanyServiceIO;



@Controller
public class ArrangementController {

	
	private static final Logger logger = LoggerFactory.getLogger(ArrangementController.class);
	
	@Autowired
	private CompanyService companyService;
	
	
//	@Autowired
//	private JndiObjectFactoryBean datasource;
	
	@RequestMapping(value = "/company", method=RequestMethod.GET, produces="application/json")
	@ResponseBody
	public HashMap<String,Object> company(@RequestParam("CompanyNm") String companyNm
										 ,@RequestParam("TelNbr") String telNbr) throws IOException 
	{
//		OrderManageIO orderManageIO =(OrderManageIO) httpServletRequest.getAttribute("OrderManageIO");
		logger.debug("#SHCHOI# inquery CompanyInfos [{}]" ,companyNm);
		 /*
		  * 입력 받은 회사명, 전화 번호로 조회한다.
		  * 1. 두 값이 모두 NULL 이면 전체 조회
		  * 2. 한쪽이라도 있으면 채워져 있는 값으로 조회.
		  */
		logger.debug("get service start");
		
		CompanyServiceIO companyServiceIO =new CompanyServiceIO();
		companyServiceIO.setCompanyNm(companyNm);
		companyServiceIO.setCompanyTelNbr(telNbr);
		List<CompanyServiceIO>	companyServiceIOs = companyService.getCompany(companyServiceIO);
		
		
		for(CompanyServiceIO item : companyServiceIOs)
		{
			logger.debug("Items : {}",item);
		}
		
		logger.debug("get service end");
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("contents", companyServiceIOs);
		return map;  
	}
	
	/*
	 * 업체 명을 입력 받는다
	 * Http method : POST
	 * 
	 * body
	 * {
	 *    compayName :,
	 *    telNumber : 
	 * }
	 */
	@RequestMapping(value = "/company", method=RequestMethod.POST)
	public String newCompany(HttpServletRequest httpServletRequest) throws IOException {
		String companyNm =(String) httpServletRequest.getParameter("companyNm");
		String telNumber = (String) httpServletRequest.getParameter("telNumber");
		
		if(companyNm==null || companyNm.isEmpty())
		{
			throw new IllegalArgumentException("계약 업체 이름을 입력해주세요.");
		}
		
		if(telNumber==null || telNumber.isEmpty())
		{
			throw new IllegalArgumentException("전화번호를 입력해 주세요.");
		}
		    
		/*
		 * 서비스 시작 
		 * 회사명, 회사 전화번호를 입력으로 받아서 테이블에 갱신할 땐는
		 * 
		 * 회사Id
		 * 회사명
		 * 회사 전화번호
		 * 갱신 날짜 를 컬럼으로 가지는 테이블에 입력
		 * 
		 */
		logger.info("insert service start");
		
		CompanyServiceIO companyServiceIO =new CompanyServiceIO();
		companyServiceIO.setCompanyNm(companyNm);
		companyServiceIO.setCompanyTelNbr(telNumber);
		companyService.insertCompany(companyServiceIO);
		
		logger.info("insert service end");
		return "home";
	}
	
	@RequestMapping(value = "/company/{companyId}", method=RequestMethod.POST)
	public String updateCompany(@RequestParam("companyId") String companyId) throws IOException {
		if(companyId==null || companyId.isEmpty())
		{
			throw new IllegalArgumentException("계약 업체 Id를 입력해주세요.");
		}

		logger.debug("update service start");
		
		CompanyServiceIO companyServiceIO =new CompanyServiceIO();
		companyServiceIO.setCompanyId(companyId);
		
		companyService.updateCompany(companyServiceIO);
		
		logger.debug("update service end");
		return "home";
	}
	
	@RequestMapping(value = "/company/{companyId}", method=RequestMethod.GET)
	public String getCompany(@RequestParam("companyId") String companyId,Model model) throws IOException {
		if(companyId==null || companyId.isEmpty())
		{
			throw new IllegalArgumentException("계약 업체 Id를 입력해주세요.");
		}

		/*
		 * 서비스 시작 
		 * 
		 */
		logger.debug("get service start");
		CompanyServiceIO companyServiceIO =new CompanyServiceIO();
		companyServiceIO.setCompanyId(companyId);
		
//		companyServiceIO = companyService.getCompany(companyServiceIO);
		
		logger.debug("get service end");
		
		
		return "home";
	}
	
	
	
	
	

}
