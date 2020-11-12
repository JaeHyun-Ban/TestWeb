package com.testweb.user.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.testweb.user.model.UserDAO;
import com.testweb.user.model.UserVO;
import com.testweb.util.checkString;

public class UserJoinServiceImpl implements UserService {

	@Override
	public int execute(HttpServletRequest request, HttpServletResponse response) {
		//회원가입 데이터 받기
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String phone1 = request.getParameter("phone1");
		String phone2 = request.getParameter("phone2");
		String phone3 = request.getParameter("phone3");
		String email = request.getParameter("email");
		String eaddr = request.getParameter("eaddr");
		String addr_basic = request.getParameter("addr-basic");
		String addr_detail = request.getParameter("addr-detail");
		
		UserDAO dao = UserDAO.getInstance();//dao생성
		
		//공백검사
		if(checkString.str(id) || checkString.str(password) || checkString.str(name)
				|| checkString.str(phone1) || checkString.str(phone2) || checkString.str(phone3)
				|| checkString.str(email) || checkString.str(addr_basic) || checkString.str(addr_detail)) 
		{
			return 2;
		}
		
		
		//id로 중복검사
		int result = dao.checkID(id);
		if(result == 0) {//중복x
			UserVO vo = new UserVO(id, password, name, phone1, phone2, phone3, email, eaddr, addr_basic, addr_detail, null);
			int num = dao.join(vo);//join진행
			
			if(num == 1) {//join 성공(required만 다채움 > 성공)
				return 1;
			} else {//join 실패 
				return 0;
			}	
		} else {//중복된 o
			return 11;
		}
		
	}

}

































