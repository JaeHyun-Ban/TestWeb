package com.testweb.util.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.testweb.user.model.UserVO;

//필터를 하고싶은 곳 - 작성, 수정, 삭제
@WebFilter({"/bbs/write.bbs", "/bbs/modify.bbs", "/bbs/delete.bbs"})
public class BbsFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		//응답,요청 가져오기(Servlet -> HttpServlet)
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		//로그인 세션 가져오기(권한)
		HttpSession session = req.getSession();
		UserVO login = (UserVO) session.getAttribute("login");
		
		if(login == null) {//null? > 로그인x
			res.setContentType("text/html; charset=UTF-8");
			PrintWriter out = res.getWriter();
			out.println("<script>");
			out.println("alert('로그인이 필요합니다');");
			out.println("location.href='/TestWeb/user/login.user';");
			out.println("</script>");
			
			return; //종료(중요)
		}
		
		chain.doFilter(request, response);
		
		
	}

}
















































