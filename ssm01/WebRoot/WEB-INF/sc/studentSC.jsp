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
	function openLayer(id) {
		layer.open({
			type : 2,
			title : "课程安排", //如果不需要标题栏,则设置为false,如果需要,则直接设置标题
			closeBtn : 0, //设置为0不显示关闭按钮
			maxmin : true, //是否显示最大化,最小化按钮
			shade : [ 0.5, "#999" ], //第一个参数为透明度,0为全透明.第二个参数为遮罩层颜色
			area : [ '300px', '250px' ], //使用2个参数定义宽度和高度,如果只定义一个参数,表示设置宽度,而高度由系统自定义
			//offset: 'rb', //右下角弹出
			//time: 2000, //2秒后自动关闭,通常针对与提示层来进行设置
			anim : 2, //动画效果.0-6.如果设置为-1表示无动画
			content : [ 'chooseCourse/showClassTime.htm?id=' + id, 'no' ], //iframe的url，no代表不显示滚动条
			btn : [ '关闭' ],
			//第二个以后的按钮自带关闭效果,如果不希望执行完毕后自动关闭
			//则应当在方法调用即将结束时返回fasle
			cancel : function() {
				return false;
			},
		})
	}
	//刷新
	function reloadPage() {
		window.location.reload();
	}
</script>
<body class="childrenBody">
	<blockquote class="layui-elem-quote news_search">
		<table>
			<tr>
				<c:if test="${termFlag ==2 }">
					<td>新学期的已选课程</td>
					<td><button class="layui-btn" onclick="reloadPage()">刷新</button></td>
				</c:if>
				<c:if test="${termFlag !=2 }">
					<td>以往学期所选课程的成绩</td>
				</c:if>
			</tr>
		</table>
	</blockquote>
	<div class="layui-form links_list">
		<table class="layui-table">
			<thead>
				<tr>
					<th style="width: 9%;">选课编号</th>
					<th style="width: 15%;">选课名称</th>
					<th style="width: 7%;">学分</th>
					<th style="width: 15%;">开课学院</th>
					<th style="width: 10%;">授课老师</th>
					<th style="width: 8%;">人数</th>
					<th style="width: 10%;">课程安排</th>
					<c:if test="${termFlag ==2 }">
						<th style="width: 8%;">退选</th>
					</c:if>
					<c:if test="${termFlag !=2 }">
						<th style="width: 8%;">学期</th>
						<th style="width: 8%;">成绩</th>
					</c:if>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${scList }" var="SC" varStatus="vs">
					<tr>
						<td>${SC.chooseCourse.no }</td>
						<td>${SC.chooseCourse.course.name }</td>
						<td>${SC.chooseCourse.course.credit }</td>
						<td>${SC.chooseCourse.college.name }</td>
						<td>${SC.chooseCourse.teacher.name }</td>
						<td>${SC.chooseCourse.selected }/${SC.chooseCourse.max }</td>
						<td><a href="javascript:openLayer(${SC.chooseCourse.id });">课程安排</a></td>
						<c:if test="${termFlag ==2 }">
							<td><a href="SC/deleteSC.htm?scId=${SC.id }">退选</a></td>
						</c:if>
						<c:if test="${termFlag !=2 }">
							<td>${SC.chooseCourse.terms.name}</td>
							<td>${SC.score}</td>
						</c:if>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<div id="page"></div>
	<script type="text/javascript" src="layui/layui.js"></script>
	<script type="text/javascript" src="js/admin/course.js"></script>
</body>
</html>