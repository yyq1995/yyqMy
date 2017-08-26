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
<meta name="renderer" content="webkit">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" href="layui/css/layui.css" media="all" />
<link rel="stylesheet"
	href="//at.alicdn.com/t/font_eolqem241z66flxr.css" media="all" />
<link rel="stylesheet" href="css/news.css" media="all" />

</head>
<script type="text/javascript">
	function getScore() {
		var scID = document.getElementsByName("id");
		var score = document.getElementsByName("score");

		var scString = "";
		for (var i = 0; i < scID.length; i++) {
			scString += scID[i].value + "-" + score[i].value + ",";
		}
		scString = scString.substring(0, scString.length-1);
		
		document.getElementById("idScore").value=scString;
		document.getElementById("f1").submit();
		
		/* var idString = "";
		for (var i = 0; i < scID.length; i++) {
			idString += scID[i].value + ",";
		}
		idString = idString.substring(0, idString.length - 1); */


	}
	function scoreLimit(obj){
  		var num = obj.value;
  		if(num >100 || num < 0){
  			obj.value=0;
  		}
  	}
</script>

<body class="childrenBody">

	<div class="layui-form links_list">
		<table class="layui-table">
			<tr>
				<td>成绩区间为0-100，区间外的数字无效！</td>
			</tr>
		
		</table></div>
	<div class="layui-form links_list">
		<table class="layui-table">
			<thead>
				<tr>
					<th style="width: 9%;">学号</th>
					<th style="width: 15%;">学生姓名</th>
					<th style="width: 7%;">性别</th>
					<th style="width: 15%;">所属学院</th>
					<th style="width: 10%;">所属专业</th>
					<th style="width: 10%;">成绩</th>
					
				</tr>
			</thead>
			<tbody>

				<c:forEach items="${pb.list }" var="SC" varStatus="vs">
					<tr>
						<td>${SC.student.no }</td>
						<td>${SC.student.name }</td>
						<td>${SC.student.sex }</td>
						<td>${SC.student.college.name }</td>
						<td>${SC.student.major.name }</td>
						<td><input type="text" name="score" id="score${SC.id }" value="${SC.score }" onchange="scoreLimit(this);">
						<input type="hidden" name="id" id="id+${SC.id }"
							value="${SC.id }" >
					</tr>
				</c:forEach>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td>
						<form id="f1" action="SC/submitScore.htm" method="post">
							<input type="hidden" name="idScore" id="idScore"> <input id="bt"
								type="button" value="确认" name="get" onclick="getScore()">
						</form>
					</td>
				</tr>
			</tbody>
		</table>
	</div>

	<div id="page"></div>
	<script type="text/javascript" src="layui/layui.js"></script>
	<script type="text/javascript" src="js/admin/course.js"></script>
</body>
</html>