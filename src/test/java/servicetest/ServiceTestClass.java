package servicetest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.Practice.MyBlog.bean.DataSourceBean;
import com.Practice.MyBlog.common.ExtSpringJUnit4ClassRunner;
import com.Practice.MyBlog.dao.CompanyDao;
import com.Practice.MyBlog.enums.ConfigPath;
import com.Practice.MyBlog.service.dto.CompanyServiceIO;

@RunWith(ExtSpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="file:src/main/webapp/WEB-INF/spring/**/*-config.xml")
//@ContextConfiguration(locations = { "/junit=config.xml" })
public class ServiceTestClass {
	
	private static final Logger logger = LoggerFactory.getLogger(ServiceTestClass.class);
	@Autowired
	DataSourceBean dsBean;
//	
	
	@Test
	public void getConfigPathTest()
	{
		Assert.assertEquals("MyBatisPath", ConfigPath.MYBATIS_CONFIG.getName());
		Assert.assertEquals("spring/mybatis-config.xml", ConfigPath.MYBATIS_CONFIG.getValue());
	}
	
	 @Test
	public void getSessionTest() throws IOException
	{
//		logger.debug("current mybatis path : {}", ConfigPath.MYBATIS_CONFIG.getValue());
		SqlSession session =  dsBean.getSessionFactory().openSession();
		Assert.assertNotNull(session);
	}
	 @Test
	 public void getSqlExecTest() throws IOException
	 {
		 CompanyServiceIO companyServiceIO = new CompanyServiceIO();
		 SqlSession session =  dsBean.getSessionFactory().openSession();
		 List<CompanyServiceIO> results = new ArrayList<CompanyServiceIO>();
		 try {
			  CompanyDao mapper = session.getMapper(CompanyDao.class);
			  
			  results = (List<CompanyServiceIO>)mapper.get(companyServiceIO);
			  
			  for(CompanyServiceIO  item : results)
			  {
				  logger.info("TESTER : {}",item);
			  }
			//  return results;
		} finally {
		  session.close();
		}
		 Assert.assertNotNull(results);
	 }
	 

}
