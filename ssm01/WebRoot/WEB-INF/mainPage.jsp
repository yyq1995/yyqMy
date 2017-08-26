<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'mainPage.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<link rel="stylesheet" href="layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="http://at.alicdn.com/t/font_eolqem241z66flxr.css" media="all" />
	<link rel="stylesheet" href="css/main.css" media="all" />
</head>
<body class="childrenBody">
	<div class="panel_box row">
		<div class="panel col">
			<a href="javascript:;" data-url="page/message/message.html">
				<div class="panel_icon">
					<i class="layui-icon" data-icon="&#xe63a;">&#xe63a;</i>
				</div>
				<div class="panel_word newMessage">
					<span>20</span>
					<cite>新消息</cite>
				</div>
			</a>
		</div>
		<div class="panel col">
			<a href="javascript:;" data-url="page/user/allUsers.html">
				<div class="panel_icon" style="background-color:#FF5722;">
					<i class="iconfont icon-dongtaifensishu" data-icon="icon-dongtaifensishu"></i>
				</div>
				<div class="panel_word userAll">
					<span>67</span>
					<cite>新增人数</cite>
				</div>
			</a>
		</div>
		<div class="panel col">
			<a href="javascript:;" data-url="page/user/allUsers.html">
				<div class="panel_icon" style="background-color:#009688;">
					<i class="layui-icon" data-icon="&#xe613;">&#xe613;</i>
				</div>
				<div class="panel_word userAll">
					<span>2340</span>
					<cite>用户总数</cite>
				</div>
			</a>
		</div>
		<div class="panel col">
			<a href="javascript:;" data-url="page/img/images.html">
				<div class="panel_icon" style="background-color:#5FB878;">
					<i class="layui-icon" data-icon="&#xe64a;">&#xe64a;</i>
				</div>
				<div class="panel_word imgAll">
					<span>48</span>
					<cite>选课总数</cite>
				</div>
			</a>
		</div>
		<div class="panel col">
			<a href="javascript:;" data-url="page/news/newsList.html">
				<div class="panel_icon" style="background-color:#F7B824;">
					<i class="iconfont icon-wenben" data-icon="icon-wenben"></i>
				</div>
				<div class="panel_word waitNews">
					<span>520</span>
					<cite>已选人数</cite>
				</div>
			</a>
		</div>
		<div class="panel col">
			<a href="javascript:;" data-url="page/news/newsList.html">
				<div class="panel_icon" style="background-color:#F7B824;">
					<i class="iconfont icon-wenben" data-icon="icon-wenben"></i>
				</div>
				<div class="panel_word waitNews">
					<span>1232</span>
					<cite>总访问量</cite>
				</div>
			</a>
		</div>
	</div>
	<blockquote class="layui-elem-quote">
		<p>选课系统已于8月24日开放。<span style="color:#1E9FFF;">郑重提示：请于9月1日前完成选课</span></p>
		<p style="margin-top:5px;">本系统负责人：汪汪汪        联系电话：1234567890       地址：机电楼C座303   邮箱：192837465@126.com</p>
	</blockquote>
	<div class="row">
		<div class="sysNotice col">
			<blockquote class="layui-elem-quote title">校园消息</blockquote>
			<div class="layui-elem-quote layui-quote-nm">
				<h3>官网新闻</h3>
				<p>* 我校承办中国轻工机械协会制浆造纸纸制品装备分会年度工作...</p>
				<p>* 中国工程院院士潘德炉来我校作报告</p>
				<p>* 我校4名优秀校友入选国家杰青、优青</p>
				<p>* 开学首日校（院）党政领导深入课堂听第一堂课</p>
				<p>* 我校被山东省教育厅确定为山东省信息化试点建设单位</p>
				<br />
				<p>校园风云</p>
				<p>* 软件学院新增选课数据库，由李四老师指导开课</p>
				<p>* 外国语学院新增选课阿拉伯语，由王五老师指导开课</p>
				<p>* 关于本学期部分节假日教学安排的通知</p>
				<p>* 新的教学楼或将在下半学期安排上课教室</p>
				<p>* 图书馆将于选课结束后开放特定的选修课交流区，欢迎同学们的到来</p>
			</div>
		</div>
		<div class="sysNotice col">
			<blockquote class="layui-elem-quote title">选课系统信息</blockquote>
			<table class="layui-table">
				<colgroup>
					<col width="150">
					<col>
				</colgroup>
				<tbody>
					<tr>
						<td>当前版本</td>
						<td class="version">1.0.0.1</td>
					</tr>
					<tr>
						<td>开发作者</td>
						<td class="author">jack</td>
					</tr>
					<tr>
						<td>官网首页</td>
						<td class="homePage">www.1234.com</td>
					</tr>
					<tr>
						<td>在线人数</td>
						<td class="server">20</td>
					</tr>
					<tr>
						<td>开课学院数</td>
						<td class="userRights">6</td>
					</tr>
					<tr>
						<td>课程总数</td>
						<td class="dataBase">48</td>
					</tr>
					<tr>
						<td>已选课人数</td>
						<td class="maxUpload">520</td>
					</tr>
					<tr>
						<td>日访问量</td>
						<td class="maxUpload">456</td>
					</tr>
				</tbody>
			</table>
			<blockquote class="layui-elem-quote title">本系统赞助单位：<i class="iconfont icon-new1">斯蒂芬软件技术有限公司</i></blockquote>
			<table class="layui-table" lay-skin="line">
				<colgroup>
					<col>
					<col width="110">
				</colgroup>
				<tbody class="hot_news"></tbody>
			</table> 
		</div>
	</div>

	<script type="text/javascript" src="layui/layui.js"></script>
	<script type="text/javascript" src="js/main.js"></script>
</body>
</html>