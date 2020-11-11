package com.testweb.bbs.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.testweb.bbs.model.BbsDAO;
import com.testweb.user.model.UserDAO;

public class DeleteServiceImpl implements BbsService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		//삭제할 글 번호
		int bno = Integer.parseInt(request.getParameter("bno")); 
		System.out.println(bno);
		
		BbsDAO dao = BbsDAO.getInstance();
		int result = dao.delete(bno);
		
		if(result == 1) {//성공
			request.setAttribute("delete", result);
		} else {//실패
			result = 0;
			request.setAttribute("delete", result);
		}
		
		

	}

}











