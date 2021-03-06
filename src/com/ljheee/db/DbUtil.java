package com.ljheee.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 */
public class DbUtil {
	
	public static final String DB_URL = "jdbc:mysql://localhost:3306/courseschedule";
	public static final String USER = "root";
	public static final String PASS = "root";
	
	static Connection con;
	static PreparedStatement ps;
	static Statement sm;
	static ResultSet rs;
	static SimpleDateFormat sdf;
	
	static{
		sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(DB_URL , USER , PASS);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 写入单表，此实验室在此时间段可用
	 * @param weekIndex 周数
	 * @param row		节数
	 * @param col		星期
	 * @param classRoom	实验室
	 * @return
	 */
	public static boolean addClass2Db(int weekIndex,int row,int col,String classRoom){
		boolean result = false;
		
//		String sql2 = "update week1 set day1='609' where id=2";
		try {
			String rooms = getUsedRooms(weekIndex, row, col);
			sm = con.createStatement();
			String newRoom = (rooms==null ? classRoom : rooms+"-"+classRoom);
			result = sm.execute("update week"+weekIndex+" set day"+col+"='"+newRoom+"' where id="+row);

			if(result==false){
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(sm != null) sm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	/*
	 * 起始到结束周，写入排课
	 * 写入DB多张表
	 */
	public static synchronized boolean sureRoom(int begin, int end,int row,int col,String classRoom){
		boolean result = false;
		for (int i = begin; i <= end; i++) {
			addClass2Db(i, row, col, classRoom);//写入DB前已经检查过，此处直接写DB
		}
		result = true;
		return result;
	}
	
	/**
	 * 检查begin--end起始到结束周，classRoom是否都可用
	 * @param begin 起始周
	 * @param end	结束周
	 * @param row	节数
	 * @param col	星期
	 * @param classRoom	实验室
	 * @return
	 * @throws UsedClassRoomException 
	 */
	public static boolean checkTable(int begin, int end, int row, int col,String classRoom) throws UsedClassRoomException {
		boolean result = false;
		for (int i = begin; i <= end; i++) {
			if(checkClass(i, row, col ,classRoom)){
				result = true;
				continue;
			}else{
				result = false;
				break;
			}
		}
		return result;
	}
	
	/**
	 * 检查[单周] [row,col]节课，classRoom是否可用
	 * @param tableIndex  	[1-20周]
	 * @param row			[1-5]
	 * @param col			[1-7]
	 * @param classRoom		实验室
	 * @return				true代表classRoom无人用
	 * @throws UsedClassRoomException 
	 */
	public static boolean checkClass(int tableIndex, int row, int col,String classRoom) throws UsedClassRoomException {
		boolean result = false;
		try {
			sm = con.createStatement();
			//select day1 from week1 where id=3
			String sql = "select day"+col+" from week"+tableIndex+" where id="+row;
			ResultSet rs = sm.executeQuery(sql);
			rs.next();
			String rooms = rs.getString("day"+col);//day7
			if( rooms == null || !rooms.contains(classRoom)){//classRoom此时段无人用
				result = true;
			}else{
				throw new UsedClassRoomException(tableIndex,row,col);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) rs.close();
				if(sm!=null) sm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	//...........................................................................
	
	/**
	 * 获取单节，已用实验室
	 * @param weekIndex
	 * @param row
	 * @param col
	 * @return
	 */
	public static String getUsedRooms(int weekIndex,int row,int col){
		String result = null;
		try {
			sm = con.createStatement();
			ResultSet rs = sm.executeQuery("SELECT day"+col+" FROM week"+weekIndex+" where id="+row);
			rs.next();
			result = rs.getString("day"+col);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) rs.close();
				if(sm!=null) sm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	/**
	 * 返回 起始到结束周，所有已用实验室（不重复）
	 * @param begin
	 * @param end
	 * @param row
	 * @param col
	 * @return
	 */
	public static Set<String> getAllUsedRooms(int begin ,int end ,int row,int col){
		Set<String> set = new HashSet<>();
		
		for (int i = begin; i <= end; i++) {
			String room = getUsedRooms(i, row, col);
			if (room != null) {
				List<String> str = Arrays.asList(room.split("-")); 
				set.addAll(str);
			}
		}
		
		return set;
	}
	
	/**
	 * 检查 起始周到结束周，classRoom时候都可用    （方法调用10ms左右）
	 * @param begin     起始周
	 * @param end		结束周
	 * @param row		节数
	 * @param col		星期
	 * @param classRoom	实验室
	 * @return
	 */
	public static boolean checkRooms(int begin, int end, int row, int col,String classRoom){
		boolean result = false;
		for (int i = begin; i <= end; i++) {
			String rooms = getUsedRooms(i, row, col);
			if( rooms == null || !rooms.contains(classRoom)){//classRoom此时段无人用
				result = true;
				continue;
			}else {
				result = false;
				break;
			}
		}
		return result;
	}
	
	/**
	 * 记录下老师-专业-所选时间-实验室
	 * @param teacherName
	 * @param majorName
	 * @param week
	 * @param weekAndJieCi
	 * @param myRoom
	 * @return
	 */
	public static boolean addRecord(String teacherName, String majorName, String week, String weekAndJieCi,
			String myRoom) {
		boolean result = false;
		//INSERT INTO record (`teacher_name`, `major_name`, `week`, `oktime`, `lib_room`, `insert_time`) VALUES ('ljh2', '14级软件工程#30人--1、2班', '1-9,11-19', '星期三第5、6节', '617', '2017-06-30 10:25:59');
		String sql = "INSERT INTO record (`teacher_name`, `major_name`, `week`, `oktime`, `lib_room`, `insert_time`) VALUES (? , ?, ?, ?, ?, ?);";
		
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, teacherName);
			ps.setString(2, majorName);
			ps.setString(3, week);
			ps.setString(4, weekAndJieCi);
			ps.setString(5, myRoom);
			ps.setString(6, sdf.format(new Date()));
			result = ps.execute();
			
			if(result == false){
				result = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				if(ps != null) ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	/**
	 * 获取Record
	 * @return
	 */
	public static ResultSet getRecords(){
		ResultSet rs = null;
		String sql = "select * from record";
		
		try {
			sm = con.createStatement();
			rs = sm.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	public static void main(String[] args) throws UsedClassRoomException {
		
//		if (checkTable(1, 20, 3, 1, "602")) {
//			sureRoom(1, 20, 3 ,1, "600");
//		}
		
//		long tt = System.currentTimeMillis();
//		System.out.println(checkTable(1, 20, 5, 7, "601"));
//		System.out.println(System.currentTimeMillis() - tt);
		
		
		long tt = System.currentTimeMillis();
		System.out.println(checkRooms(1, 20, 3, 1, "603"));
		System.out.println(System.currentTimeMillis() - tt);
		
	}

	

}
