package com.testweb.user.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UserService {
	
	
	//User기능 처리용 인터페이스
	//응답,요청을 받아감
	public int execute(HttpServletRequest request, HttpServletResponse response);
	
	
}
