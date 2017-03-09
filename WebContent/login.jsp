<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
        <title>实验课排课系统</title>
</head>
<body background="hy.jpg">
<h1 align="center">实验教学--排课管理<span>--DBA登录</span></h1>

<form action="treat" method="post">
    <table id="dba_login" align="center">
		<tr>
		<td>姓名:</td>
		<td><input type="text" name="userName" size="20" maxlength="10" /></td>
		</tr>

	    <tr>
		<td>密码：</td>
		<td><input type="password" name="password" size="20" maxlength="10" /></td>
		</tr>

		<tr>
		<td colspan='2'><input type="submit" name="submit" value="登陆" />
		<input type="hidden"  name="yemian"  value="login"/>
		<input type="reset" name="reset" value="重新填写" /></td>
		</tr>
	</table>
	
</form>

<%
	String loginInfo =(String)session.getAttribute("login");
	if(loginInfo!=null){
 %>
<div style="color:red;font-size:30px;">Login:<%= loginInfo%></div>
<%} %>

</body>
</html>
