package com.testweb.user.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.testweb.bbs.model.BbsDAO;
import com.testweb.bbs.model.BbsVO;
import com.testweb.bbs.service.BbsService;
import com.testweb.user.model.UserDAO;
import com.testweb.user.model.UserVO;

public class GetMyPageServiceImpl implements BbsService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		//내가 작성한 글 전부 가져오기
		
		HttpSession session = request.getSession();
		UserVO vo = (UserVO) session.getAttribute("login");
		String id = vo.getId();
		
		BbsDAO dao = BbsDAO.getInstance();
		ArrayList<BbsVO> list = dao.mypage(id);
		
		if(list != null) { //성공
			request.setAttribute("mypage", list);
			
		} else { //실패
			return;
		}

	}

}
