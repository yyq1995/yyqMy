var navs = [ {
	"title" : "系统首页",
	"icon" : "icon-computer",
	"href" : "main.htm",
	"spread" : false
},{
	"title" : "个人信息",
	"icon" : "icon-text",
	"href" : "",
	"spread" : false,
	"children" : [
		{
			"title" : "查看信息",
			"icon" : "&#xe60a;",
			"href" : "admin/adminInfo.htm",
			"spread" : false
		},
		{
			"title" : "修改密码",
			"icon" : "&#xe614;",
			"href" : "admin/adminChangePwdPage.htm",
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
	"href" : "chooseCourse/chooseCourseList.htm?terms.flag=2",
	"spread" : false
},{
	"title" : "成绩信息",
	"icon" : "icon-text",
	"href" : "chooseCourse/chooseCourseList.htm?terms.flag=1",
	"spread" : false
}]