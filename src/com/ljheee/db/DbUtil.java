package com.ljheee.db;

import java.sql.*;

/**
 * DB
 * @author ljhee
 *
 */
public class DbUtil {
	
	static Connection con = null;
	static  PreparedStatement stmt = null;
	static   ResultSet rs = null;  

	
	static  String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";       
	static  String dbURL = "jdbc:sqlserver://localhost:1433;integratedSecurity=true; DatabaseName=salarymanagement";     
   
	static{
		try {
            Class.forName(driver);//
            con = DriverManager.getConnection(dbURL);//                  
		} catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } 
	}
		
	
	
}
