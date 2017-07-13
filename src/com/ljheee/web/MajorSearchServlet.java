package com.ljheee.web;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ljheee.bean.TheoryMajor;
import com.ljheee.bean.WeekClass;
import com.ljheee.read.Big2SmallTable;
import com.ljheee.util.StringUtil;

import net.sf.json.JSONArray;

@WebServlet("/majorSearchServlet")
public class MajorSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

private Big2SmallTable big2SmallTable = null;
	
	
	@Override
	public void init() throws ServletException {
		//全校理论课，计划表
		big2SmallTable = Big2SmallTable.getInstance(new File(this.getServletContext().getRealPath("/")+"/xlsFiles", "1.xls"));
		big2SmallTable.init();
		super.init();
	}
	
	@Override
	public void destroy() {
		big2SmallTable.close();
		super.destroy();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String levelAndName = request.getParameter("majorName").split("#")[0];
		System.out.println("levelAndName="+levelAndName);
		
		int beginWeek = 0;
		try {
			beginWeek = Integer.parseInt(request.getParameter("beginWeek"));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
		
		ArrayList<String> list = new ArrayList<>();
		TheoryMajor tm = big2SmallTable.getTheoryMajor(levelAndName);//2014级软件工程
		WeekClass wc = tm.getTheoryTable(beginWeek);//获取该专业，指定周数下的  空课时间段
		
		int arr[][] = wc.week;
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				if(arr[i][j] == 0){//major free time
					list.add(StringUtil.getWeekAndJieCi2(i+1, j+1));//由(3,1) 返回星期一第5、6节
				}
			}
		}
		
		JSONArray jsonArray = JSONArray.fromObject(list);
		response.getWriter().print(jsonArray);
		response.getWriter().flush();
	}

}
