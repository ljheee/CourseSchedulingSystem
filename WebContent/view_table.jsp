<%@ page language="java" import="java.util.*,com.ljheee.db.DbUtil,java.sql.*" 
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
  <head>
    
    <title>图标查看</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style media="screen">
* {
	margin: 0;
	padding: 0;
}

header {
	padding: 1em 0;
	text-align: center;
	background-color: #4a4;
	color: #fff;
	font-size: 300%;
}

header span {
	font-size: 50%;
	margin-left: 1em;
	color: #eee;
}

footer {
	position: absolute;
	bottom: 0;
	padding: 2em 0;
	text-align: center;
	background-color: #4a4;
	color: #fff;
	width: 100%;
	clear: both;
}

.content {
	width: 75%;
	overflow: auto;
	float: right;
	min-height: 600px;
}

.nav {
	float: left;
	background-color: #eee;
	width: 25%;
	position: fixed;
	height: 100%;
	overflow: auto;
}

ul {
	list-style-type: none;
	margin-top: 1em;
}

li a {
	display: block;
	color: #000;
	padding: 8px 0 8px 16px;
	text-decoration: none;
}

li a:hover {
	background-color: #4a4;
	color: white;
}

table {
	width: 100%;
	border-collapse: collapse;
	margin: 2m auto;
}

th, td {
	text-align: left;
	padding: 8px;
}

tr:nth-child(even) {
	background-color: #f2f200;
}
</style>
  </head>
  
  
  
  <body>
    <header>
    	<div style="float:right;font-size:18px;"><a href='http://127.0.0.1:8080/CourseSchedulingSystem/Exit.jsp'>点击退出</a></div>
		实验教学--排课管理<span>图表查看</span>
	</header>
	
	<div class="nav">
		<ul>
			<li><a href="import.html">导入教学计划</a></li>
			<li><a href="course_schedule.jsp">教师排课</a></li>
			<li><a href="search.jsp">相关查询</a></li>
			<li><a href="view_table.jsp">图表查看</a></li>
			<li><a href="aboutus.html">其他</a></li>
		</ul>
	</div>
	
	<%
	ResultSet rs = DbUtil.getRecords();
	pageContext.setAttribute("rs", rs);
	%>
	
	<div class="content">
		<h3>已选的实验课</h3>
		<hr />
		<br />
		
		<table>
			<tr>
				<th>编号</th>
				<th>姓名</th>
				<th>专业</th>
				<th>起始周</th>
				<th>实验课时间</th>
				<th>实验室</th>
				<th>选课时间</th>
			</tr>
			
		<c:if test="${empty rs}">
			<p> 尚无选课</p>
			<tr>
				<td>000</td>
				<td>李建华</td>
				<td>2014级软件工程#42人--1,2班</td>
				<td>1-13</td>
				<td>星期四第1,2节</td>
				<td>600</td>
				<td>2017-06-30 11:21:59</td>
			</tr>
        </c:if>
			
			
			<c:if test="${ not empty rs}">
			<% 
			while(rs.next()){%>
				<tr>
				<td><%=rs.getInt(1) %></td>
				<td><%=rs.getString(2) %></td>
				<td><%=rs.getString(3) %></td>
				<td><%=rs.getString(4) %></td>
				<td><%=rs.getString(5) %></td>
				<td><%=rs.getString(6) %></td>
				<td><%=rs.getString(7) %></td>
				</tr>
			<%}%>
		    </c:if>
		</table>
	</div>
	
	
	<footer>Copyright (c) 2017 CourseSchedulingSystem All Rights Reserved.</footer>
	
  </body>
</html>
