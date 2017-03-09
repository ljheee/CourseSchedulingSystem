package com.ljheee.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MyServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	String pageType="";
	
	String input_name;
	String input_pass;
	
	String tempDirectoryPath = null;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		pageType = request.getParameter("yemian");
//		HttpSession  session = request.getSession();
		
		
	}

	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		pageType = request.getParameter("yemian");
		HttpSession  session = request.getSession();
		System.out.println(pageType);
		
		
		if(pageType.equals("login")){
			input_name =request.getParameter("userName");
			input_pass =request.getParameter("password");
			
			if(input_name.equals("ljh")&&input_pass.equals("123")){
				session.setAttribute("dba", "dba");
				pageType="";
				request.getRequestDispatcher("/index.jsp").forward(request, response);
			}else{
				session.setAttribute("login", "login fail......");
				pageType="";
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}
		}
			
	}

	

}
