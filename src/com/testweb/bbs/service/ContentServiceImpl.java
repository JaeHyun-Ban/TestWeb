package com.testweb.bbs.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.testweb.bbs.model.BbsDAO;
import com.testweb.bbs.model.BbsVO;

public class ContentServiceImpl implements BbsService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		//bno값을 통해 게시글 상세 불러오기
		int bno = Integer.parseInt(request.getParameter("bno"));
		
		BbsDAO dao = BbsDAO.getInstance();
		BbsVO vo =  dao.content(bno);
		
		//꺼낸 상세글 값 담아주기
		request.setAttribute("vo", vo);
		
		

	}

}
