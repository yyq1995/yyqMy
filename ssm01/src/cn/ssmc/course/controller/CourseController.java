package cn.ssmc.course.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;

import cn.ssmc.college.service.CollegeService;
import cn.ssmc.course.service.CourseService;
import cn.ssmc.entity.College;
import cn.ssmc.entity.Course;
import cn.ssmc.entity.PageBean;

@Controller
@RequestMapping("/course")
public class CourseController {

	@Autowired
	private CourseService courService;
	@Autowired
	private CollegeService collegeService;

	@RequestMapping("courseList.htm")
	public String getCourseListWithCollege(Course course, HttpServletRequest request, PageBean pb) {
		if (pb == null) {
			pb = new PageBean();
		}
		pb = courService.selectCWCPage(course, pb);

		List<College> collegeList = collegeService.selectCollegeList();
		request.setAttribute("collegeList", collegeList);
		request.setAttribute("course", course);
		request.setAttribute("pb", pb);
		return "course/main";
	}

	@RequestMapping(value = "addCourse.htm", method = RequestMethod.GET)
	public String addCoursePage(HttpServletRequest request) {

		List<College> collegeList = collegeService.selectCollegeList();
		request.setAttribute("collegeList", collegeList);
		return "course/add";
	}

	@RequestMapping(value = "addCourse.htm", method = RequestMethod.POST)
	public String addCourse(Course course) {
		courService.insert(course);
		return "tips/cAddSuccess";
	}

	@RequestMapping(value = "updCourse.htm", method = RequestMethod.GET)
	public String updateCoursePage(HttpServletRequest request) {
		String id = request.getParameter("id");
		Course course = courService.selectByPrimaryKey(id);
		System.out.println(course.getCollege().getName());
		request.setAttribute("course", course);
		List<College> collegeList = collegeService.selectCollegeList();
		request.setAttribute("collegeList", collegeList);
		return "course/update";
	}

	@RequestMapping(value = "updCourse.htm", method = RequestMethod.POST)
	public String updateCourse(Course course) {
		System.out.println(course);
		courService.updateByPrimaryKeySelective(course);
		return "tips/cUpdSuccess";
	}

	// 删除
	@RequestMapping("deleteCourse.htm")
	public String deleteCourse(HttpServletRequest request, PageBean pb, @RequestParam("id") int id) {

		if (courService.selectCountByChoos(id) == 0) {
			courService.deleteByPrimaryKey(id);
			request.setAttribute("cPage", pb.getcPage());
			request.setAttribute("maxSize", pb.getMaxSize());
			request.setAttribute("isCourse", 1);
			return "tips/delSuccess";
			/*return "redirect:courseList.htm?cPage=" + pb.getcPage() + "&maxSize=" + pb.getMaxSize();*/
		}else{
			return "tips/hasChoose";
		}

	}

	@RequestMapping("courseListAjax.htm")
	public void getCourseListAjax(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String collegeId = request.getParameter("collegeId");

		List<Course> courseList = courService.selectCourseByCollegeId(collegeId);

		Gson gson = new Gson();
		String json = gson.toJson(courseList);

		// System.out.println("json:" + json);

		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json;charset=utf-8");

		response.getWriter().print(json);
	}

}
