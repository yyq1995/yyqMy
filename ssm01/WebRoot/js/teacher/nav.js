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
			"href" : "teacher/teacherInfo.htm",
			"spread" : false
		},
		{
			"title" : "修改密码",
			"icon" : "&#xe614;",
			"href" : "teacher/teacherChangePwdPage.htm",
			"spread" : false
		}
	]
},{
	"title" : "选课信息",
	"icon" : "icon-text",
	"href" : "chooseCourse/chooseCourseList.htm?terms.flag=2",
	"spread" : false
}, {
	"title" : "成绩管理",
	"icon" : "icon-text",
	"href" : "",
	"spread" : false,
	"children" : [
		{
			"title" : "成绩信息",
			"icon" : "&#xe60a;",
			"href" : "chooseCourse/chooseCourseList.htm?terms.flag=1",
			"spread" : false
		},
		{
			"title" : "批量添加成绩",
			"icon" : "&#xe614;",
			"href" : "SC/addScore.htm",
			"spread" : false
		}
	]
}]