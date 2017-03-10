package com.ljheee.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ljheee.read.ReadXls;



public class SelectMajorServlet extends HttpServlet {


	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		System.out.println("select:"+name);
		ReadXls readXls = ReadXls.getInstance();
		
		List<?> list = readXls.getTeacherTeachesByName(name);
		req.setAttribute("majorList", list);
		req.getRequestDispatcher("course_schedule.jsp").forward(req, resp);
	}

}
