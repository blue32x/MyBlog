package com.Practice.MyBlog.bean;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Practice.MyBlog.bean.dto.FileWriteIO;
import com.Practice.MyBlog.dao.CompanyDao;
import com.Practice.MyBlog.enums.ConfigPath;
import com.Practice.MyBlog.service.dto.CompanyServiceIO;


@Component
public class CommonBean {
	private static final Logger logger = LoggerFactory.getLogger(CommonBean.class);
	@Autowired
	private CompanyDao			companyDao; 
	@Autowired
	private DataSourceBean dsBean;
	/*
	 * 채번 API
	 * 1.멀티 쓰레드 환경일 경우 ?  --> SELECT FOR UPDATE, OR ORACLE SEQUENCE 
	 */
	public String getCompanyId() throws IOException
	{
		/*
		 * 9자리
		 * ex) 인덱스가 1인 경우  000000001 이렇게 채번됨
		 */
		int result =0;
		SqlSession session = dsBean.getSessionFactory().openSession();
		try {
			  CompanyDao mapper = session.getMapper(CompanyDao.class);
			  
			  result = (int)mapper.selectForUpdate();
			  
			//  return results;
		} finally {
		  session.close();
		}
		
		
		int nextIdx = result+1;
		return String.format("%09d", nextIdx);  
	}
	
	
	public String registeredCompany(String companyNm,String companyTelNbr) throws IOException
	{
		List<CompanyServiceIO> results = new ArrayList<CompanyServiceIO>();
		SqlSession session = dsBean.getSessionFactory().openSession();
			
		try {
			  CompanyDao mapper = session.getMapper(CompanyDao.class);
			  CompanyServiceIO companyServiceIO = new CompanyServiceIO();
			  companyServiceIO.setCompanyNm(companyNm); 
			  companyServiceIO.setCompanyTelNbr(companyTelNbr);
			  results = (List<CompanyServiceIO>)mapper.get(companyServiceIO);
			  
			//  return results;
		} finally {
		  session.close();
		}
		if(results.size()==0 || results.isEmpty())
		{
			return null;
		}
		return results.get(0).getCompanyId();
	}
	
	public String getTmstmp()
	{
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyymmddhhmmss");		
		return sdf.format(date).toString();
	}
	
	
	
	
	public String downloadExcel(List<FileWriteIO> list) throws Exception
	{
		
		if(list.size() < 1)
		{
			logger.error("input list size is 0");
		}
	   String tmphome = ConfigPath.EXCEL_TEMPLATE.getValue();
	   SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	   String file_name="Bill_form.xlsx";
	   String date = sdf.format(new Date());
	   String target_file_name= "Company_name_"+date+"_Bill.xlsx";
	   
	   FileInputStream fis = new FileInputStream("C:\\Users\\shchoi54\\git\\MyBlog\\src\\main\\resources\\excelform\\Bill_form.xlsx");
	   FileOutputStream fos = new FileOutputStream("C:\\result\\"+target_file_name);
	   
	   Iterator<FileWriteIO> iterator = list.iterator();
	   XSSFWorkbook book = new XSSFWorkbook(fis);
	   XSSFSheet sheet = book.getSheetAt(0); //EXCEL 시트 지정
	   int iRow = 5;  //6행  행은 +2행씩 증가	
	   int iCol = 1;  //B열부터 시작    날짜가 있음 열도 +2 씩 증가  
	   /*
	    * 2019-06-09
	    * 입려받은 데이터를 셀의 원하는 위치에 셋팅 한다. 
	    */
	   int totPrice = 0;
	   int totCount = 0;
	   for(int idx =0; idx< list.size(); idx++)
	   {
		   String targetDate = list.get(idx).getOrderDate();
		   int price = list.get(idx).getPrice();
		   int count = list.get(idx).getCount();
		   ///달력 포맷 탐색
		   for(int i=iRow; i<16; i+=2)
		   {
			   for(int j=iCol; j<15; j+=2)
			   {
				   int dataJ = j+1;
				   int dataI = i;
				   Cell cell0 = sheet.getRow(dataI).getCell(dataJ);  //실제 데이터 넣는 곳
				   
				   if(targetDate.equals(sdf.format(sheet.getRow(i).getCell(j).getDateCellValue())))
				   {
					   logger.debug("targetDate {} form_Date : [{}]",new Object[] {targetDate, sheet.getRow(i).getCell(j).getDateCellValue()});
					   
					   double cellData = cell0.getNumericCellValue();
					   Double tmpDouble = new Double(price);
					   totPrice += price; //총액
					   totCount += count; //총인원 수
					   cellData+=tmpDouble;  //form의 저장되어있는 데이터와 현재 데이터를 더한다. 
					   
					   logger.debug("cellData {} form_Date : [{}]",new Object[] {cellData, sheet.getRow(i).getCell(j).getDateCellValue()});
					   cell0.setCellValue(cellData);   
					   logger.debug("replaceDate {} form_Date : [{}]",new Object[] {sheet.getRow(dataI).getCell(dataJ).getNumericCellValue(), sheet.getRow(i).getCell(j).getDateCellValue()});
				   
				   }
			   }
			 //  iCol=1; //첫 행 작업외에는 1열부터 시작한다.
		   }
	   }
	   
	   //인원  row: 17 / col:4
	   Cell cntCell = sheet.getRow(17).getCell(4);
	   cntCell.setCellValue(totCount);
//	   //단가  row: 19 / col:13
	   Cell standardPriceCell = sheet.getRow(17).getCell(13);
	   if(standardPriceCell == null)
	   {
		   standardPriceCell=sheet.getRow(17).createCell(13);
		   standardPriceCell.setCellValue("4500");
	   }
//	   //총금액 row:18 / col:4
	   Cell totPriceCell = sheet.getRow(18).getCell(4);
	   if(totPriceCell == null)
	   {
		   totPriceCell=sheet.getRow(18).createCell(4);
		   totPriceCell.setCellValue(totPrice);
	   }

	   /*
	    * 2019-06-09
	    * file write
	    */
	   book.write(fos);
	   
	   
	   //Stream close;
	   fos.close();
	   fis.close();
	   
	   
		return null;
	}
	
	
}
