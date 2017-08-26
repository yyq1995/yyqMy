<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
  	function scoreLimit(obj){
  		var num = obj.value;
  		if(num >100 || num < 0){
  			obj.value=0;
  		}
  	}
  
  </script>
  <body>
      	<form action="SC/updateSC.htm" method="post">
      	请填写0-100的数字，否则无效<br>
      	<input type="hidden" name="chooseCourse.id" value="${sc.chooseCourse.id }">
      	<input type="hidden" name="id" value="${sc.id }">
      		成绩：<input name="score" type="text" value="${sc.score}" onchange="scoreLimit(this);">
      		<input type="submit" value="修改">
      	</form>
  </body>
</html>
