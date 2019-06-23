package daotest;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.Practice.MyBlog.bean.CommonBean;
import com.Practice.MyBlog.common.ExtSpringJUnit4ClassRunner;
import com.Practice.MyBlog.error.CustomException;
import com.Practice.MyBlog.service.OrderService;
import com.Practice.MyBlog.service.dto.OrderContentsIO;

@RunWith(ExtSpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="file:src/main/webapp/WEB-INF/spring/**/*-config.xml")
public class OrderDaoTest_20190623 {

	private static final Logger logger = LoggerFactory.getLogger(OrderDaoTest_20190623.class);
	@Autowired
	CommonBean commonBean;
	@Autowired
	OrderService OrderService;
	@Test
	public void insertTest() throws IOException, CustomException
	{
		Assert.assertNotNull(commonBean);
		OrderContentsIO orderContentsIO = new OrderContentsIO();
		orderContentsIO.setOrderDt("20190623");
		orderContentsIO.setCompanyId("000000005");
		String orderId =commonBean.gererateOrderId(orderContentsIO);
	//	Assert.assertEquals("2019062300000001101", orderId);
		orderContentsIO.setOrderId(orderId);
		orderContentsIO.setOrderCnt(1);
		orderContentsIO.setSumOfPrice(5500);
		orderContentsIO.setCompanyNm("1");
		orderContentsIO.setCompanyTelNbr("0101");
		orderContentsIO.setLastChngTmstmp(commonBean.getTmstmp());
		
		
		OrderService.registOrder(orderContentsIO);
		
	}
	
	@Test
	public void updateTest() throws IOException, CustomException
	{
		OrderContentsIO orderContentsIO = new OrderContentsIO();
		Assert.assertNotNull(commonBean);
		orderContentsIO.setOrderDt("20190623");
		orderContentsIO.setOrderId("2019062300000000502");
		orderContentsIO.setOrderCnt(3);
		orderContentsIO.setSumOfPrice(11500);
		orderContentsIO.setLastChngTmstmp(commonBean.getTmstmp());
		
		
		OrderService.updateOrder(orderContentsIO);
	}
	
	@Test
	public void deleteTest() throws IOException, CustomException
	{
		OrderContentsIO orderContentsIO = new OrderContentsIO();
		Assert.assertNotNull(commonBean);
		orderContentsIO.setOrderId("2019062300000000501");
		
		
		OrderService.deleteOrder(orderContentsIO);
	}
	
	@Test
	public void selectTest() throws IOException
	{
		OrderContentsIO orderContentsIO = new OrderContentsIO();
		orderContentsIO.setOrderDt("20190623");
		orderContentsIO.setCompanyId("000000011");
		String orderId =commonBean.gererateOrderId(orderContentsIO);
		Assert.assertEquals("2019062300000001101", orderId);
	}
}
