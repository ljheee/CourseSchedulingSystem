package com.ljheee.web;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ljheee.db.DbUtil;
import com.ljheee.db.UsedClassRoomException;
import com.ljheee.util.StringUtil;

import net.sf.json.JSONArray;

@WebServlet("/writeDbServlet")
public class WriteDbServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String teacherName = request.getParameter("teacherName");
		String majorName = request.getParameter("majorName");//nullPointer
		int beginWeek = 0;
		int endWeek = 0;
		try {
			beginWeek = Integer.parseInt(request.getParameter("beginWeek"));
			endWeek = Integer.parseInt(request.getParameter("endWeek"));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
		String myRoom = request.getParameter("myroom");
		String okTime = request.getParameter("oktime");
		StringUtil.RowCol rc = StringUtil.getRowCol(okTime);
		
		boolean result = false;
		String errorMsg = null;
		
		try {
			//写入数据库时，再检查
			if (DbUtil.checkTable(beginWeek, endWeek, rc.row, rc.col, myRoom)) {
				result = DbUtil.sureRoom(beginWeek, endWeek, rc.row, rc.col, myRoom);
			}
		} catch (UsedClassRoomException e) {
			errorMsg = e.getMessage();
			e.printStackTrace();
		}
		
		
		if (result == true) {//Todo 记录下老师-专业-所选时间-实验室
			System.out.println(teacherName+"-"+ majorName+"-"+beginWeek+"-"+beginWeek+"-"+rc.row+"-"+rc.col+"-"+myRoom);
			ArrayList<String> list = new ArrayList<>();
			list.add("选择成功,已写入DB");
			
			
			JSONArray json = JSONArray.fromObject(list);//发送true
			response.getWriter().print(json);
			response.getWriter().flush();
			return;
			
		}else{
			ArrayList<String> list = new ArrayList<>();
			list.add(errorMsg);
			
			JSONArray json = JSONArray.fromObject(list);//发送具体的实验室冲突:
			response.getWriter().print(json);			//ClassRoom was Used. 周数1 星期=4 节次=1(1代表第1-2节;2代表3-4节；以此类推...)
			response.getWriter().flush();
			return;
		}
	}
	

}
