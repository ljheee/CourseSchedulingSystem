package com.ljheee.read;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.ljheee.bean.Course;
import com.ljheee.bean.Major;
import com.ljheee.bean.Teacher;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;

/**
 * 读取--学院教学计划（xls文件）
 */
public class ReadXls {
	
	private static ReadXls readXls;

	File xlsFile;

	Workbook wb = null;
	Sheet sheet = null;
	int rows = 0;
	int cols = 0;

	int colOfcourse,colOflevel,colOfmajorName,colOfnumStudent,colOfgroup,courseHour,colOfTeacher;
	
	private ReadXls() {
	}
	
	public static ReadXls getInstance(){
		if(readXls == null){
			readXls = new ReadXls();
		}
		return readXls;
	}
	
	public void setXlsFile(File xlsFile) {
		this.xlsFile = xlsFile;
		try {
			WorkbookSettings wbs = new WorkbookSettings(); 
			wbs.setEncoding("UTF-8");
			wb = Workbook.getWorkbook(xlsFile,wbs);
			sheet = wb.getSheet(0);
			rows = sheet.getRows();
			cols = sheet.getColumns();
		} catch (BiffException | IOException e) {
			e.printStackTrace();
		}
		System.out.println("small sheet="+sheet);
	}

	Teacher teacher = null;
	List<Major> teachMajors = null;
	Course course = null;
	Major major = null;

	/**
	 * 读取所有教师教学信息
	 * 
	 * @return
	 */
	public List<Teacher> getTeacherList() {
		List<Teacher> tSet = new ArrayList<>();

		//D:\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\pkxt\xlsFiles\2.xls
		Cell[] tableTitles = sheet.getRow(0);;

		
		//匹配表头
		for (int i = 0; i < tableTitles.length; i++) {
			switch (tableTitles[i].getContents().trim()) {
			case "课程名称":
				colOfcourse = i;
				break;
			case "年级":
				colOflevel = i;
				break;
			case "专业名称":
				colOfmajorName = i;
				break;
			case "人数":
				colOfnumStudent = i;
				break;
			case "分组":
				colOfgroup = i;
				break;
			case "实验学时":
				courseHour = i;
				break;
			case "实验老师":
				colOfTeacher = i;
				break;
			default:
				break;
			}
		}
		

		for (int i = 1; i < rows; i++) {// 从第一行

			Cell[] rowCells = sheet.getRow(i);

			teachMajors = new ArrayList<>();
			teacher = new Teacher();
			course = new Course();
			major = new Major();

			course.name = rowCells[colOfcourse].getContents().trim();
			major.level = rowCells[colOflevel].getContents().trim();
			major.name = rowCells[colOfmajorName].getContents().trim();
			major.numStudent = rowCells[colOfnumStudent].getContents().trim();
			major.group = rowCells[colOfgroup].getContents().trim();
			course.courseHour = rowCells[courseHour].getContents().trim();
			teacher.name = rowCells[colOfTeacher].getContents().trim();
			major.course = course;

			teacher.teachMajor = major;
			if (teacher.name != null && !teacher.name.equals("")) {
				tSet.add(teacher);
			}
		}
		return tSet;
	}

	/**
	 * 获取教师名集合
	 * 
	 * @return
	 */
	public Set<String> getTeachers() {
		Set<String> set = new HashSet<>();

		Cell[] tableTitles = sheet.getRow(0);
		int colOfTeacher = 0;
		for (int i = 0; i < tableTitles.length; i++) {
			if (tableTitles[i].getContents().equals("实验老师")) {
				colOfTeacher = i;
			}
		}

		Cell[] teacherCells = sheet.getColumn(colOfTeacher);
		for (int i = 1; i < teacherCells.length; i++) {
			String name = teacherCells[i].getContents();
			if (!"".equals(name) && name != null) {
				set.add(name);
			}
		}
		return set;
	}

	/**
	 * 获取教师实验课程
	 * 
	 * @param name
	 * @return
	 */
	public List<Major> getTeacherTeachesByName(String name) {
		List<Major> list = new ArrayList<>();

		List<Teacher> tList = getTeacherList();
		for (int i = 0; i < tList.size(); i++) {
			if (tList.get(i).name.equals(name)) {
				list.add(tList.get(i).teachMajor);
			}
		}
		return list;
	}

	/**
	 * 读取指定实验老师-实验课程信息
	 * 
	 * @param majors
	 * @return
	 */
	public Set<String> getMajorsInfo(List<Major> majors) {
		Set<String> result = new HashSet<>();

		for (int i = 0; i < majors.size(); i++) {
			Major major = majors.get(i);
			result.add(major.level + major.name + major.numStudent + "——" + major.group);
		}
		return result;
	}

	/**
	 * 获取专业分组
	 * @param teacherName
	 * @param majorName
	 * @return
	 */
	public List<String> getGroups(String teacherName, String majorName) {
		List<String> list = new ArrayList<>();

		List<Major> majors = getTeacherTeachesByName(teacherName);
		for (int i = 0; i < majors.size(); i++) {
			if (majors.get(i).name.equals(majorName)) {
				list.add(majors.get(i).group);
			}
		}

		return list;
	}

	/**
	 * 关闭工作簿
	 */
	public void close() {
		wb.close();
	}

}
