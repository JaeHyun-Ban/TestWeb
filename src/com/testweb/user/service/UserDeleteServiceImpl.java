package com.testweb.user.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.testweb.user.model.UserDAO;
import com.testweb.user.model.UserVO;

public class UserDeleteServiceImpl implements UserService {

	@Override
	public int execute(HttpServletRequest request, HttpServletResponse response) {
		
		//회원 삭제 처리
		String password = request.getParameter("password");//입력받은 비밀번호
		
		HttpSession session = request.getSession();//로그인 세션 비밀번호
		UserVO vo = (UserVO) session.getAttribute("login");
		String check = vo.getPassword();
		
		UserDAO dao = UserDAO.getInstance();
		if(password.equals(check)) {//비밀번호가 일치
			String id = vo.getId();
			int result = dao.delete(id, password);//delete진행
			
			if(result == 1) {//delete성공
				session.invalidate();//세션 삭제
				return 1;
			} else {
				return 0;
			}
		} else {//비밀번호가 일치하지 않음
			return 0;
		}
	}

}
