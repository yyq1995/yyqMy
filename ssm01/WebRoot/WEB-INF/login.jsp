<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'login.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="css/normalize.css" />
	<link rel="stylesheet" type="text/css" href="css/demo.css" />
	<!--必要样式-->
	<link rel="stylesheet" type="text/css" href="css/component.css" />
	<!--[if IE]>
	<script src="js/html5.js"></script>
	<![endif]-->
  </head>
  
  <body>
		<div class="container demo-1">
			<div class="content">
				<div id="large-header" class="large-header">
					<canvas id="demo-canvas"></canvas>
					<div class="logo_box">
						<h3>欢迎你</h3>
						<form id="form" action="login.htm" name="f" method="post">
							<div class="input_outer">
								<span class="u_user"></span>
								<input name="no" value="" class="text" style="color: #FFFFFF !important" type="text" placeholder="请输入账户">
							</div>
							<div class="input_outer">
								<span class="us_uer"></span>
								<input name="password" value="" class="text" style="color: #FFFFFF !important; position:absolute; z-index:100;" type="password" placeholder="请输入密码">
							</div>
							<div class="mb2">
							<div class="input_outer">
								<span class="u_user"></span>
								<input name="code" class="text" style="color: #FFFFFF !important" type="text" placeholder="请输入验证码">
							</div>
							<div class="img">
					  			<img id="ImgCode" src="/ssm/img.htm" onclick="_chg()">
					   			<a href="javascript:_chg();" style="color: #CCEEFF">看不清楚换一张</a>
							</div>
							<div>
								<h4>${error}</h4>
								<h4>${interceptorTips}</h4>
							</div>
							<input type="radio" value="1" checked="checked" name="radio">学生登录
							<input type="radio" value="2" name="radio">教师登录
							<input type="radio" value="3" name="radio">管理员登录
							<a class="act-but submit" href="javascript:login();" style="color: #FFFFFF">登录</a></div>
						</form>
					</div>
				</div>
			</div>
		</div><!-- /container -->
		
		<script type="text/javascript">
			function login(){
				//alert("------------")
				document.getElementById("form").submit();
			}
		</script>
		<script type="text/javascript">
   			function _chg(){
	   			var url="/ssm/img.htm?"+Math.random();
	  			 var img = document.getElementById("ImgCode");
	   			img.src=url;
	   		}
		</script>
		<script src="js/TweenLite.min.js"></script>
		<script src="js/EasePack.min.js"></script>
		<script src="js/rAF.js"></script>
		<script src="js/demo-1.js"></script>
	</body>
</html>
							