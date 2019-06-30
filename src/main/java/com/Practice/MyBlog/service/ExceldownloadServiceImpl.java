package com.Practice.MyBlog.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Practice.MyBlog.bean.CommonBean;
import com.Practice.MyBlog.bean.DataSourceBean;
import com.Practice.MyBlog.bean.dto.FileWriteIO;
import com.Practice.MyBlog.dao.CompanyDao;
import com.Practice.MyBlog.dao.OrderDao;
import com.Practice.MyBlog.error.CustomException;
import com.Practice.MyBlog.service.dto.CompanyServiceIO;
import com.Practice.MyBlog.service.dto.ExcelServiceIO;
import com.Practice.MyBlog.service.dto.OrderContentsIO;


@Service("ExceldownloadService")
public class ExceldownloadServiceImpl implements ExceldownloadService {
	
	private static final Logger logger = LoggerFactory.getLogger(ExceldownloadServiceImpl.class);
	@Autowired
	private DataSourceBean dsBean;
	
	@Autowired
	private CommonBean cmmonBean;   
	/*
	 * 2019-06-16
	 * 1.온라인 excel download  기능 추가.
	 * 2. controller로 부터 excelDownload의 조회데이터를 db로 부터 조회하기 위해 회사명, 조회시작일자, 조회종료일자를 ExcelServiceIO에 셋팅
	 * 3.db 데이터 조회 후 CommonBean의 excelDownload method 호출
	 * 
	 */
	public ExcelServiceIO downloadExcel(ExcelServiceIO excelServiceIO) throws Exception
	{
		ExcelServiceIO result = new ExcelServiceIO();
		
		OrderContentsIO orderContentsIO = new OrderContentsIO();
		List<OrderContentsIO> orderContentsIOs = null;
		//1. company id 조회
		String companyId = cmmonBean.registeredCompany(excelServiceIO.getCompanyNm(), null);
		if(companyId == null)
		{
			//기등록 되어있는 업체임.
			throw new CustomException("등록되지 않은 업체입니다.");
		}
		//2.db  데이터 조회
		SqlSession session = dsBean.getSessionFactory().openSession();
		try {
			  OrderDao mapper = session.getMapper(OrderDao.class);
			  
			  orderContentsIO.setCompanyId(companyId);
			  orderContentsIO.setOrderStartDt(excelServiceIO.getOrderStartDt());
			  orderContentsIO.setOrderEndDt(excelServiceIO.getOrderEndDt());
			  
			  orderContentsIOs =  mapper.inqueryOrderContents(orderContentsIO);
		}catch(Exception e)
		{
			logger.info("{}",e);
		}
		finally {
		  session.close();
		}
		
		//3.조회한 데이터 조립 
		//4.exceldownload method 호출
		/*
		 * problem .데이터가 많아질 경우 api 호출 후 오랜시간을 잡아먹음 호출 후 비동기 처리하던가. 그냥 일정 날짜에  정기적으로 배치 처리하는 것도 
		 * 고려.
		 */
		cmmonBean.downloadExcel(_mappingIO(orderContentsIOs),excelServiceIO.getCompanyNm());
		
		return result;
	}
	/**
	 * 2019-06-30
	 * 거래 하는 전 업체에 대한 excel 다운로드 기능 추가.
	 * 
	 * 1. 계약 업체 전체 조회 후 companyId list로 반환
	 * 2. 각 companyId 별  excel 정산 파일 생성
	 * 
	 */
	public ExcelServiceIO downloadAllExcel(ExcelServiceIO excelServiceIO) throws Exception {
		// TODO Auto-generated method stub
	ExcelServiceIO result = new ExcelServiceIO();
		
		OrderContentsIO orderContentsIO = new OrderContentsIO();
		List<OrderContentsIO> orderContentsIOs = null;
		//1. company id 목록 조회
		Map<String,Object> excelMap = new HashMap<String,Object>();
		List<CompanyServiceIO> companyList = null;
		
		
		
		//2.db  데이터 조회
		SqlSession session = dsBean.getSessionFactory().openSession();
		try {
			CompanyDao companyMapper = session.getMapper(CompanyDao.class);
			
			 companyList = companyMapper.getAll();
			
			 for(CompanyServiceIO companyServiceIO : companyList)
			 {
				 OrderDao mapper = session.getMapper(OrderDao.class);
				 
				 orderContentsIO.setCompanyId(companyServiceIO.getCompanyId());
				 orderContentsIO.setOrderStartDt(excelServiceIO.getOrderStartDt());
				 orderContentsIO.setOrderEndDt(excelServiceIO.getOrderEndDt());
				 
				 orderContentsIOs =  mapper.inqueryOrderContents(orderContentsIO);
				 
				if( excelMap.containsKey(companyServiceIO.getCompanyId()))
				{
					logger.debug("allExcel duplicated key.. continue next key");
					continue;
				}
				else
				{
					excelMap.put(companyServiceIO.getCompanyId(), orderContentsIOs);
				}
				 
			 }
		}catch(Exception e)
		{
			logger.error("{}",e);
		}
		finally {
		  session.close();
		}
		
		//3.조회한 데이터 조립 
		//4.exceldownload method 호출
		/*
		 * problem .데이터가 많아질 경우 api 호출 후 오랜시간을 잡아먹음 호출 후 비동기 처리하던가. 그냥 일정 날짜에  정기적으로 배치 처리하는 것도 
		 * 고려.
		 */
		for(CompanyServiceIO companyServiceIO : companyList)
		 {
			if( excelMap.containsKey(companyServiceIO.getCompanyId()))
			{
				
				@SuppressWarnings("unchecked")
				List<OrderContentsIO> tmp = (List<OrderContentsIO>)  excelMap.get(companyServiceIO.getCompanyId());
				if(tmp.size() != 0)
				{					
					cmmonBean.downloadExcel(_mappingIO(tmp),companyServiceIO.getCompanyNm());
				}
			}
			else
			{
			}
		 }
		
		
		
		return result;
	}
	
	
	
	private List<FileWriteIO> _mappingIO(List<OrderContentsIO> orderContentsIOs)
	{
		List<FileWriteIO>  list= new ArrayList<FileWriteIO>(); 
		
		for(OrderContentsIO item : orderContentsIOs)
		{
			FileWriteIO  fileWriteIO = new FileWriteIO();
			
			fileWriteIO.setPrice(item.getSumOfPrice());
			fileWriteIO.setOrderDate(item.getOrderDt());
			fileWriteIO.setCount(item.getOrderCnt());
			logger.debug("## {}",fileWriteIO);
			list.add(fileWriteIO);
		}
		
		return list;
	}

	

}
