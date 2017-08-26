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
		function openLayer(id){
		layer.open({
			type:2,
			title: "课程安排",//如果不需要标题栏,则设置为false,如果需要,则直接设置标题
			closeBtn: 0, //设置为0不显示关闭按钮
			maxmin :true,//是否显示最大化,最小化按钮
			shade: [0.5,"#999"],//第一个参数为透明度,0为全透明.第二个参数为遮罩层颜色
			area: ['300px', '250px'],//使用2个参数定义宽度和高度,如果只定义一个参数,表示设置宽度,而高度由系统自定义
			//offset: 'rb', //右下角弹出
			//time: 2000, //2秒后自动关闭,通常针对与提示层来进行设置
			anim: 2,//动画效果.0-6.如果设置为-1表示无动画
			content: ['chooseCourse/showClassTime.htm?id='+id, 'no'], //iframe的url，no代表不显示滚动条
			btn : ['关闭'],
			//第二个以后的按钮自带关闭效果,如果不希望执行完毕后自动关闭
			//则应当在方法调用即将结束时返回fasle
			cancel:function(){
				return false;
			},
		})
	}
	var index;
	function openLayerAdd(){
		index = layer.open({
			type:2,
			title: "选课添加",//如果不需要标题栏,则设置为false,如果需要,则直接设置标题
			closeBtn: 0, //设置为0不显示关闭按钮
			maxmin :true,//是否显示最大化,最小化按钮
			shade: [0.5,"#999"],//第一个参数为透明度,0为全透明.第二个参数为遮罩层颜色
			area: ['500px', '400px'],//使用2个参数定义宽度和高度,如果只定义一个参数,表示设置宽度,而高度由系统自定义
			//offset: 'rb', //右下角弹出
			//time: 2000, //2秒后自动关闭,通常针对与提示层来进行设置
			anim: 2,//动画效果.0-6.如果设置为-1表示无动画
			content: ['chooseCourse/showChooseCourseAddPage.htm?maxPage=${pb.maxPage }', 'no'], //iframe的url，no代表不显示滚动条
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
			title: "选课修改",//如果不需要标题栏,则设置为false,如果需要,则直接设置标题
			closeBtn: 0, //设置为0不显示关闭按钮
			maxmin :true,//是否显示最大化,最小化按钮
			shade: [0.5,"#999"],//第一个参数为透明度,0为全透明.第二个参数为遮罩层颜色
			area: ['500px', '400px'],//使用2个参数定义宽度和高度,如果只定义一个参数,表示设置宽度,而高度由系统自定义
			//offset: 'rb', //右下角弹出
			//time: 2000, //2秒后自动关闭,通常针对与提示层来进行设置
			anim: 2,//动画效果.0-6.如果设置为-1表示无动画
			content: ['chooseCourse/showChooseCourseUpdatePage.htm?ccID='+id, 'no'], //iframe的url，no代表不显示滚动条
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
		window.location.href("chooseCourseList.htm?terms.flag=${termFlag}&maxPage=${pb.maxPage}&maxSize=${pb.maxSize}");
	}
	
	function closeUpdLayer(){
		layer.close(index);
		window.location.href("chooseCourseList.htm?terms.flag=${termFlag}&cPage=${pb.cPage}");
	}
	function getMsg(id,max){
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
			content: ['SC/addSC.htm?ccId='+id+'&max='+max, 'no'], //iframe的url，no代表不显示滚动条
			//btn : ['关闭'],
			//第二个以后的按钮自带关闭效果,如果不希望执行完毕后自动关闭
			//则应当在方法调用即将结束时返回fasle
			//cancel:function(){
				//return false;
			//},
		})
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
			content: ['chooseCourse/deleteChooseCourse.htm?ccID='+id+'&cPage='+cPage+'&maxSize=${pb.maxSize }'+maxSize, 'no'], //iframe的url，no代表不显示滚动条
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
	
	function init() {
		var cID = "${cc.college.id}";
		//获取指定公寓id代表的公寓的option对象
		var college = document.getElementById("college" + cID);
		//选中指定公寓
		college.selected = "selected"
		//获取指定公寓的楼层信息
	}
	function que(){
		document.getElementById("cPage").value=1;
	    document.getElementById("f1").submit();
	}
	function closeAddLayer(){
		window.location.href("/ssm/chooseCourse/chooseCourseList.htm?cPage=${pb.cPage }&terms.flag=${termFlag}");
	}
	//刷新
	function reloadPage(){
		window.location.reload();
	}
</script>
<body class="childrenBody" onload="init()">
	<blockquote class="layui-elem-quote news_search">
		<table>
			<tr>
				<td><c:if test="${teacherLogin != null }">
						<c:if test="${termFlag ==2}">
				新学期的您所需要授课的选课信息
			</c:if>
						<c:if test="${termFlag ==1}">
				上学期的课程成绩信息
			</c:if>
					</c:if></td>
		
			<c:if test="${teacherLogin == null }">
				
					<td colspan="3"><c:if test="${adminLogin != null }">
							<c:if test="${termFlag ==2}">
								<td><h4>新学期的选课管理</h4></td>
							</c:if>
							<c:if test="${termFlag ==1}">
								<td>上学期的成绩管理：</td>
							</c:if>
						</c:if></td>
				
			</c:if>
			<td colspan="6">&nbsp;</td>
			<td colspan="1"><c:if test="${adminLogin!=null }">
								<c:if test="${termFlag ==2}">
									<button class="layui-btn">
										<a href="javascript:openLayerAdd();">添加</a>
									</button>
								</c:if>
							</c:if></td>
					</tr>
			</table>		
		</blockquote>
		<blockquote class="layui-elem-quote news_search">
		<!--表单 查询课表提交表单 如果老师不可见-->
		<c:if test="${teacherLogin == null }">
			<form id="f1" action="chooseCourse/chooseCourseList.htm"
				method="post">
				<input type="hidden" name="cPage" value="${pb.cPage }" id="cPage" />
				<input type="hidden" name="terms.flag" value="${termFlag}">
				<table>
					
					<tr>
						<td colspan="3">&nbsp;</td>
					</tr>
					<tr>
						<td><label class="layui-form-label">开课学院：</label></td>
						<td><select name="college.id">
								<option value="0">开课学院</option>
								<c:forEach items="${collegeList }" var="college">
									<option value="${college.id }" id="college${college.id }">${college.name }</option>
								</c:forEach>
						</select></td>
						<td><label class="layui-form-label">授课老师</label></td>
						<td><input type="text" class="layui-input text"
							value="${cc.teacher.name }" placeholder="请输入老师名称"
							name="teacher.name"></td>
						<td><label class="layui-form-label">课程名</label></td>
						<td><input type="text" value="${cc.course.name }"
							placeholder="请输入课程名" class="layui-input text" name="course.name"></td>

						<td>
							<button class="layui-btn" onclick="que();">查询</button> <!-- <button type="reset" class="layui-btn layui-btn-primary">重置</button> -->
							<button type="reset" class="layui-btn layui-btn-primary">重置</button>
							<button class="layui-btn" onclick="reloadPage()">刷新</button>
						</td>
					
					</tr>
				</table>
			</form>
		</c:if>
	</blockquote>
	<div class="layui-form links_list">
		<table class="layui-table">
			<thead>
				<tr>
					<th style="width: 9%;">编号</th>
					<th style="width: 15%;">选课名称</th>
					<th style="width: 7%;">学分</th>
					<th style="width: 15%;">开课学院</th>
					<th style="width: 10%;">授课老师</th>
					<th style="width: 8%;">人数</th>
					<th style="width: 10%;">课程安排</th>

					<c:if test="${termFlag == 2 }">
						<c:if test="${adminLogin != null }">
							<th style="width: 8%;">修改</th>
							<th style="width: 8%;">删除</th>
						</c:if>
						<c:if test="${studentLogin != null }">
							<th style="width: 8%;">选课</th>
						</c:if>
					</c:if>
					<c:if test="${adminLogin != null || teacherLogin != null}">
						<th style="width: 15%;">名单</th>
					</c:if>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${pb.list }" var="chooseCourse" varStatus="vs">
					<tr>
						<td>${chooseCourse.no }</td>
						<td>${chooseCourse.course.name }</td>
						<td>${chooseCourse.course.credit }</td>
						<td>${chooseCourse.college.name }</td>
						<td>${chooseCourse.teacher.name }</td>
						<td>${chooseCourse.selected }/${chooseCourse.max }</td>
						<td><a href="javascript:openLayer(${chooseCourse.id });">课程安排</a></td>
						<c:if test="${termFlag==2 }">
							<c:if test="${adminLogin != null }">
								<td><a
									href="javascript:openLayerUpdate(${chooseCourse.id });">修改</a></td>
								<td><a
									href="javascript:deleteMsg(${chooseCourse.id },${pb.cPage },${pb.maxSize })">删除</a></td>
							</c:if>
							<c:if test="${studentLogin!=null }">
								<td><a
									href="javascript:getMsg(${chooseCourse.id },${chooseCourse.max })">选课</a></td>
							</c:if>
						</c:if>
						<c:if test="${adminLogin != null || teacherLogin != null}">
							<td><a
								href="SC/getStdCList.htm?chooseCourse.id=${chooseCourse.id }&chooseCourse.terms.flag=${chooseCourse.terms.flag}&addScore=0">名单</a></td>
						</c:if>
					</tr>
				</c:forEach>
				<tr>
					<td colspan="6">共${pb.maxSize }条纪录，当前第${pb.cPage }/${pb.maxPage }页，每页${pb.pageSize }条纪录
					</td>
					<td colspan="4"><a href="javascript:search(1)">首页</a> <a
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