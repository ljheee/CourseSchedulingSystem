<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  

<html>
  <head>
    
    <title>教师理论课--空闲时间查询</title>
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
  var Teacher;
  
	function getTeacher(currTeacher){ 
		
	$("#selBeginWeek").removeAttr("disabled");
    //当前 所选择 的教师名
    Teacher = currTeacher;  
    alert(Teacher);
    
    //清空 周数选择 下拉选单  
    document.all.selBeginWeek.length = 0;  
    document.all.selBeginWeek.options[0] = new Option("选择周数1-20",0);
     
    //填充周数选择 下拉选单  
    for(var i=1; i<=20;i++){     //循环添加多个值
        document.all.selBeginWeek.options[i] = new Option(""+i,i);
    }
}  
	
	function finishSelectBeginWeek(beginweek){  
		document.all.freeTime.length = 0; 
		document.all.freeTime.options[document.all.freeTime.length] = new Option("freeTime",'');
		$.post('teacherSearch', {teacherName:Teacher,beginWeek:beginweek} ,function(jsonArray) {
			
		 	$("#freeTime").removeAttr("disabled");
		 
		    for(var i=0; i < jsonArray.length;i++){     //循环添加多个值
		       document.all.freeTime.options[i] = new Option(jsonArray[i],i);
		    }
		},"json");
	}
	
</script>
  </head>
  
  
  
  <body>
    <header>
    	<div style="float:right;font-size:18px;"><a href='http://127.0.0.1:8080/pkxt/Exit.jsp'>点击退出</a></div>
		实验教学--排课管理<span>教师理论课--空闲时间查询</span>
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
	
	
	<div class="content">
	
	<h4> 教师理论课-空闲时间查询</h4>
	<br />
	<div><li> 教师空闲时间是指：该时间段没有理论课，可作为实验课的备选</li></div>
	<br />
	<table id="teacher_search">
			<tr>
				<th>教师名</th>
				<th>选择周</th>
				<th>空闲时间</th>
			</tr>
			<tr>
				<td><select id="selectTeacher" onChange="getTeacher(this.options[this.selectedIndex].value)">
						<option value="0" selected = "selected"  >请选择</option>
						<c:forEach var="item" items="${tList}">
						<option  value="${item.name}">${item.name}</option>
						</c:forEach>
					</select> 
				</td>
				<td><select id="selBeginWeek" disabled="disabled" onChange="finishSelectBeginWeek(this.options[this.selectedIndex].text)">
						<option value="0" selected = "selected" >请选择</option>
					</select>
				</td>
				
				<td><select id="freeTime" disabled="disabled" >
						<option value="0" selected = "selected" >空闲时间</option>
					</select>
				</td>
				
			</tr>
	</table>
	
	
	</div>	
	
	
	<footer>Copyright (c) 2017 CourseSchedulingSystem All Rights Reserved.</footer>
	
  </body>
</html>
