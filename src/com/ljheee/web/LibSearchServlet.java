package com.ljheee.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ljheee.db.DbUtil;
import com.ljheee.db.UsedClassRoomException;

import net.sf.json.JSONArray;

@WebServlet("/libSearchServlet")
public class LibSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String libRoom = request.getParameter("libRoom");
		System.out.println("libRoom="+libRoom);
		
		int selWeek = 0,row = 0,col = 0;
		try {
			selWeek = Integer.parseInt(request.getParameter("selWeek"));
			row = Integer.parseInt(request.getParameter("row"));
			col = Integer.parseInt(request.getParameter("col"));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	
		Boolean result = false;
		try {
			result = DbUtil.checkClass(selWeek, row, col, libRoom);
		} catch (UsedClassRoomException e) {
			e.printStackTrace();
		}
		
		JSONArray jsonArray = JSONArray.fromObject(result);
		response.getWriter().print(jsonArray);
		response.getWriter().flush();
	}

}
