package com.testweb.bbs.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.testweb.bbs.model.BbsDAO;
import com.testweb.user.model.UserVO;

public class UpdateServiceImpl implements BbsService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		//글 번호를 받아서 게시글 수정
		int bno = Integer.parseInt(request.getParameter("bno")); 
		String checkid = request.getParameter("id");
		
		HttpSession session = request.getSession();
		UserVO login = (UserVO) session.getAttribute("login");
		String id = login.getId();
		
		//작성자 아이디 확인
		if(id.equals(checkid)) {
			//수정할 값 받아오기
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			
			BbsDAO dao = BbsDAO.getInstance();
			int result = dao.update(bno, title, content);
			if(result == 1) {
				request.setAttribute("update", result);
			} else {
				request.setAttribute("update", result);
			}
		} else {
			try {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('작성자가 아닙니다');");
				out.println("</script>");
				response.sendRedirect("content.bbs?bno="+bno);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		
		
		
		

	}

}
