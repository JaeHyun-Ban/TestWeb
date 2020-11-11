package com.testweb.user.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.testweb.user.model.UserDAO;
import com.testweb.user.model.UserVO;

public class UserLoginServiceImpl implements UserService {

	@Override
	public int execute(HttpServletRequest request, HttpServletResponse response) {
		
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		UserDAO dao = UserDAO.getInstance();
		UserVO user = dao.login(id, password);//1=존재
		
		if(user != null) {//성공
			//로그인 정보를 세션에 담기
			HttpSession session = request.getSession();
			session.setAttribute("login", user);
			return 1;
			
		} else {//실패
			return 0;
		}

	}

}











































