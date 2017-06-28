package com.ljheee.db;
/**
 * 此实验室已用
 * 在检查实验室是否可用时，就会抛异常
 * checkTable{checkClass}
 */
public class UsedClassRoomException extends Exception {

	private static final long serialVersionUID = 5487474692359270035L;
	

	public UsedClassRoomException(int tableIndex, int row, int col) {
		super("[ClassRoom was Used. WeekNum_"+tableIndex+" DayNum_"+col+" CourseNum_"+row+"(1代表第1-2节_2代表3-4节_以此类推...)]");
	}

}
