package com.myblog.blogproject.service;

import com.myblog.blogproject.domain.user.User;
import com.myblog.blogproject.domain.user.UserDao;
import com.myblog.blogproject.domain.user.dto.LoginReqDto;
import com.myblog.blogproject.domain.user.dto.LoginRespDto;

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
	
	public LoginRespDto 로그인(LoginReqDto dto) {
		return userDao.login(dto);
	}
}
