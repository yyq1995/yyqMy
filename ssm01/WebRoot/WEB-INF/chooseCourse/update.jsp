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
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
  	
  	function getTeacher(){
  		//alert("3333");
  		var collegeId = document.getElementById("collegeId").value;
  		//alert("teacher:" + collegeId);
  		$.ajax({
			url:"teacher/teacherListAjax.htm?collegeId="+collegeId,
			
			method:"get",
			
			dataType:"json",
			
			success:function(data){
				var opts="<option value='0'>请选择老师</option>";
				for(var i = 0;i<data.length;i++){
					var teacherId=data[i].id;
					var teacherName=data[i].name;
					//alert("teacherId:" + teacherId)
					
					if(teacherId != null && teacherId == ${chooseCourse.teacher.id}){
						opts+="<option selected='selected' onclick='getTeacherName(this)' teacherName='"+teacherName+"' value='"+teacherId+"'>"+teacherName+"</option>";
					}else{
						opts+="<option onclick='getTeacherName(this)' teacherName='"+teacherName+"' value='"+teacherId+"'>"+teacherName+"</option>";
					}
				}
				//alert("opts");
				document.getElementById("teacherSelect").innerHTML=opts;
			},
			
			error:function(){
				alert("请求错误");
			}
		});
		
		getClassTime();
  	}
  	
  	function getClassTime(){
  		var max = document.getElementById("max").value;
  		//alert("max:" + max);
  		$.ajax({
			url:"classTime/classTimeListAjax.htm?max="+max,
			
			method:"get",
			
			dataType:"json",
			
			success:function(data){
				var opts="<option value='0'>请选择教室及时间安排</option>";
				for(var i = 0;i<data.length;i++){
					var classTimeId=data[i].id;
					var classTimeName=data[i].className;
					var classTimeTime=data[i].time;
					
					opts+="<option onclick='getClassTimeInfo(this)' classTimeId='"+classTimeId+"' classTimeName='"+classTimeName+"' classTimeTime='"+classTimeTime+"' value='"+classTimeId+"'>"+classTimeName+" "+classTimeTime+"</option>";
				}
				document.getElementById("classTimeSelect").innerHTML=opts;
			},
			error:function(){
				alert("请求错误");
			}
		});
  	}
  	
  	function getTeacherName(obj){
  		
  		var teacherName = obj.getAttribute("teacherName");
  		
  		document.getElementById("teacherName").value=teacherName;
  	}
  	
  	function getClassTimeInfo(obj){
  	
  		var classTimeId = obj.getAttribute("classTimeId");
  		var classTimeName = obj.getAttribute("classTimeName");
  		var classTimeTime = obj.getAttribute("classTimeTime");
  		
  		
  		document.getElementById("classTimeId").value=classTimeId;
  		document.getElementById("classTimeName").value=classTimeName;
  		document.getElementById("classTimeTime").value=classTimeTime;
  	}
  	function classroomSize() {
		var max = document.getElementById("max").value;
		if (max > 150 || max < 20) {
			document.getElementById("max").value = 20;
			document.getElementById("msgSize").innerHTML = "人数应在20到150之间!";
		} else {
			document.getElementById("msgSize").innerHTML = "";
			getClassTime();
		}
	}
  	
  </script>

<body onload="getTeacher()">
	修改选课
	<form action="chooseCourse/updateChooseCourse.htm" method="post">
		<table>
			<tr>
				<td><input name="id" value="${chooseCourse.id}" type="hidden"><br>
					选课编号：<label>${chooseCourse.no}</label> <input name="no"
					value="${chooseCourse.no}" id="collegeName" type="hidden">
				</td>
			</tr>
			<tr>
				<td>开课学院：<label>${chooseCourse.college.name}</label> <input
					name="college.id" value="${chooseCourse.college.id}" id="collegeId"
					type="hidden"> <input name="college.name"
					value="${chooseCourse.college.name}" id="collegeName" type="hidden">
				</td>
			</tr>
			<tr>
				<td>课程名称： <label>${chooseCourse.course.name}</label> <input
					name="course.id" value="${chooseCourse.course.id}" id="courseName"
					type="hidden"> <input name="course.name"
					value="${chooseCourse.course.name}" id="courseName" type="hidden">
					<input name="course.credit" value="${chooseCourse.course.credit}"
					id="courseCredit" type="hidden">

				</td>

			</tr>
			<tr>
				<td>老师名称：<select id="teacherSelect" name="teacher.id">
						<option value="0">请选择老师</option>
				</select> <input name="teacher.name" value="${chooseCourse.teacher.name}"
					id="teacherName" type="hidden">
				</td>
			</tr>
			<tr>
				<td><input id="selected" value="${chooseCourse.selected}"
					type="hidden" name="selected"> 上课人数： <input id="max"
					value="${chooseCourse.max}" type="text" name="max"
					onchange="classroomSize()"><label id="msgSize"></label></td>

			</tr>
			<tr>
				<td>教室及时间：<select id="classTimeSelect">
						<option value="0">请选择教室及时间安排</option>
				</select> <input name="classTime.id" value="${chooseCourse.classTime.id}"
					id="classTimeId" type="hidden"> <input
					name="classTime.className"
					value="${chooseCourse.classTime.className}" id="classTimeName"
					type="hidden"> <input name="classTime.time"
					value="${chooseCourse.classTime.time}" id="classTimeTime"
					type="hidden">
				</td>
			</tr>
			<tr>
				<td><input type="submit" value="修改" ">
				</td>
			</tr>
		</table>
	</form>
</body>

</html>
