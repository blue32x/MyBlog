package com.Practice.MyBlog.service;

import com.Practice.MyBlog.service.dto.ExcelServiceIO;

public interface ExceldownloadService {
	
	public ExcelServiceIO downloadExcel(ExcelServiceIO excelServiceIO) throws Exception;
	
	public ExcelServiceIO downloadAllExcel(ExcelServiceIO excelServiceIO) throws Exception;

}
