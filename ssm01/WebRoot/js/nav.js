var navs = [ {
	"title" : "后台首页",
	"icon" : "icon-computer",
	"href" : "WEB-INF/page/main.htm",
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
			"href" : "page/user/userInfo2.htm",
			"spread" : false
		},
		{
			"title" : "修改密码",
			"icon" : "&#xe614;",
			"href" : "page/user/changePwd.html",
			"spread" : false
		}
	]
}, {
	"title" : "课程管理",
	"icon" : "icon-text",
	"href" : "course/courseList.htm",
	"spread" : false
}, {
	"title" : "选课管理",
	"icon" : "icon-text",
	"href" : "chooseCourse/chooseCourseList.htm",
	"spread" : false
}, {
	"title" : "(学生)已选课程",
	"icon" : "icon-text",
	"href" : "SC/getSCouseList.htm?studId=66&termFlag=2",
	"spread" : false
}, {
	"title" : "(学生)查看成绩",
	"icon" : "icon-text",
	"href" : "SC/getSCouseList.htm?studId=66&termFlag=1",
	"spread" : false
}, {
	"title" : "4044页面",
	"icon" : "&#xe61c;",
	"href" : "page/404.html",
	"spread" : false
} ]