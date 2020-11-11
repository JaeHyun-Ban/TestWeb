package com.testweb.user.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;

import com.testweb.user.model.UserDAO;
import com.testweb.user.model.UserVO;

public class UserUpdateServiceImpl implements UserService {

	@Override
	public int execute(HttpServletRequest request, HttpServletResponse response) {
		
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String pwcheck = request.getParameter("pwcheck");
		String email = request.getParameter("email");
		String eaddr = request.getParameter("eaddr");
		String phone1 = request.getParameter("phone1");
		String phone2 = request.getParameter("phone2");
		String phone3 = request.getParameter("phone3");
		String addr_basic = request.getParameter("addr_basic");
		String addr_detail = request.getParameter("addr_detail");
		
		UserDAO dao = UserDAO.getInstance();
		
		if(password.equals(pwcheck)) {
			UserVO vo = new UserVO(id, password, name, phone1, phone2, phone3, email, eaddr, addr_basic, addr_detail, null);
			dao.update(vo);
			
			HttpSession session = request.getSession();
			session.setAttribute("login", vo);//세션 갱신

			return 1;
		} else {
			
			return 0;
		}

	}

}










































