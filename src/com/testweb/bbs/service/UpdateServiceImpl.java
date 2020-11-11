package com.testweb.bbs.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.testweb.bbs.model.BbsDAO;

public class UpdateServiceImpl implements BbsService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		//글 번호를 받아서 게시글 수정
		int bno = Integer.parseInt(request.getParameter("bno")); 
		
		//수정할 값 받아오기
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		BbsDAO dao = BbsDAO.getInstance();
		dao.update(bno, title, content);
		
		

	}

}
