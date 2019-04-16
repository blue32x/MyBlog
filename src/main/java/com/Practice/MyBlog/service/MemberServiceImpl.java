package com.Practice.MyBlog.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.Practice.MyBlog.bean.MemberBean;


@Service
public class MemberServiceImpl implements MemberService {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);
	@Autowired
	MemberBean memberBean;
	public void signUpMember(Model model) {
		// TODO Auto-generated method stub
		if(memberBean.isMember("test"))
		{
			
		}
		else
		{
			
		}
	}

	public void signInMember(Model model) {
		// TODO Auto-generated method stub
//		if(memberBean.isMember("test"))
//		{
//			try {
//				
//				/*
//				 * 1. try login .....
//				 * 	1-1 check password
//				 * 	1-2	check user statement
//				 * 2. if login success then init login cnt;
//				 */
//				
//			}catch() {
//				/*
//				 *  1. failed login
//				 *  2. increment login cnt
//				 */
//				
//				
//			}
//		}
//		else
//		{
//			logger.debug("member가 아닙니다.");
//		}
	}

	public Model getMember(String userId) {
		// TODO Auto-generated method stub
		if(memberBean.isMember("test"))
		{
			
		}
		else
		{
			
		}
		
		return null;

	}

}
