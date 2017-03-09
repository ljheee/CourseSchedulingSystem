<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>

	<title>Exit</title>
</head>
<body background="bg2.jpg">
	退出成功！3秒后返回登陆界面.......
	<%
		session.removeAttribute("dba");
		response.setHeader("Refresh","3;url=http://127.0.0.1:8080/CourseSchedulingSystem/login.jsp");
	%>

	<a href="http://127.0.0.1:8080/CourseSchedulingSystem/login.jsp">没有跳转，请点这里</a>

</body>
</html>