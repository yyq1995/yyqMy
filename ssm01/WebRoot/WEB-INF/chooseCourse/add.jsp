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

	function getCourse() {
		getTeacher();

		var collegeId = document.getElementById("collegeId").value;
		//alert(collegeId);

		$.ajax({
			url : "course/courseListAjax.htm?collegeId=" + collegeId,

			method : "get",

			dataType : "json",

			success : function(data) {

				var opts = "<option value='0'>请选择课程</option>";

				for (var i = 0; i < data.length; i++) {
					var courseId = data[i].id;
					var courseName = data[i].name;
					var courseCredit = data[i].credit;
					//alert(courseId);
					opts += "<option onclick='getCourseInfo(this)' "
						+ " courseName='" + courseName
						+ "' courseCredit='" + courseCredit
						+ "' value='" + courseId + "'>" + courseName + "</option>";

				//alert(opts);
				}
				document.getElementById("courseSelect").innerHTML = opts;
			},

			error : function() {
				alert("请求错误");
			}
		});
	}

	function getTeacher() {
		//alert("3333");
		var collegeId = document.getElementById("collegeId").value;
		//alert("teacher:" + collegeId);
		$.ajax({
			url : "teacher/teacherListAjax.htm?collegeId=" + collegeId,

			method : "get",

			dataType : "json",

			success : function(data) {
				var opts = "<option value='0'>请选择老师</option>";
				for (var i = 0; i < data.length; i++) {
					var teacherId = data[i].id;
					var teacherName = data[i].name;
					//alert("teacherId:" + teacherId)
					opts += "<option onclick='getTeacherName(this)' teacherName='" + teacherName + "' value='" + teacherId + "'>" + teacherName + "</option>";
				}
				//alert("opts");
				document.getElementById("teacherSelect").innerHTML = opts;
			},

			error : function() {
				alert("请求错误");
			}
		});
	}

	function getClassTime() {
		var max = document.getElementById("max").value;
		//alert("max:" + max);
		$.ajax({
			url : "classTime/classTimeListAjax.htm?max=" + max,

			method : "get",

			dataType : "json",

			success : function(data) {
				var opts = "<option value='0'>请选择教室及时间安排</option>";
				for (var i = 0; i < data.length; i++) {
					var classTimeId = data[i].id;
					var classTimeName = data[i].className;
					var classTimeTime = data[i].time;
					opts += "<option onclick='getClassTimeInfo(this)' classTimeName='" + classTimeName + "' classTimeTime='" + classTimeTime + "' value='" + classTimeId + "'>" + classTimeName + " " + classTimeTime + "</option>";
				}
				document.getElementById("classTimeSelect").innerHTML = opts;
			},
			error : function() {
				alert("请求错误");
			}
		});
	}

	function getCollegeName(obj) {
		//alert("objobjobj:" + obj);

		var collegeName = obj.getAttribute("collegeName");

		//alert("我是谁："+collegeName);
		document.getElementById("collegeName").value = collegeName;
	}

	function getCourseInfo(obj) {
		var courseName = obj.getAttribute("courseName");
		var courseCredit = obj.getAttribute("courseCredit");

		document.getElementById("courseName").value = courseName;
		document.getElementById("courseCredit").value = courseCredit;
	}

	function getTeacherName(obj) {
		var teacherName = obj.getAttribute("teacherName");

		document.getElementById("teacherName").value = teacherName;
	}

	function getClassTimeInfo(obj) {
		var classTimeName = obj.getAttribute("classTimeName");
		var classTimeTime = obj.getAttribute("classTimeTime");

		document.getElementById("classTimeName").value = classTimeName;
		document.getElementById("classTimeTime").value = classTimeTime;
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

<body>
	添加选课
	<form action="chooseCourse/addChooseCourse.htm" method="post">
		<input type="hidden" name="maxPage" value="${pb.maxPage }">
		<table>
			<tr>
				<td>选课编号：<input type="text" name="no"></td>
			</tr>
			<tr>
				<td>开课学院：<select id="collegeId" name="college.id"
					onchange="getCourse()">
						<c:forEach items="${collegeList }" var="college">
							<option value="${college.id }" id="college${college.id }"
								collegeName="${college.name }" onclick="getCollegeName(this)">${college.name }</option>
						</c:forEach>
				</select> <input name="college.name" id="collegeName" type="hidden">

				</td>
			</tr>
			<tr>
				<td>课程名称：<select id="courseSelect" name="course.id">
						<option value="0">请选择课程</option>
				</select> <input name="course.name" id="courseName" type="hidden"> <input
					name="course.credit" id="courseCredit" type="hidden">

				</td>

			</tr>
			<tr>
				<td>老师名称：<select id="teacherSelect" name="teacher.id">
						<option value="0">请选择老师</option>
				</select> <input name="teacher.name" id="teacherName" type="hidden">
				</td>
			</tr>
			<tr>
				<td>上课人数：<input id="max" type="text" name="max"
					onchange="classroomSize()"><label id="msgSize"></label>
				</td>

			</tr>
			<tr>
				<td>教室及时间：<select id="classTimeSelect" name="classTime.id">
						<option value="0">请选择教室及时间安排</option>
				</select> <input name="classTime.className" id="classTimeName" type="hidden">
					<input name="classTime.time" id="classTimeTime" type="hidden">
				</td>
			</tr>
			<tr>
				<td><input type="submit" value="添加" onclick="closeLay()">
				</td>
			</tr>
		</table>
	</form>
</body>
<!--  <script type="text/javascript">
  	function closeLay(){
  		window.parent.closeLayer();
  	}
  </script> -->
</html>
