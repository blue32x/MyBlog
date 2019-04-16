package com.Practice.MyBlog.service;

import org.springframework.ui.Model;

public interface MemberService {

	public void signUpMember(Model model);
	public void signInMember(Model model);
	public Model getMember(String userId);
}
