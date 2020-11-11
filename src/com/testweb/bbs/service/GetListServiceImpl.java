package com.testweb.bbs.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.testweb.bbs.model.BbsDAO;
import com.testweb.bbs.model.BbsVO;
import com.testweb.util.PageVO;

public class GetListServiceImpl implements BbsService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		BbsDAO dao = BbsDAO.getInstance();
		
		//페이징 처리 + 페이지 바 >> 기본값
		int pageNum = 1;//시작할 페이지
		int amount = 10;//한번에 볼 페이지의 수
	
		//파라미터가 넘어오면 받음
		if(request.getParameter("pageNum") != null || request.getParameter("amount") != null) {	
			
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
			amount = Integer.parseInt(request.getParameter("amount"));
				
		}
		
		//모든 게시글 가져오기
		ArrayList<BbsVO> list = dao.getList(pageNum, amount);
		
		//전체 게시글 수
		int total = dao.getTotal();
		//페이지 바를 처리할 pageVO
		PageVO pageVO = new PageVO(pageNum, amount, total);
		
		//가져온 게시글 전달
		request.setAttribute("list", list);
		//가져온 페이지 바 전달
		request.setAttribute("pageVO", pageVO);
		
		
		
		
		
		

	}

}








































