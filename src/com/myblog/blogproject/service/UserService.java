package com.myblog.blogproject.service;

import com.myblog.blogproject.domain.user.User;
import com.myblog.blogproject.domain.user.UserDao;

public class UserService {
	
	private UserDao userDao;
	
	public UserService() {
		userDao = new UserDao();
	}
	
	public int 회원가입(User user) {
		return userDao.insert(user);
	}
	
	public int 아이디중복체크(String username) {
		return userDao.findByUsername(username);
	}
}
