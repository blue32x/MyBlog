package com.Practice.MyBlog.controller;

import java.io.IOException;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * <PRE>
 * 2019.02.23
 * RegistrationController : This Controller manages Our Member Registration.
 * for Example, Sign-up, Sign-in, membership withdrawal.
 * Our API will follow RESTFUL-API. 
 * methods : 1. signin(...)
 * 			 2. signUp(...)
 * 			 3. signOut(...)
 * 			 4. withdrawal(...)
 * 
 * Maybe, server receives Data at front like below URI.
 * sign - up 	http://www.blablah.com/member  				HTTP Method : POST
 * withdrawal 	http://www.blablah.com/member/{ID}       	HTTP Method : DELETE 
 *
 * 
 * login/out
 * sign - out 	http://www.blablah.com/signin 				HTTP Method : POST 
 * sign - in 	http://www.blablah.com/singout  			HTTP Method : POST
 * 
 * 
 * payments
 * payment 					http://www.blablah.com/payment  					HTTP Method : POST 
 * paymentinfos 			http://www.blablah.com/payments/{params}  			HTTP Method : GET 
 * paymentinfo	 			http://www.blablah.com/payments/{ID}  				HTTP Method : GET
 * paymentinfomodify 		http://www.blablah.com/payments/{ID}  				HTTP Method : PATCH  
 *  
 * </PRE>
 * @author shchoi54
 *
 */
@Controller
public class RegistrationController {
	private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);
	
	@RequestMapping(value = "/member", method=RequestMethod.POST)
	public String signup(Model model) throws IOException {
		
		logger.debug("member resgistration start");
		
		/*
		 * 1. Json
		 */
		Map<String,Object> attributesMap= model.asMap();
		
		if(attributesMap.containsKey("userId"))
		{
			throw new IllegalArgumentException("input userId");
		}
		
		if(attributesMap.containsKey("userName"))
		{
			throw new IllegalArgumentException("input userName");
		}
		if(attributesMap.containsKey("password"))
		{
			throw new IllegalArgumentException("input userPassword");
		}
		
		
		logger.debug("member resgistration end");
		
		return "home";
	}
	
	@RequestMapping(value = "/member/{memberId}", method=RequestMethod.GET)
	public String inquiry(Locale locale, Model model) throws IOException {

		return "home";
	}
	
	
	@RequestMapping(value = "/member/login", method=RequestMethod.POST)
	public String signin(Model model) throws IOException {

		/*
		 * 1 . password check
		 * 		1-1 incorrect passoword  --> redirect
		 * 		1-2 correct password 	 --> login success.
		 */ 
		Map<String,Object> attributesMap= model.asMap();

		if(attributesMap.containsKey("userId"))
		{
			throw new IllegalArgumentException("input userId");
		}
		if(attributesMap.containsKey("password"))
		{
			throw new IllegalArgumentException("input userPassword");
		}
		
		
		
		return "home";
	}
	
	
	
	

}
