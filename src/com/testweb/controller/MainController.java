package com.testweb.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.testweb.main.service.MainListServiceImpl;
import com.testweb.main.service.MainService;

@WebServlet("")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MainController() {
        super();
        
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		disPatcherServlet(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		disPatcherServlet(request, response);
	}
	
	public void disPatcherServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1.인코딩
		request.setCharacterEncoding("utf-8");
		
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String command = uri.substring(conPath.length());
		
		System.out.println("command: " + command);
		
		
		MainService service = null;
		
		if(command.equals("/")) {
			
			service = new MainListServiceImpl();
			service.execute(request, response);
			
			request.getRequestDispatcher("index.jsp").forward(request, response);
			
		}
	
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	

}
