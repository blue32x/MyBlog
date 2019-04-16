package com.Practice.MyBlog.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Practice.MyBlog.service.TestService;


@Controller
public class TestController {

	private static final Logger logger = LoggerFactory.getLogger(TestController.class);
	

//	@Autowired
//	private TestService testService;
	/**
	 * Simply selects the home view to render by returning its name.
	 * @throws IOException 
	 */
	@RequestMapping(value = "/serviceEndPoint/test.do")
	public String home(Locale locale, Model model) throws IOException {
		logger.info("Welcome home! The client locale is {}.", locale);
		logger.debug("Welcome home! The client lcaole is {}.", locale);
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		   
		model.addAttribute("serverTime", formattedDate );
		
		logger.info("Welcome CONTROLLER START");
//		testService.test();
		logger.info("Welcome CONTROLLER END");
		
		
		
		return "home";
	}
	
}
