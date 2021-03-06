package com.ljheee.util;


/**
 * 字符串工具类
 * @author ljheee
 *
 */
public class StringUtil {
	
	/**
	 * 根据单双周，返回int
	 * @param arg
	 * @return
	 */
	public static int getSingleDouble(String arg) {
		int result = 0;
		switch (arg) {
		case "":
			result = 0;//不分单双周
			break;
		case "单":
			result = 1;//单周
			break;
		case "双":
			result = 2;//双周
			break;
		default:
			System.out.println("getSingleDouble=default");
			break;
		}
		return result;
	}


	/**
	 * 根据节次，转化为上课时间段0-4
	 * @param arg
	 * @return
	 */
	public static int getJieCi(String arg) {
		int result = 0;
		switch (arg) {
		case "第1,2节":
			result = 0;
			break;
		case "第3,4节":
			result = 1;
			break;
		case "第5,6节":
			result = 2;
			break;
		case "第7,8节":
			result = 3;
			break;
		case "第9,10节":
			result = 4;
			break;
		default:
			System.out.println("getJieCi=default");
			break;
		}
		return result;
	}

	/**
	 * 星期一 至星期日=0-6
	 * @param arg
	 * @return
	 */
	public static int getDayOfWeek(String arg) {
		int result = 0;
		switch (arg) {
		case "星期一":
			result = 0;
			break;
		case "星期二":
			result = 1;
			break;
		case "星期三":
			result = 2;
			break;
		case "星期四":
			result = 3;
			break;
		case "星期五":
			result = 4;
			break;
		case "星期六":
			result = 5;
			break;
		case "星期日":
			result = 6;
			break;
		default:
			break;
		}
		return result;
	}

	/**
	 * 根据二维矩阵x,y，返回此课=星期几   第几-几节
	 * 例如：由(3,1) 返回星期一第5、6节
	 * @param x 课表横排，取值0-4[对应5个上课时间段]
	 * @param y 课表竖排，取值0-6[对应一周7天]
	 * @return
	 */
	public static String[] getWeekAndJieCi(int x,int y) {
		
		y--;
		x--;
		String[] xx = {"第1,2节","第3,4节","第5,6节","第7,8节","第9,10节"};
		String[] yy = {"星期一","星期二","星期三","星期四","星期五","星期六","星期日"};
		String[] result = new String[2];
		
		result[0] = yy[y];
		result[1] = xx[x];
		
		return result;
	}
	
	/**
	 * 根据二维矩阵x,y，返回此课=星期几   第几-几节
	 * 例如：由(3,1) 返回星期一第5、6节
	 * @param x 课表横排row，取值1-5[对应5个上课时间段]
	 * @param y 课表竖排col，取值1-7[对应一周7天]
	 * @return
	 */
	public static String getWeekAndJieCi2(int x,int y) {
		String[] result = getWeekAndJieCi(x,y);
		return result[0]+result[1];
	}
	
	public static String array2String(String[] obj){
		String result ="";
		for (String object : obj) {
			result+=object.toString();
		}
		return result;
	}
	
	public static class RowCol{
		public int row,col;
	}
	
	/**
	 * 由“星期二第7,8节”--->(row,col)
	 * @param okTime
	 * @return
	 */
	public static RowCol getRowCol(String okTime){
		RowCol rowCol = new RowCol();
		rowCol.col = getDayOfWeek(okTime.substring(0, 3)) + 1;//星期二
		rowCol.row = getJieCi(okTime.substring(3)) + 1;//第7,8节
		return rowCol;
	}
}
