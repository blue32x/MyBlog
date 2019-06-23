package com.Practice.MyBlog.dao;

import org.springframework.stereotype.Repository;

import com.Practice.MyBlog.dao.dto.MemberInfo;

public interface MemberDao {
	
	public void insertMember(MemberInfo memberInfo);
	public void updateMember(String userId);
	public MemberInfo getMember(String userId);
	public void deleteMember(String userId);

}
