package com.web.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.blog.dao.user.UserSignup;
import com.web.blog.model.user.SignupRequest;
import com.web.blog.model.user.Users;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserSignup userSignup;
	
	@Override
	public void insert(SignupRequest signup) {
		// TODO Auto-generated method stub
		//user.insert(signup);
	}

	@Override
	public Users login(Users users){
		return userSignup.login(users);
	}

	@Override
	public boolean uploadImg(SignupRequest signup) {
		// TODO Auto-generated method stub
		return userSignup.uploadImg(signup);
	}

}
