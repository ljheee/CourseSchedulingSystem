package com.ljheee.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ljheee.read.ReadXls;

import net.sf.json.JSONArray;


/**
 * $.post('selectMajorServlet', {name: currTeacher} ,function(jsonArray)
 * 填充专业选择 下拉选单 
 * @author ljheee
 *
 */
public class SelectMajorServlet extends HttpServlet {


	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		
		String name = req.getParameter("name");
		System.out.println("select:"+name);
		ReadXls readXls = ReadXls.getInstance();
		
		List<?> list = readXls.getTeacherTeachesByName(name);//获取教师实验课程List<Major>
		JSONArray jsonArray = JSONArray.fromObject(list);
		resp.getWriter().print(jsonArray);
		resp.getWriter().flush();
//		req.setAttribute("majorList", list);
//		req.getRequestDispatcher("course_schedule.jsp").forward(req, resp);
	}

}
