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
<title>My JSP 'adminInfo.jsp' starting page</title>

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
<script type="text/javascript">
		function getAddress(){
		alert(${province});
			if(${province}!=""&&${province}!=null){
				alert(${province});
				var opt = "<option selected='selected' value='"+${province}+"'/>";
				document.getElementById("province").innerHTML=opt;
			}
		}
	</script>

<body class="childrenBody" onload="getAddress()">

	<form class="layui-form" action="admin/updateAdminInfo.htm">
		<input type="hidden" name="id" value="${adminLogin.id}">
		<div style="width:100%;text-align:center">
			<div class="user_left">
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">编号</label>
						<div class="layui-input-block">
							<input type="text" value="${adminLogin.no}" disabled
								class="layui-input layui-disabled">
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">姓名</label>
						<div class="layui-input-block">
							<input type="text" value="${adminLogin.name}" disabled
								class="layui-input layui-disabled">
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">用户组</label>
						<div class="layui-input-block">
							<input type="text" value="管理员" disabled
								class="layui-input layui-disabled">
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">昵称</label>
						<div class="layui-input-block">
							<input name="nickname" type="text" value="${adminLogin.nickname}"
								lay-verify="required" class="layui-input">
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">性别</label>
						<div class="layui-input-block">
							<c:if test="${adminLogin.sex==1}">
								<input type="tel" value="男" disabled lay-verify="required|phone"
									class="layui-input">
							</c:if>
							<c:if test="${adminLogin.sex!=1}">
								<input type="tel" value="女" disabled lay-verify="required|phone"
									class="layui-input">
							</c:if>
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">出生年月</label>
						<div class="layui-input-block">
							<input type="text" value="${birthday}" name="birthday"
								lay-verify="required|date"
								onclick="layui.laydate({elem: this,max: laydate.now()})"
								class="layui-input">
						</div>
						<div class="layui-inline">
							<div class="layui-input-block">
								<input type="submit" class="layui-btn" value="提交修改">
								<button type="reset" class="layui-btn layui-btn-primary">重置</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>

	<script type="text/javascript" src="layui/layui.js"></script>
	<script type="text/javascript" src="js/address.js"></script>
	<script type="text/javascript" src="js/user.js"></script>
</body>

</html>
