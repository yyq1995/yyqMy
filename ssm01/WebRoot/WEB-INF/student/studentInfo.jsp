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

<title>My JSP 'studentMain.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" href="layui/css/layui.css" media="all" />
<link rel="stylesheet" href="css/user.css" media="all" />
</head>

<body class="childrenBody">
	<form class="layui-form" action="student/updateStudentInfo.htm">
		<div class="user_left">
			<div class="layui-form-item">
				<div class="layui-inline">
					<label class="layui-form-label">学号</label>
					<div class="layui-input-block">
						<input type="text" value="${studentLogin.no }" disabled
							class="layui-input layui-disabled">
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label">姓名</label>
					<div class="layui-input-block">
						<input type="text" value="${studentLogin.name }" disabled
							class="layui-input layui-disabled">
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label">用户组</label>
					<div class="layui-input-block">
						<input type="text" value="学生" disabled
							class="layui-input layui-disabled">
					</div>
				</div>
			</div>
			<div class="layui-form-item" style="width: 40%;">
				<label class="layui-form-label">性别</label>
				<div class="layui-input-block">
					<c:if test="${studentLogin.sex==1}">
						<input type="tel" value="男" disabled lay-verify="required|phone"
							class="layui-input">
					</c:if>
					<c:if test="${studentLogin.sex!=1}">
						<input type="tel" value="女" disabled lay-verify="required|phone"
							class="layui-input">
					</c:if>
				</div>
			</div>

			<div class="layui-form-item">
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">学院</label>
						<div class="layui-input-inline">
							<input type="text" value="${studentLogin.college.name }" disabled
								class="layui-input layui-disabled">
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">专业</label>
						<div class="layui-input-inline">
							<input type="text" value="${studentLogin.major.name }" disabled
								class="layui-input layui-disabled">
						</div>
					</div>
				</div>
				<div class="layui-form-item" style="width: 40%;">
					<div class="layui-inline">
						<label class="layui-form-label">昵称</label>
						<div class="layui-input-block">
							<input type="text" name="nickname"
								value="${studentLogin.nickname }" placeholder="请输入用户名"
								lay-verify="required" class="layui-input">
						</div>
					</div>
				</div>

				<div class="layui-form-item" style="width: 40%;">
					<label class="layui-form-label">出生年月</label>
					<div class="layui-input-block">
						<input type="text" name="birthday" value="${birthday}"
							placeholder="请输入出生年月" lay-verify="required|date"
							onclick="layui.laydate({elem: this,max: laydate.now()})"
							class="layui-input">
					</div>
				</div>

			</div>
		</div>

		<div class="layui-form-item" style="margin-left: 5%;">
			<div class="layui-input-block">
				<input type="submit" class="layui-btn" value="提交修改">
				<button class="layui-btn" lay-submit="" lay-filter="changeUser">立即提交</button>
				<button type="reset" class="layui-btn layui-btn-primary">重置</button>
			</div>
		</div>
	</form>
	<script type="text/javascript" src="layui/layui.js"></script>
	<script type="text/javascript" src="js/address.js"></script>
	<script type="text/javascript" src="js/user.js"></script>
</body>

</html>