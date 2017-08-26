<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'adminMain.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>
<script type="text/javascript">
	function hour() {
		var opts = "";
		for (var i = 32; i <= 64; i=i+2) {
			opts += "<option  value=" + i + " id='hour'"+i+">" + i + "</option>"
		}
		document.getElementById("hour").innerHTML = opts;
	}
</script>
<body onload="hour();">
	<form action="course/addCourse.htm" method="post">
		<table>
			<tr>
				<td>开课学院：<select name="college.id">

						<c:forEach items="${collegeList }" var="college">
							<option value="${college.id }" id="college${college.id }">${college.name }</option>
						</c:forEach>
				</select></td>

			</tr>
			<tr>
				<td>课程名称：<input type="text" name="name">
				</td>

			</tr>
			<tr>
				<td>课程编号：<input type="text" name="no">
				</td>
			</tr>
			<tr>
				<td>课时: <select id="hour" name="hour">
				</select>
				</td>

			</tr>
			<tr>
				<td>学分：<select name="credit">
						<option value="1.0">1.0</option>
						<option value="2.0">2.0</option>
						<option value="3.0">3.0</option>
				</select>
				</td>

			</tr>
			<tr>
				<td><input type="submit" value="添加"></td>
			</tr>
		</table>
	</form>
</body>
</html>
