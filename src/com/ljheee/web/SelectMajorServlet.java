package com.ljheee.web;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ljheee.bean.Major;
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

	private ReadXls readXls;
	
	@Override
	public void init() throws ServletException {
		readXls = ReadXls.getInstance();
//		readXls.setXlsFile(new File(this.getServletContext().getRealPath("/")+"/xlsFiles", "2.xls"));
		super.init();
	}
	
	@Override
	public void destroy() {
		
		readXls.close();
		super.destroy();
	}
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		
		String teacherName = req.getParameter("name");
		System.out.println("select:"+teacherName);
		
		readXls.setXlsFile(new File(this.getServletContext().getRealPath("/")+"/xlsFiles", "2.xls"));
		List<Major> list = readXls.getTeacherTeachesByName(teacherName);//获取教师实验课程List<Major>
		JSONArray jsonArray = JSONArray.fromObject(list);
		resp.getWriter().print(jsonArray);
		resp.getWriter().flush();
	}

}
