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
	function search(page){
		if(page>=1 && page <= ${pb.maxPage} ){
			document.getElementById("cPage").value=page;
	    	document.getElementById("f1").submit();
		}
	    
	}
	function search2(){
		var pages;
		pages=document.getElementById("page").value;
		alert(pages);
		search(pages);
	}
	var index;
	function openLayerAdd(){
		index = layer.open({
			type:2,
			title: "课程添加",//如果不需要标题栏,则设置为false,如果需要,则直接设置标题
			closeBtn: 0, //设置为0不显示关闭按钮
			maxmin :true,//是否显示最大化,最小化按钮
			shade: [0.5,"#999"],//第一个参数为透明度,0为全透明.第二个参数为遮罩层颜色
			area: ['500px', '400px'],//使用2个参数定义宽度和高度,如果只定义一个参数,表示设置宽度,而高度由系统自定义
			//offset: 'rb', //右下角弹出
			//time: 2000, //2秒后自动关闭,通常针对与提示层来进行设置
			anim: 2,//动画效果.0-6.如果设置为-1表示无动画
			content: ['course/addCourse.htm', 'no'], //iframe的url，no代表不显示滚动条
			btn : ['关闭'],
			//第二个以后的按钮自带关闭效果,如果不希望执行完毕后自动关闭
			//则应当在方法调用即将结束时返回fasle
			cancel:function(){
				return false;
			},
		})
	}
	function openLayerUpdate(id){
		index = layer.open({
			type:2,
			title: "课程修改",//如果不需要标题栏,则设置为false,如果需要,则直接设置标题
			closeBtn: 0, //设置为0不显示关闭按钮
			maxmin :true,//是否显示最大化,最小化按钮
			shade: [0.5,"#999"],//第一个参数为透明度,0为全透明.第二个参数为遮罩层颜色
			area: ['500px', '400px'],//使用2个参数定义宽度和高度,如果只定义一个参数,表示设置宽度,而高度由系统自定义
			//offset: 'rb', //右下角弹出
			//time: 2000, //2秒后自动关闭,通常针对与提示层来进行设置
			anim: 2,//动画效果.0-6.如果设置为-1表示无动画
			content: ['course/updCourse.htm?id='+id, 'no'], //iframe的url，no代表不显示滚动条
			btn : ['关闭'],
			//第二个以后的按钮自带关闭效果,如果不希望执行完毕后自动关闭
			//则应当在方法调用即将结束时返回fasle
			cancel:function(){
				return false;
			},
		})
	}
	function closeLayer(){
		layer.close(index);
		window.location.href("courseList.htm?maxPage=${pb.maxPage}&maxSize=${pb.maxSize}");
	}
	function closeUpdLayer(){
		layer.close(index);
		window.location.href("courseList.htm?cPage=${pb.cPage}");
	}
	function init() {
		//从request中获取上次搜索时使用的参数
	/* 	var deptId = "${deptId}",
			floor = "${floor}";

		//获取指定公寓id代表的公寓的option对象
		var dept = document.getElementById("dept" + deptId);
		//选中指定公寓
		dept.selected = "selected"
		//获取指定公寓的楼层信息
		test(dept);
		//选中上次搜索时使用的楼层参数
		document.getElementById("floor" + floor).selected = "selected";
		getRoom(); */
		var cID = "${course.college.id}";

		
		//获取指定公寓id代表的公寓的option对象
		var college = document.getElementById("college" + cID);
		//选中指定公寓
		college.selected = "selected"
		//获取指定公寓的楼层信息
		
	}
	function deleteMsg(id,cPage,maxSize){
		
		index = layer.open({
			type:2,
			title: false,//如果不需要标题栏,则设置为false,如果需要,则直接设置标题
			closeBtn: 0, //设置为0不显示关闭按钮
			maxmin :false,//是否显示最大化,最小化按钮
			shade: 0,//第一个参数为透明度,0为全透明.第二个参数为遮罩层颜色
			area: ['200px', '100px'],//使用2个参数定义宽度和高度,如果只定义一个参数,表示设置宽度,而高度由系统自定义
			//offset: 'rb', //右下角弹出
			time: 2500, //2秒后自动关闭,通常针对与提示层来进行设置
			anim: 2,//动画效果.0-6.如果设置为-1表示无动画
			content: ['course/deleteCourse.htm?id='+id+'&cPage='+cPage+'&maxSize='+maxSize, 'no'], //iframe的url，no代表不显示滚动条
			//btn : ['关闭'],
			//第二个以后的按钮自带关闭效果,如果不希望执行完毕后自动关闭
			//则应当在方法调用即将结束时返回fasle
			//cancel:function(){
				//return false;
			//},
		})
	}
	function tipsLayer(url){
		window.location.href(url);
	}
	function que(){
		document.getElementById("cPage").value=1;
	    document.getElementById("f1").submit();
	}
</script>
<body class="childrenBody" onload="init()">
	<blockquote class="layui-form links_list">
		<!--表单 查询课表提交表单-->
		<form id="f1" action="course/courseList.htm" method="post">
			<input type="hidden" name="cPage" value="${pb.cPage }" id="cPage" />

			<div class="layui-form-item">
				<table>
					<tr>
						<td><label class="layui-form-label">开课学院</label></td>
						<td><select name="college.id">
								<option value="0">开课学院</option>
								<c:forEach items="${collegeList }" var="college">
									<option value="${college.id }" id="college${college.id }">${college.name }</option>
								</c:forEach>
						</select></td>
						<td><label class="layui-form-label">课程名</label>
						
							<div class="layui-input-block">
								<input type="text" value="${course.name }" placeholder="请输入课程名"
									name="name" class="layui-input text">
							</div>
						</td>
						<td><button class="layui-btn"  onclick="que();">查询</button>
						<button type="reset" class="layui-btn layui-btn-primary">重置</button>
						<button class="layui-btn"><a href="javascript:openLayerAdd();">添加</a></button>
						</td>
						
					</tr>


				</table>

			</div>
		</form>
	</blockquote>
	<div class="layui-form links_list">
		<table class="layui-table">
			<thead>
				<tr>
					<th style="width: 8%;">课程编号</th>
					<th style="width: 26%;">课程名称</th>
					<th style="width: 7%;">学分</th>
					<th style="width: 7%">学时</th>
					<th style="width: 18%;">开课学院</th>
					<th style="width: 10%;">删除</th>
					<th style="width: 10%;">修改</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${pb.list }" var="course" varStatus="vs">
					<tr>
						<td>${course.no }</td>
						<td>${course.name }</td>
						<td>${course.credit }</td>
						<td>${course.hour }</td>
						<td>${course.college.name }</td>
						<td><a href="javascript:openLayerUpdate(${course.id });">修改</a></td>
						<td><a
							href="javascript:deleteMsg(${course.id },${pb.cPage },${pb.maxSize });">删除</a></td>
					</tr>
				</c:forEach>
				<tr>
					<td colspan="4">共${pb.maxSize }条纪录，当前第${pb.cPage }/${pb.maxPage }页，每页${pb.pageSize }条纪录
					</td>
					<td colspan="3"><a href="javascript:search(1)">首页</a> <a
						href="javascript:search(${pb.cPage-1 })">上一页</a> <a
						href="javascript:search(${pb.cPage+1 })">下一页</a> &nbsp;&nbsp;转到<input
						name="page" id="page" type="text"> <a
						href="javascript:search2();">GO</a></td>

				</tr>
			</tbody>
		</table>
	</div>

	<div id="page"></div>
	<script type="text/javascript" src="layui/layui.js"></script>
	<script type="text/javascript" src="js/admin/course.js"></script>
</body>
</html>
