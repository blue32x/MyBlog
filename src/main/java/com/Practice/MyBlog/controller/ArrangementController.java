package com.Practice.MyBlog.controller;

import java.io.IOException;
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

import com.Practice.MyBlog.enums.ResultCodeEnum;
import com.Practice.MyBlog.error.CustomException;
import com.Practice.MyBlog.service.CompanyService;
import com.Practice.MyBlog.service.ExceldownloadService;
import com.Practice.MyBlog.service.OrderService;
import com.Practice.MyBlog.service.dto.CompanyServiceIO;
import com.Practice.MyBlog.service.dto.ExcelServiceIO;
import com.Practice.MyBlog.service.dto.OrderContentsIO;

@Controller
public class ArrangementController {

	private static final Logger logger = LoggerFactory.getLogger(ArrangementController.class);

	@Autowired
	private CompanyService companyService;

	@Autowired
	private OrderService orderService;
	
	
	@Autowired
	private ExceldownloadService exceldownloadService;

	@RequestMapping(value = "/company", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public HashMap<String, Object> company(@RequestParam("CompanyNm") String companyNm,
			@RequestParam("TelNbr") String telNbr) throws IOException {
		// OrderManageIO orderManageIO =(OrderManageIO)
		// httpServletRequest.getAttribute("OrderManageIO");
		logger.debug("#SHCHOI# inquery CompanyInfos [{}]", companyNm);
		/*
		 * 입력 받은 회사명, 전화 번호로 조회한다. 1. 두 값이 모두 NULL 이면 전체 조회 2. 한쪽이라도 있으면 채워져 있는 값으로 조회.
		 */
		logger.debug("get service start");

		CompanyServiceIO companyServiceIO = new CompanyServiceIO();
		companyServiceIO.setCompanyNm(companyNm);
		companyServiceIO.setCompanyTelNbr(telNbr);
		List<CompanyServiceIO> companyServiceIOs = companyService.getCompany(companyServiceIO);

		logger.debug("get service end");
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("contents", companyServiceIOs);
		return map;
	}

	/*
	 * 업체 명을 입력 받는다 Http method : POST
	 * 
	 * body { compayName :, telNumber : }
	 */
	@RequestMapping(value = "/company", method = RequestMethod.POST)
	public String newCompany(HttpServletRequest httpServletRequest) throws IOException {
		CompanyServiceIO companyServiceIO = new CompanyServiceIO();
		try {
			String companyNm = (String) httpServletRequest.getParameter("companyNm");
			String telNumber = (String) httpServletRequest.getParameter("telNumber");

			if (companyNm == null || companyNm.isEmpty()) {
				throw new CustomException("계약 업체 이름을 입력해주세요.");
			}

			if (telNumber == null || telNumber.isEmpty()) {
				throw new CustomException("전화번호를 입력해 주세요.");
			}

			/*
			 * 서비스 시작 회사명, 회사 전화번호를 입력으로 받아서 테이블에 갱신할 땐는
			 * 
			 * 회사Id 회사명 회사 전화번호 갱신 날짜 를 컬럼으로 가지는 테이블에 입력
			 * 
			 */
			logger.info("insert service start");

			companyServiceIO.setCompanyNm(companyNm);
			companyServiceIO.setCompanyTelNbr(telNumber);
			companyService.insertCompany(companyServiceIO);

			logger.info("insert service end");
			companyServiceIO.setRsltCd(ResultCodeEnum.NORMAL.getValue());

		} catch (CustomException e) {
			companyServiceIO.setRsltCd(ResultCodeEnum.ERROR.getValue());
		}
		return "home";
	}

	@RequestMapping(value = "/company/order", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> resistOrder(HttpServletRequest httpServletRequest) throws IOException {
		String companyNm = (String) httpServletRequest.getParameter("companyNm");
		String telNumber = (String) httpServletRequest.getParameter("telNumber");
		String orderCnt = (String) httpServletRequest.getParameter("orderCnt");
		String totAmount = (String) httpServletRequest.getParameter("totAmount");
		String orderDt = (String) httpServletRequest.getParameter("orderDt");
		logger.debug("insert Order Information  .... start");
		OrderContentsIO orderContentsIO = new OrderContentsIO();
		HashMap<String, Object> result = new HashMap<String, Object>();
		try {
			orderContentsIO.setCompanyNm(companyNm);
			orderContentsIO.setCompanyTelNbr(telNumber);
			orderContentsIO.setSumOfPrice(Integer.parseInt(totAmount));
			orderContentsIO.setOrderDt(orderDt);
			orderContentsIO.setOrderCnt(new Integer(orderCnt));
			orderService.registOrder(orderContentsIO);
			orderContentsIO.setRsltCd(ResultCodeEnum.NORMAL.getValue());
			logger.debug("insert Order Information... end");
		} catch (CustomException e) 
		{
			logger.error("{}",e);
			orderContentsIO.setRsltCd(ResultCodeEnum.ERROR.getValue());
		} catch(Exception e2)
		{
			logger.error("{}",e2);
			orderContentsIO.setRsltCd(ResultCodeEnum.ERROR.getValue());
		}
		
		result.put("contents", orderContentsIO);
		return result;
	}
	
	@RequestMapping(value = "/company/order/del", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> delOrder(HttpServletRequest httpServletRequest) throws IOException {
		String orderId = (String) httpServletRequest.getParameter("orderId");
		
		logger.debug("delete Order Information  .... start");
		OrderContentsIO orderContentsIO = new OrderContentsIO();
		orderContentsIO.setOrderId(orderId);
		HashMap<String, Object> result = new HashMap<String, Object>();
		try {
			
			orderService.deleteOrder(orderContentsIO);
			orderContentsIO.setRsltCd(ResultCodeEnum.NORMAL.getValue());
			logger.debug("delete Order Information... end");
		} catch (CustomException e) 
		{
			logger.error("{}",e);
			orderContentsIO.setRsltCd(ResultCodeEnum.ERROR.getValue());
		} catch(Exception e2)
		{
			logger.error("{}",e2);
			orderContentsIO.setRsltCd(ResultCodeEnum.ERROR.getValue());
		}
		
		result.put("contents", orderContentsIO);
		return result;
	}
	
	

	@RequestMapping(value = "/company/order", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public HashMap<String, Object> inqueryOrder(@RequestParam("CompanyNm") String companyNm,
			@RequestParam("inqueryStartDt") String inqueryStartDt, @RequestParam("inqueryEndDt") String inqueryEndDt)
			throws IOException {

		if (companyNm == null || companyNm.isEmpty()) {
			throw new IllegalArgumentException("계약 업체 이름을 입력해주세요.");
		}

		if (inqueryStartDt == null || inqueryStartDt.isEmpty()) {
			throw new IllegalArgumentException("조회시작일자를 입력해 주세요.");
		}

		if (inqueryEndDt == null || inqueryEndDt.isEmpty()) {
			throw new IllegalArgumentException("조회 종료일자를 입력해 주세요.");
		}

		/*
		 * 서비스 시작
		 * 
		 */
		HashMap<String, Object> result = new HashMap<String, Object>();
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
	
	@RequestMapping(value = "/company/excel", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public HashMap<String, Object> downloadExcel(@RequestParam("CompanyNm") String companyNm,
			@RequestParam("inqueryStartDt") String inqueryStartDt, @RequestParam("inqueryEndDt") String inqueryEndDt)
			throws IOException {
		if (companyNm == null || companyNm.isEmpty()) {
			throw new IllegalArgumentException("계약 업체 이름을 입력해주세요.");
		}

		if (inqueryStartDt == null || inqueryStartDt.isEmpty()) {
			throw new IllegalArgumentException("조회시작일자를 입력해 주세요.");
		}

		if (inqueryEndDt == null || inqueryEndDt.isEmpty()) {
			throw new IllegalArgumentException("조회 종료일자를 입력해 주세요.");
		}		
		ExcelServiceIO excelServiceIO = new ExcelServiceIO();
		logger.debug("get order Contents...start");

		logger.debug("excel download...start");
		excelServiceIO.setCompanyNm(companyNm);
		excelServiceIO.setOrderStartDt(inqueryStartDt);
		excelServiceIO.setOrderEndDt(inqueryEndDt);
		try {
			exceldownloadService.downloadExcel(excelServiceIO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.debug("excel download...end");
		
		return null;
	}

	@RequestMapping(value = "/company/allExcel", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public HashMap<String, Object> downloadAllExcel(@RequestParam("inqueryStartDt") String inqueryStartDt, 
			                                        @RequestParam("inqueryEndDt") String inqueryEndDt)
			throws IOException {
		if (inqueryStartDt == null || inqueryStartDt.isEmpty()) {
			throw new IllegalArgumentException("조회시작일자를 입력해 주세요.");
		}

		if (inqueryEndDt == null || inqueryEndDt.isEmpty()) {
			throw new IllegalArgumentException("조회 종료일자를 입력해 주세요.");
		}
		
		
		ExcelServiceIO excelServiceIO = new ExcelServiceIO();
		logger.debug("get order Contents...start");

		logger.debug("excel Alldownload...start");
		excelServiceIO.setOrderStartDt(inqueryStartDt);
		excelServiceIO.setOrderEndDt(inqueryEndDt);
		try {
			exceldownloadService.downloadAllExcel(excelServiceIO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.debug("excel Alldownload...end");
		
		return null;
	}

}
