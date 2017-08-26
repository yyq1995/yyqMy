package cn.ssmc.chooseCourse.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.ssmc.chooseCourse.service.ChooseCourseService;
import cn.ssmc.college.service.CollegeService;
import cn.ssmc.entity.ChooseCourse;
import cn.ssmc.entity.ClassTime;
import cn.ssmc.entity.College;
import cn.ssmc.entity.PageBean;
import cn.ssmc.entity.Teacher;

@Controller
@RequestMapping("/chooseCourse")
public class ChooseCourseController {

	@Autowired
	private ChooseCourseService chooseCourseService;

	@Autowired
	private CollegeService collegeService;
	


	// 选课页面(GET)，或是在其中点击查询(POST)
	//如果是老师，那么只看见自己需要教的的选课
	@RequestMapping("chooseCourseList.htm")
	public String selectChooseCourse(ChooseCourse chooseCourse, HttpServletRequest request, PageBean pb) {
		Teacher teacher = (Teacher) request.getSession().getAttribute("teacherLogin");
		if(teacher!=null){
			chooseCourse.setTeacher(teacher);
		}
		if (pb == null) {
			pb = new PageBean();
		}
		request.setAttribute("termFlag", chooseCourse.getTerms().getFlag());
		pb = chooseCourseService.selectCCPage(chooseCourse, pb);
		request.setAttribute("pb", pb);
		
		List<College> collegeList = collegeService.selectCollegeList();
		request.setAttribute("collegeList", collegeList);
		request.setAttribute("cc", chooseCourse);
		// 返回的是一個共用的chooseCourse頁面
		return "chooseCourse/main";
	}

	//删除之前要先查是否有学生选课
	@RequestMapping("deleteChooseCourse.htm")
	public String deleteChooseCourse(@RequestParam("ccID") int ccID,PageBean pb,HttpServletRequest request) {
		
		if(chooseCourseService.countSelect(ccID)>0){
			return "tips/hasStud";
		}
		chooseCourseService.deleteByPrimaryKey(ccID);
		request.setAttribute("cPage", pb.getcPage());
		request.setAttribute("maxSize", pb.getMaxSize());
		request.setAttribute("isCourse", 2);
		return "tips/delSuccess";
		/*return "redirect:chooseCourseList.htm?terms.flag=2&cPage="+pb.getcPage()+"&maxSize="+pb.getMaxSize();*/
	}

	@RequestMapping("showClassTime.htm")
	public String selectClassTime(HttpServletRequest request) {
		String ccId = request.getParameter("id");
		ClassTime classTime = chooseCourseService.selectClassTime(ccId);
		request.setAttribute("classTime", classTime);

		// 返回的是一個共用的courseTime頁面
		return "dialog/courseTime";
	}

	// 打开添加选课的页面
	@RequestMapping("showChooseCourseAddPage.htm")
	public String showChooseCourseAddPage(PageBean pb,HttpServletRequest request) {

		List<College> collegeList = collegeService.selectCollegeList();
		request.setAttribute("collegeList", collegeList);
		request.setAttribute("pb", pb);
		return "chooseCourse/add";
	}
	
	@RequestMapping("addChooseCourse.htm")
	public String addChooseCourse(PageBean pb,ChooseCourse chooseCourse,HttpServletRequest request){
		int max = pb.getMaxPage();
		System.out.println(max+"添加页面的值已拿到，接下来执行添加再重定向");
		chooseCourseService.insert(chooseCourse);
		/*request.setAttribute("terms.flag", 2);*/
		request.setAttribute("termsFlag", 2);
		request.setAttribute("maxPage", max);
		/*return "rediect:chooseCourseList.htm?terms.flag=2&maxPage=2";*/
		return "tips/addSuccess";
	}
	
	//跳转修改页面
		@RequestMapping("showChooseCourseUpdatePage.htm")
		public String showChooseCourseUpdatePage(@RequestParam("ccID") int ccID,HttpServletRequest request) {
			ChooseCourse chooseCourse = chooseCourseService.selectByPrimaryKey(ccID);
			request.setAttribute("chooseCourse", chooseCourse);
			System.out.println(chooseCourse);
			return "chooseCourse/update";
		}
		
		//修改
		@RequestMapping("updateChooseCourse.htm")
		public String updateChooseCourse(ChooseCourse chooseCourse){
			
			chooseCourseService.updateByPrimaryKey(chooseCourse);
			
			return "tips/ccUpdSuccess";
		}
}
