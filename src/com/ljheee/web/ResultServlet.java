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
import com.ljheee.bean.TheoryTeacher;
import com.ljheee.db.DbUtil;
import com.ljheee.read.Big2SmallTable;
import com.ljheee.util.MatrixUtil;
import com.ljheee.util.StringUtil;

import net.sf.json.JSONArray;
/**
 * 
 * @author ljheee
 *
 */
@WebServlet("/resultServlet")
public class ResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	Big2SmallTable big2SmallTable = null;
	
	
	@Override
	public void init() throws ServletException {
		super.init();
		big2SmallTable = Big2SmallTable.getInstance(new File(this.getServletContext().getRealPath("/")+"/xlsFiles", "1.xls"));
		big2SmallTable.init();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		
		String teacherName = request.getParameter("teacherName");
		String majorName = request.getParameter("majorName").split("#")[0];
		int beginWeek = 0;
		int endWeek = 0;
		try {
			beginWeek = Integer.parseInt(request.getParameter("beginWeek"));
			endWeek = Integer.parseInt(request.getParameter("endWeek"));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
		//Todo 添加数据库查询的实验室结果（beginWeek-endWeek）
		
		
		ArrayList<String> list = new ArrayList<>();
//		Big2SmallTable big2SmallTable = Big2SmallTable.getInstance(new File(this.getServletContext().getRealPath("/")+"/xlsFiles", "1.xls"));
//		big2SmallTable.init();
		TheoryTeacher tt = big2SmallTable.getTheoryTeacher(teacherName);//"黄华军"
		TheoryMajor tm = big2SmallTable.getTheoryMajor(majorName);//"2014级软件工程"
		MatrixUtil.firstCalculate(tt, tm);
		int[][] free = MatrixUtil.getResult(beginWeek, endWeek);//起始周
		for (int i = 0; i < free.length; i++) {
			for (int j = 0; j < free[0].length; j++) {
				if(free[i][j]==0){//okOption
					list.add(StringUtil.getWeekAndJieCi2(i, j));
					if(DbUtil.checkTable(beginWeek,endWeek,i,j));
				}
			}
		}
		JSONArray jsonArray = JSONArray.fromObject(list);
		response.getWriter().print(jsonArray);
		response.getWriter().flush();
		big2SmallTable.close();
	}

	
	
}
