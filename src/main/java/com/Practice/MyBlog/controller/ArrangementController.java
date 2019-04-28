package com.Practice.MyBlog.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Practice.MyBlog.service.CompanyService;
import com.Practice.MyBlog.service.OrderService;
import com.Practice.MyBlog.service.dto.CompanyServiceIO;
import com.Practice.MyBlog.service.dto.OrderContentsIO;



@Controller
public class ArrangementController {

	
	private static final Logger logger = LoggerFactory.getLogger(ArrangementController.class);
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private OrderService orderService;
	
	
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
	
	@RequestMapping(value = "/company/order", method=RequestMethod.POST)
	public String resistOrder(HttpServletRequest httpServletRequest) throws IOException {
		String companyNm = (String) httpServletRequest.getParameter("companyNm");
		String telNumber = (String) httpServletRequest.getParameter("telNumber");
		String totAmount = (String) httpServletRequest.getParameter("totAmount");
		String orderDt   = (String) httpServletRequest.getParameter("orderDt");
		logger.debug("insert Order Information  .... start");
		OrderContentsIO orderContentsIO = new OrderContentsIO();
		
 		orderContentsIO.setCompanyNm(companyNm);
 		orderContentsIO.setCompanyTelNbr(telNumber);
 		orderContentsIO.setSumOfPrice(Integer.parseInt(totAmount));
 		orderContentsIO.setOrderDt(orderDt);
 		orderService.registOrder(orderContentsIO);
		
		logger.debug("insert Order Information... end");
		return "home";
	}
	
	@RequestMapping(value = "/company/order", method=RequestMethod.GET,   produces="application/json")
	@ResponseBody
	public HashMap<String,Object> inqueryOrder(@RequestParam("CompanyNm") String companyNm
			 				   ,@RequestParam("inqueryStartDt") String inqueryStartDt
			 				   ,@RequestParam("inqueryEndDt") String inqueryEndDt
							   ) throws IOException {

		if(companyNm==null || companyNm.isEmpty())
		{
			throw new IllegalArgumentException("계약 업체 이름을 입력해주세요.");
		}
		
		if(inqueryStartDt==null || inqueryStartDt.isEmpty())
		{
			throw new IllegalArgumentException("조회시작일자를 입력해 주세요.");
		}
		
		if(inqueryEndDt==null || inqueryEndDt.isEmpty())
		{
			throw new IllegalArgumentException("조회 종료일자를 입력해 주세요.");
		}
		
		/*
		 * 서비스 시작 
		 * 
		 */
		HashMap<String,Object> result = new HashMap<String,Object>();
		List<OrderContentsIO> orderContentsIOs = new ArrayList<OrderContentsIO>();
		OrderContentsIO orderContentsIO = new OrderContentsIO();
 		logger.debug("get order Contents...start");
		
 		orderContentsIO.setCompanyNm(companyNm);
 		orderContentsIO.setOrderStartDt(inqueryStartDt);
 		orderContentsIO.setOrderEndDt(inqueryEndDt);
 		orderContentsIOs = orderService.getOrderContents(orderContentsIO);
 		
		logger.debug("get order Contents...end");
		
		
		
		result.put("contents", orderContentsIOs);
		return result;
	}
	
	
	
	
	

}
