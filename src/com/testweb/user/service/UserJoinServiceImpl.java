package com.testweb.user.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.testweb.user.model.UserDAO;
import com.testweb.user.model.UserVO;

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
				
		int result = dao.checkID(id);//id로 중복검사
		
		//중복o=1, 중복x=0
		if(result == 1) {
			return 1;
		} else {
			UserVO vo = new UserVO(id, password, name, phone1, phone2, phone3, email, eaddr, addr_basic, addr_detail, null);
			dao.join(vo);
			
			return 0;
		}
	}

}

































