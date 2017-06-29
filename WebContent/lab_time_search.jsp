<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  

<html>
  <head>
    
    <title>实验室-空闲时间查询</title>
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
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>

<script type="text/javascript">   

var Room;
var Week;
var XinQi;
var JieCi;
	function finishSelectBeginWeek(beginWeek){
		Week = beginWeek;
		$("#xinqi").removeAttr("disabled");
	}
	function finishSelectXinQi(xinqi){
		XinQi = xinqi;
		$("#jieci").removeAttr("disabled");
	}
	function finishSelectJieCi(jieci){
		JieCi = jieci;
		var room = document.getElementById("room").value;
		if(room=='' || room==null){
			alert("请输入实验室，例如：617");
		}else{
			Room = room;
		}
	}

	function searchLib(){ 
		Room = document.getElementById("room").value;
		if(Room=='' || room==null){
			alert("请输入实验室，例如：617");
		}
		
		$.post('libSearchServlet', {libRoom:Room, selWeek:Week, row:JieCi, col:XinQi} , function(flag){
			
			alert(flag+"\n [true代表此实验室未使用，\n false代表此实验室已使用]");
			
		 }, "json");
	}  
</script>
  </head>
  
  
  
  <body>
    <header>
    	<div style="float:right;font-size:18px;"><a href='http://127.0.0.1:8080/CourseSchedulingSystem/Exit.jsp'>点击退出</a></div>
		实验教学--排课管理<span>学生专业理论课-空闲时间查询</span>
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
	
	<h4> 实验室-空闲时间查询 </h4>
	<hr>
	
	<div>
	<ul>
		<li> 节次（1-5）</li>
		<li> --1代表上午1-2节</li>
		<li> --2代表上午3-4节</li>
		<li> --3代表下午5-6节</li>
		<li> --4代表下午7-8节</li>
		<li> --5代表晚上9-10节</li>
	</ul>
	</div>
	
	<br />
	
	<table id="major_search">
			<tr>
				<th>实验室</th>
				<th>选择周数</th>
				<th>星期(1-7)</th>
				<th>节次(1-5)</th>
				<th>查询</th>
			</tr>
			<tr>
				<td>
					<input name="libRoom" id="room" type="number" placeholder="例如：617" required="required"/>
				</td>
				<td><select name="selWeek" id="week" onChange="finishSelectBeginWeek(this.options[this.selectedIndex].text)">
						<option value="0" selected = "selected">请选择</option>
						<option value="1" >1</option>
						<option value="2" >2</option>
						<option value="3" >3</option>
						<option value="4" >4</option>
						<option value="5" >5</option>
						<option value="6" >6</option>
						<option value="7" >7</option>
						<option value="8" >8</option>
						<option value="9" >9</option>
						<option value="10" >10</option>
						<option value="12" >12</option>
						<option value="13" >13</option>
						<option value="14" >14</option>
						<option value="15" >15</option>
						<option value="16" >16</option>
						<option value="17" >17</option>
						<option value="18" >18</option>
						<option value="19" >19</option>
						<option value="20" >20</option>
					</select>
				</td>
				<td><select name="col" id="xinqi" disabled="disabled" onChange="finishSelectXinQi(this.options[this.selectedIndex].text)">
						<option value="0" selected = "selected">请选择</option>
						<option value="1" >1</option>
						<option value="2" >2</option>
						<option value="3" >3</option>
						<option value="4" >4</option>
						<option value="5" >5</option>
						<option value="6" >6</option>
						<option value="7" >7</option>
					</select>
				</td>
				<td><select name="row" id="jieci" disabled="disabled" onChange="finishSelectJieCi(this.options[this.selectedIndex].text)">
						<option value="0" selected = "selected">请选择</option>
						<option value="1" >1</option>
						<option value="2" >2</option>
						<option value="3" >3</option>
						<option value="4" >4</option>
						<option value="5" >5</option>
					</select>
				</td>
				<td>
					<input type="submit" value="查询" onclick="searchLib()">
				</td>
			</tr>
	</table>
	
	
	
	</div>	
	
	
	<footer>Copyright (c) 2017 CourseSchedulingSystem All Rights Reserved.</footer>
	
  </body>
</html>
