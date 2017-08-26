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

<title>My JSP 'duplicateAddSC.jsp' starting page</title>

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
	function getTerm(obj){
		var termID = obj.getAttribute("term");
  		
  		document.getElementById("termID").value = termID;
	}
</script>
<body>
	<form action="SC/addScore.htm" method="post">

		<div class="layui-form-item">
			<label>选择上学期您所教授课程：</label> 
			<input type="hidden" name="chooseCourse.terms.flag" value="${termFlag }">
			<input type="hidden" name="chooseCourse.terms.id" id="termID">
			<select name="chooseCourse.id">
				<c:forEach items="${ccList }" var="cc">
					<option value="${cc.id }" term="${cc.terms.id }" onclick="getTerm(this)">${cc.course.name}</option>
				</c:forEach>
			</select> <input type="submit" value="确定添加">
		</div>
	</form>
</body>
</html>
