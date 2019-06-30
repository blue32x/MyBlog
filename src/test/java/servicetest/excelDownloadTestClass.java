package servicetest;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.Practice.MyBlog.bean.CommonBean;
import com.Practice.MyBlog.bean.dto.FileWriteIO;
import com.Practice.MyBlog.common.ExtSpringJUnit4ClassRunner;
import com.Practice.MyBlog.enums.ConfigPath;

@RunWith(ExtSpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="file:src/main/webapp/WEB-INF/spring/**/*-config.xml")
public class excelDownloadTestClass {
	
	private static final Logger logger = LoggerFactory.getLogger(excelDownloadTestClass.class);
	@Autowired
	CommonBean commonBean;
	
	@Test
	public void getexcelTest()
	{
		//Assert.assertEquals("MyBatisPath", ConfigPath.EXCEL_TEMPLATE.getName());
		
		try {
			commonBean.downloadExcel(_makeTestDate(),"Company_Name");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info("#SHCHOI# exception : [{}]",e);
		}
	}
	
	
	private List<FileWriteIO> _makeTestDate()
	{
		
		List<FileWriteIO> result = new ArrayList<FileWriteIO>();
		
		FileWriteIO data1 = new FileWriteIO();
		 data1.setPrice(1000);
		 data1.setCount(1);
		 data1.setOrderDate("20190605");
		 result.add(data1);
		FileWriteIO data2 = new FileWriteIO();
		 data2.setPrice(1500);
		 data2.setCount(1);
		 data2.setOrderDate("20190607");
		 result.add(data2);
		 
		 
		 FileWriteIO data3 = new FileWriteIO();
		 data3.setPrice(1500);
		 data3.setCount(2);
		 data3.setOrderDate("20190605");
		 result.add(data3);
		return result;
	}
}
