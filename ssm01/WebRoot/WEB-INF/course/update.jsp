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
		for (var i = 32; i <= 64; i = i + 2) {
			opts += "<option  value='" + i + "' id='hour" + i + "'>" + i + "</option>"
		}
		document.getElementById("hour").innerHTML = opts;
		var hourName = "${course.hour }";
		if (hourName != null && hourName.length != 0) {
			var hourObj = document.getElementById("hour" + hourName);
			hourObj.selected = "selected";
		}
	}
</script>
<body onload="hour()">
	<form action="course/updCourse.htm" method="post">
		<table>
			<tr>
				<td>开课学院：<select name="college.id">
						<option value="${course.college.id }">${course.college.name }</option>
						<c:forEach items="${collegeList }" var="college">
							<option value="${college.id }" id="college${college.id }">${college.name }</option>
						</c:forEach>
				</select></td>

			</tr>
			<tr>
				<td>课程名称：<input type="text" name="name" value="${course.name }">
					<input type="hidden" name="id" value="${course.id }">
				</td>

			</tr>
			<tr>
				<td>课程编号：<input type="text" name="no" value="${course.no }">
				</td>
			</tr>
			<tr>
				<td>
					 课时: <select id="hour" name="hour">
				</select> 
				</td>

			</tr>
			<tr>
				<td>学分：<select name="credit">
						<option value="${course.credit }">${course.credit }</option>
						<option value="1.0">1.0</option>
						<option value="2.0">2.0</option>
						<option value="3.0">3.0</option>
				</select>
				</td>

			</tr>
			<tr>
				<td><input type="submit" value="修改"></td>
			</tr>
		</table>
	</form>
</body>
</html>
