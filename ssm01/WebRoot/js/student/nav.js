var navs = [ {
	"title" : "系统首页",
	"icon" : "icon-computer",
	"href" : "main.htm",
	"spread" : false
}, {
	"title" : "个人信息",
	"icon" : "icon-text",
	"href" : "",
	"spread" : false,
	"children" : [
		{
			"title" : "查看信息",
			"icon" : "&#xe60a;",
			"href" : "student/studentInfo.htm",
			"spread" : false
		},
		{
			"title" : "修改密码",
			"icon" : "&#xe614;",
			"href" : "student/studentChangePwdPage.htm",
			"spread" : false
		}
	]
}, {
	"title" : "学生选课",
	"icon" : "icon-text",
	"href" : "",
	"spread" : false,
	"children" : [
		{
			"title" : "全部选课",
			"icon" : "&#xe60a;",
			"href" : "chooseCourse/chooseCourseList.htm?terms.flag=2",
			"spread" : false
		},
		{
			"title" : "已选课程",
			"icon" : "&#xe614;",
			"href" : "SC/getSCouseList.htm?termFlag=2",
			"spread" : false
		}
	]
} , {
	"title" : "学生成绩",
	"icon" : "icon-text",
	"href" : "SC/getSCouseList.htm?studId=66&termFlag=1",
	"spread" : false
} ]