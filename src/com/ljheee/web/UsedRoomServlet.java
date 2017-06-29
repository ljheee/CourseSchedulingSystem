package com.ljheee.web;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ljheee.db.DbUtil;
import com.ljheee.util.StringUtil;

import net.sf.json.JSONArray;

@WebServlet("/usedRoomServlet")
public class UsedRoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
//		String teacherName = request.getParameter("teacherName");
//		String majorName = request.getParameter("majorName").split("#")[0];//nullPointer
		int beginWeek = 0;
		int endWeek = 0;
		try {
			beginWeek = Integer.parseInt(request.getParameter("beginWeek"));
			endWeek = Integer.parseInt(request.getParameter("endWeek"));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
		String okTime = request.getParameter("oktime");
		StringUtil.RowCol rc = StringUtil.getRowCol(okTime);
		
		Set<String> usedRoomSet = null;
		
		//Todo 添加数据库查询的实验室结果（beginWeek-endWeek）
		try {
			System.out.println("beginWeek="+beginWeek+",endWeek="+endWeek+",row="+rc.row+",col="+rc.col);
//			if (DbUtil.checkTable(beginWeek , endWeek, rc.row , rc.col, "609")) {
//				DbUtil.sureRoom(beginWeek , endWeek, rc.row , rc.col, "609");
//			}
			
			usedRoomSet = DbUtil.getAllUsedRooms(beginWeek, endWeek, rc.row, rc.col);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		JSONArray jsonArray = JSONArray.fromObject(usedRoomSet);
		response.getWriter().print(jsonArray);
		response.getWriter().flush();
	}

}
