package com.testweb.user.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;

import com.testweb.user.model.UserDAO;
import com.testweb.user.model.UserVO;
import com.testweb.util.checkString;

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
		
		//공백 검사>>모두
		if(checkString.str(name) || checkString.str(password) || checkString.str(pwcheck)
				|| checkString.str(email) || checkString.str(eaddr) || checkString.str(phone1)
				|| checkString.str(phone2) || checkString.str(phone3) || checkString.str(addr_basic)
				|| checkString.str(addr_detail))
		{
			return 2;
		};
		
		
		System.out.println(email);
		UserDAO dao = UserDAO.getInstance();
		
		if(password.equals(pwcheck)) {//비밀번호 확인 성공
			
			UserVO vo = new UserVO(id, password, name, phone1, phone2, phone3, email, eaddr, addr_basic, addr_detail, null);
			int result = dao.update(vo);//업데이트 진행
			
			if(result == 1) {//update 성공
				HttpSession session = request.getSession();
				session.setAttribute("login", vo);//세션 갱신
				
				return 1;
			} else {//update 실패
				return 0;
			}
		} else {//비밀번호확인 틀림
			return 11;
		}
		
	}

}










































