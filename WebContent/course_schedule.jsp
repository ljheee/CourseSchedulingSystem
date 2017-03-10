<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  

<html>
  <head>
    
    <title>index page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
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
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script type="text/javascript">   
  
function getTeacher(currTeacher)  
{  
    //当前 所选择 的教师名
    var currTeacher = currTeacher;  
    alert(currTeacher);
    //清空 专业选择 下拉选单  
    //document.all.selMajor.length = 0 ;  
     
    //填充专业选择 下拉选单  
      
   $.post('selectMajorServlet', { name: currTeacher} ,function(list) {
		//$('#fileUploadProcess').html(data);
	   document.all.selMajor.options[document.all.selMajor.length] = new Option("ljh",'');
	});

}  
</script>


  </head>
  
  <body>
    <header>
    	<div style="float:right;font-size:18px;"><a href='http://127.0.0.1:8080/CourseSchedulingSystem/Exit.jsp'>点击退出</a></div>
		实验教学--排课管理<span>教师排课</span>
	</header>
	
	<div class="nav">
		<ul>
			<li><a href="import.html">导入教学计划</a></li>
			<li><a href="course_schedule.jsp">教师排课</a></li>
			<li><a href="view_table.jsp">图表查看</a></li>
			<li><a href="aboutus.html">其他</a></li>
		</ul>
	</div>
	
	<div class="content">
		<table>
			<tr>
				<th>教师姓名</th>
				<th>教学专业</th>
				<th>分组</th>
				<th>导出</th>
			</tr>
			
			<tr>
				<td><select id="selectTeacher" onChange="getTeacher(this.options[this.selectedIndex].value)">
						<option value="1" selected = "selected"  >请选择</option>
						<c:forEach var="item" items="${tList}">
						<option  value="${item.name}">${item.name}</option>
						</c:forEach>
					</select> 
				</td>
				<td><select id="selMajor">
						<option value="1" selected = "selected"  >请选择</option>
						<c:forEach var="item" items="${majorList}">
						<option  value="${item.name}">${item.name}</option>
						</c:forEach>
					</select>
				</td>
				<td><select>
						<option value="1" selected = "selected"  >请选择</option>
					</select>
				</td>
				<td><select>
						<option value="1" selected = "selected"  >请选择</option>
					</select>
				</td>
			</tr>
			
			
			
		</table>
		
	</div>
	
			
	
	
	<footer>Copyright (c) 2017 CourseSchedulingSystem All Rights Reserved.</footer>
	
  </body>
</html>
