<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'delSuccess.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body onload="goParent()">
	删除成功
	<br>
</body>
<script type="text/javascript">
  	   function goParent(){
  	   		var course=${isCourse};
  	   		var url="";
  	   		if(course==1){
  	   			url = "courseList.htm?cPage="+${cPage} + "&maxSize=" + ${maxSize};
  	   		}else {
  	   			url = "chooseCourseList.htm?terms.flag=2&cPage="+${cPage}+"&maxSize="+${maxSize};
  	   		}
  	   		window.parent.tipsLayer(url);
  	   } 
  </script>
</html>
