package com.web.blog.dao.user;

import org.apache.ibatis.annotations.Mapper;

import com.web.blog.model.user.EmailCheck;
import com.web.blog.model.user.SignupRequest;
import com.web.blog.model.user.Users;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserSignup {
	Users login(Users users);
	boolean insert(SignupRequest request);
	SignupRequest select(String uid, String email);
	SignupRequest selectUid(String uid);
	SignupRequest selectEmail(String email);
	boolean delete(String uid);
	boolean update(SignupRequest request);
	
	
	// email check
	EmailCheck emailselect(String email);
	boolean emailupdate(EmailCheck emailcheck);
	boolean emailinsert(EmailCheck emailcheck);
	boolean emaildelete(String email);
	
	//image
	boolean uploadImg(SignupRequest image);
}
