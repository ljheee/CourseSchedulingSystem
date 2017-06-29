<%@ page language="java"  pageEncoding="UTF-8" errorPage="error.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  

<html>
  <head>
    
    <title>course schedule page</title>
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
var Teacher;
var Major;
var Begin;
var End;
var OkTime;
function getTeacher(currTeacher){  
    //当前 所选择 的教师名
    Teacher= currTeacher;  
    alert(Teacher);
    
    //清空 专业选择 下拉选单  
    document.all.selMajor.length = 0;  
     
    //填充专业选择 下拉选单  
    $.post('selectMajorServlet', {name: currTeacher} ,function(jsonArray) {
		
 	document.all.selMajor.options[document.all.selMajor.length] = new Option("ljh",'');
 	$("#selMajor").removeAttr("disabled");
 
    for(var i=0; i<jsonArray.length;i++){     //循环添加多个值
       document.all.selMajor.options[i] = new Option(jsonArray[i].level+'级'+jsonArray[i].name+'#'+jsonArray[i].numStudent+'人--'+jsonArray[i].group,i);
    }
   	},"json");
}  

function finishSelectMajor(curMajor){  
	Major = curMajor;
	alert(Major);
}

function finishSelectBeginWeek(beginweek){ 
	Begin = beginweek;
}

function finishSelectEndWeek(endweek){ 
	End = endweek;
	
	//清空 okTime 下拉选单  
    document.all.okTime.length = 0; 
	$.post('resultServlet', {teacherName: Teacher,majorName:Major,beginWeek:Begin,endWeek:End} ,function(jsonArray) {
		
	 	document.all.okTime.options[document.all.okTime.length] = new Option("okTime",'');
	 	$("#okTime").removeAttr("disabled");
	 
	    for(var i=0; i < jsonArray.length;i++){     //循环添加多个值
	       document.all.okTime.options[i] = new Option(jsonArray[i],i);
	    }
	    
	   	},"json");
}


function finishSelectOkTime(okTime){ 
	OkTime = okTime;
	
	//清空 usedRoom 下拉选单  
    document.all.usedRoom.length = 0; 
	$.post('usedRoomServlet', {teacherName: Teacher,majorName:Major,beginWeek:Begin,endWeek:End,oktime:okTime} ,function(jsonArray) {
		
	 	document.all.usedRoom.options[document.all.usedRoom.length] = new Option("usedRoom",'');
	 	$("#usedRoom").removeAttr("disabled");
	 
	    for(var i=0; i < jsonArray.length;i++){     //循环添加多个值
	       document.all.usedRoom.options[i] = new Option(jsonArray[i],i);
	    }
	    
	   	},"json");
}

//最终写入DB
function write2DB(){
	var myRoom = document.getElementById("myRoom").value;
	alert("即将使用实验室"+myRoom+"\n---写入后无法修改！---");
	
	$.post('writeDbServlet', {teacherName: Teacher,majorName:Major,beginWeek:Begin,endWeek:End,oktime:OkTime,myroom:myRoom} ,function(flag) {
		
		var msg;
		for(var i=0; i < flag.length;i++){ 
			msg += flag[i];
		}
		alert(msg);
	    
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
			<li><a href="search.jsp">相关查询</a></li>
			<li><a href="view_table.jsp">图表查看</a></li>
			<li><a href="aboutus.html">其他</a></li>
		</ul>
	</div>
	
	
	<div class="content">
	<p> 1、请按顺序依次选择 。</p>
	<p> 2、可选的实验课时间段：由教师、和学生专业理论课筛选后的空闲时间段决定（起止周内）。</p>
	<p> 3、排课顺序先来后到，初始状态  实验室均可用。先选课者 先确定实验室。 </p>
	<p> 4、一天课程为上午1、2节，3、4节；下午5、6节，7、8节；晚上9、10节。 此系统约定该5个上课时间段用1-5表示。</p>
	<br />
	
		<table>
			<tr>
				<th>教师姓名</th>
				<th>教学专业</th>
				<th>开始周</th>
				<th>结束周</th>
				<th>可选的时间段</th>
				<th>已用的实验室</th>
				<th>输入实验室</th>
			</tr>
			
			<tr>
				<td><select id="selectTeacher" onChange="getTeacher(this.options[this.selectedIndex].value)">
						<option value="0" selected = "selected">请选择</option>
						<c:forEach var="item" items="${tList}">
						<option  value="${item.name}">${item.name}</option>
						</c:forEach>
					</select> 
				</td>
				<td><select id="selMajor" disabled="disabled" onChange="finishSelectMajor(this.options[this.selectedIndex].text)">
						<option value="0" selected = "selected"  >请选择</option>
					</select>
				</td>
				<td><select id="beginWeek" onChange="finishSelectBeginWeek(this.options[this.selectedIndex].text)">
						<option value="0" selected = "selected"  >请选择</option>
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
				<td><select id="endWeek" onChange="finishSelectEndWeek(this.options[this.selectedIndex].text)">
						<option value="0" selected = "selected"  >请选择</option>
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
				<td><select id="okTime" disabled="disabled" onChange="finishSelectOkTime(this.options[this.selectedIndex].text)">
						<option value="1" selected = "selected"  >请选择</option>
					</select>
				</td>
				<td><select id="usedRoom" disabled="disabled">
						<option value="1" selected = "selected"  >请选择</option>
					</select>
				</td>
				<td>
					<input type="number" placeholder="输入例如617" id="myRoom" >
				</td>
				<td>
					<input type="button" value="提交" onclick="write2DB()">
				</td>
			</tr>
			
		</table>
		
	</div>
	
			
	
	
	<footer>Copyright (c) 2017 CourseSchedulingSystem All Rights Reserved.</footer>
	
  </body>
</html>
