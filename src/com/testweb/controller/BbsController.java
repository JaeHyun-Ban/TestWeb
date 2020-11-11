package com.testweb.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.testweb.bbs.service.BbsService;
import com.testweb.bbs.service.ContentServiceImpl;
import com.testweb.bbs.service.DeleteServiceImpl;
import com.testweb.bbs.service.GetListServiceImpl;
import com.testweb.bbs.service.ModifyCheckServiceImpl;
import com.testweb.bbs.service.ModifyServiceImpl;
import com.testweb.bbs.service.RegistServiceImpl;
import com.testweb.bbs.service.UpdateServiceImpl;
import com.testweb.user.service.UserJoinServiceImpl;
import com.testweb.user.service.UserService;

@WebServlet("*.bbs")
public class BbsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BbsController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		dispatcherServlet(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		dispatcherServlet(request, response);
	}

	public void dispatcherServlet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// get과post를 여기서 한번에 처리
		// 1.인코딩
		request.setCharacterEncoding("utf-8");

		// 2.요청분기
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String command = uri.substring(conPath.length());

		System.out.println("command:" + command);
		BbsService service = null;

		// ※MVC2 - forward이동이 기본※
		// 게시판 리스트 화면 처리 + 글 가져오기
		if (command.equals("/bbs/list.bbs")) {
			service = new GetListServiceImpl();
			service.execute(request, response);

			request.getRequestDispatcher("bbs.jsp").forward(request, response);

			// 게시물 상세보기 화면 + 글 가져오기
		} else if (command.equals("/bbs/content.bbs")) {
			service = new ContentServiceImpl();
			service.execute(request, response);

			request.getRequestDispatcher("bbs_content.jsp").forward(request, response);

			// 게시물 작성 화면 처리
		} else if (command.equals("/bbs/write.bbs")) {
			request.getRequestDispatcher("bbs_write.jsp").forward(request, response);

			// 게시물 작성 처리
		} else if (command.equals("/bbs/regist.bbs")) {
			service = new RegistServiceImpl();
			service.execute(request, response);
			
			response.sendRedirect("list.bbs");
			
			//////////////////게시물 수정 화면 처리//////////////////
		} else if (command.equals("/bbs/modify.bbs")) {
			
			//로그인 id, 작성자를 비교 
			service = new ModifyCheckServiceImpl();
			service.execute(request, response);
			int result = (int) request.getAttribute("check");
			
			if(result == 1) {//작성자 = 로그인
				service = new ContentServiceImpl();
				service.execute(request, response);
				
				request.getRequestDispatcher("bbs_modify.jsp").forward(request, response);
			} else {//작성자 != 로그인
				request.setAttribute("msg", "수정할 권한이 존재하지 않습니다");
				
				service = new ContentServiceImpl();
				service.execute(request, response);
				
				request.getRequestDispatcher("bbs_content.jsp").forward(request, response);
				
			}
			
			
			
			
//			response.setContentType("text/html; charset=UTF-8");
//			PrintWriter out = response.getWriter();
//			if(result == 1) {//작성자가 같을 시
//				// 글을 불러오는 content서비스를 재활용
//				service = new ContentServiceImpl();
//				service.execute(request, response);
//				request.getRequestDispatcher("bbs_modify.jsp").forward(request, response);
//			}else {//작성자가 다를 시
//				out.println("<script>");
//				out.println("alert('작성자가 아닙니다');");
//				out.println("</script>");
//				response.sendRedirect("content.bbs?bno="+request.getParameter("bno"));
//				
//			}
				

			// 게시글 수정 처리
		} else if (command.equals("/bbs/update.bbs")) {
			service = new UpdateServiceImpl();
			service.execute(request, response);

			int result = (int) request.getAttribute("update");

			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			if (result == 1) {// 성공
				out.println("<script>");
				out.println("alert('글이 수정되었습니다');");
				out.println("</script>");
				response.sendRedirect("content.bbs?bno=" + request.getParameter("bno"));
			}

			// 게시글 삭제 + 리스트 이동
		} else if (command.equals("/bbs/delete.bbs")) {
			service = new DeleteServiceImpl();
			service.execute(request, response);
			int result = (int) request.getAttribute("delete");

			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			if (result == 1) {// 성공
				out.println("<script>");
				out.println("alert('글이 삭제되었습니다');");
				out.println("location.href='list.bbs';");
				out.println("</script>");

			} else {// 실패
				out.println("<script>");
				out.println("alert('글이 삭제되지 않았습니다');");
				out.println("location.href='list.bbs';");
				out.println("</script>");
			}

		}

	}

}
