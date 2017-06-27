package com.ljheee.bean;

import java.io.Serializable;

/**
 *教师
 */
public class Teacher implements Serializable{
	
	private static final long serialVersionUID = 8189764718441759027L;

	/*教师名*/
	public String name;
	
	/*[本学期]所教实验课专业*/
	public Major teachMajor;
	
	
	public Teacher() {
	}

	

	public String getName() {
		return name;
	}



	public Major getTeachMajor() {
		return teachMajor;
	}



	public Teacher(String name, Major teachMajor) {
		this.name = name;
		this.teachMajor = teachMajor;
	}


	@Override
	public String toString() {
		return "Teacher [name=" + name + ", teachMajor=" + teachMajor + "]";
	}

}
