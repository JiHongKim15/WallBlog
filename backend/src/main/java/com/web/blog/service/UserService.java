package com.web.blog.service;

import com.web.blog.model.user.SignupRequest;
import com.web.blog.model.user.Users;
public interface UserService {
	public void insert(SignupRequest signup);
	public Users login(Users users);
	public boolean uploadImg(SignupRequest signup);
}
