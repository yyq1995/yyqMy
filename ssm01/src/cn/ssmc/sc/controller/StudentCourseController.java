package cn.ssmc.sc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.ssmc.chooseCourse.service.ChooseCourseService;
import cn.ssmc.college.service.CollegeService;
import cn.ssmc.entity.Admin;
import cn.ssmc.entity.ChooseCourse;
import cn.ssmc.entity.College;
import cn.ssmc.entity.Course;
import cn.ssmc.entity.PageBean;
import cn.ssmc.entity.Student;
import cn.ssmc.entity.StudentChos;
import cn.ssmc.entity.Teacher;
import cn.ssmc.sc.service.StudentCourseService;

//从session中拿到学生的值还没写
@Controller
@RequestMapping("/SC")
public class StudentCourseController {

	@Autowired
	private StudentCourseService studentCourseService;

	@Autowired
	private ChooseCourseService chooseCourseService;

	@Autowired
	private CollegeService collegeService;

	// 学生视图 学生已选课表
	// 拿到学生的id
	// 当前学期 flag=2 查新学期已选课表---已选课表视图
	// 查成绩 flag！=2 ---------------查成绩视图
	@RequestMapping("getSCouseList")
	public String getSCouseList(@RequestParam("termFlag") int termFlag, HttpServletRequest request) {
		int studId = ((Student) request.getSession().getAttribute("studentLogin")).getId();
		List<StudentChos> list = studentCourseService.selectWithChooseStudView(studId, termFlag);
		request.setAttribute("scList", list);
		request.setAttribute("termFlag", termFlag);
		return "sc/studentSC";
	}

	// 管理员老师视图 某门课有哪些学生选课
	// 视图1：当前学期 某门课 有哪些学生选课
	// 视图2：之前学期 某门课 学生的成绩
	// get 拿到ccID的值 和term的flag 和 是否是增加addScore成绩
	@RequestMapping("getStdCList")
	public String getSCouseListByCC(StudentChos studentChos, @RequestParam("addScore") Integer addScore,
			HttpServletRequest request, PageBean pb) {
		if (pb == null) {
			pb = new PageBean();
		}
		/*if (studentChos.getStudent() != null) {
			System.out.println(studentChos.getStudent().getName());
		}*/

		pb = studentCourseService.selectWCVPage(pb, studentChos,addScore);
		request.setAttribute("pb", pb);
		request.setAttribute("SC1", studentChos);

		List<College> collegeList = collegeService.selectCollegeList();
		request.setAttribute("collegeList", collegeList);

		if (addScore == 1) {
			return "sc/scoreList";
		}
		return "sc/SCList";
	}

	// 学生选课----------SESSION中拿到学生的id 已经写完
	@RequestMapping("addSC")
	public String addStdCouse(@RequestParam("ccId") int ccId, @RequestParam("max") int max,
			HttpServletRequest request) {
		int studID = ((Student) request.getSession().getAttribute("studentLogin")).getId();
		if (chooseCourseService.countSelect(ccId) < max) {
			if (studentCourseService.isExist(ccId, studID) != null) {
				return "tips/duplicateAddSC";
			}

			if (studentCourseService.selectStudNum(studID) >= 3) {
				return "tips/tooMore";
			}
			studentCourseService.insert(studID, ccId);
			/* return "redirect:getSCouseList.htm?termFlag=2"; */
			return "tips/scAddSuccess";
		}

		return "tips/outMax";
	}

	// 学生选课----------SESSION中拿到学生的id 还没写
	// 两个视图 学生退选返回到学生已选课表页面
	// 管理员删除返回到某课表有哪些学生选择 学生的信息的页面-------还没写
	@RequestMapping("deleteSC")
	public String deleteStdCouse(@RequestParam("scId") int scID, HttpServletRequest request, PageBean pb) {
		Student student = (Student) request.getSession().getAttribute("studentLogin");
		Admin admin = (Admin) request.getSession().getAttribute("adminLogin");
		if (student != null) {
			studentCourseService.deleteByPrimaryKey(scID);
			int studId = ((Student) request.getSession().getAttribute("studentLogin")).getId();
			return "redirect:getSCouseList.htm?studId=" + studId + "&termFlag=2";
		}
		if (admin != null) {
			StudentChos sc = studentCourseService.selectGetChooseByKey(scID);
			studentCourseService.deleteByPrimaryKey(scID);
			return "redirect:getStdCList.htm?chooseCourse.id=" + sc.getChooseCourse().getId()
					+ "&chooseCourse.terms.flag=2&addScore=0&cPage=" + pb.getcPage() + "&maxSize=" + pb.getMaxSize();
		}
		return null;

	}

	// 修改成绩
	@RequestMapping(value = "updateSC", method = RequestMethod.GET)
	public String updateSCPage(StudentChos studentChos, HttpServletRequest request) {
		StudentChos sc = studentCourseService.selectByKeyScore(studentChos.getId());
		request.setAttribute("sc", sc);
		return "dialog/updScore";
	}

	// 修改成绩
	@RequestMapping(value = "updateSC", method = RequestMethod.POST)
	public String updateSC(StudentChos sc, HttpServletRequest request) {
		System.out.println(sc);
		studentCourseService.updateByPrimaryKeySelective(sc);
		request.setAttribute("sc", sc);
		/*
		 * return "redirect:getStdCList.htm?chooseCourse.id=" +
		 * sc.getChooseCourse().getId() +
		 * "&chooseCourse.terms.flag=1&addScore=0";
		 */
		return "tips/scoreSuccess";
	}

	// 添加成绩
	@RequestMapping(value = "addScore", method = RequestMethod.GET)
	public String addScore(HttpServletRequest request) {
		Teacher teacher = (Teacher) request.getSession().getAttribute("teacherLogin");
		if (teacher != null) {
			List<ChooseCourse> list = studentCourseService.selectCname(teacher.getId());
			if (list.size() != 0) {
				System.out.println("-----");
				request.setAttribute("ccList", list);
				request.setAttribute("termFlag", list.get(0).getTerms().getFlag());
				return "dialog/addScore";
			}
		}
		return "tips/noCCscore";
	}

	@RequestMapping(value = "addScore", method = RequestMethod.POST)
	public String addScoreForm(StudentChos sc, HttpServletRequest request) {
		Teacher teacher = (Teacher) request.getSession().getAttribute("teacherLogin");
		if (teacher != null) {
			System.out.println(sc);
			return "redirect:getStdCList.htm?chooseCourse.id=" + sc.getChooseCourse().getId()
					+ "&chooseCourse.terms.flag=" + sc.getChooseCourse().getTerms().getFlag() + "&addScore=1";
		}
		return "";
	}

	@RequestMapping(value = "submitScore", method = RequestMethod.POST)
	public String submitScore(@RequestParam("idScore") String idScore, HttpServletRequest request) {
		Teacher teacher = (Teacher) request.getSession().getAttribute("teacherLogin");
		StudentChos sc = new StudentChos();
		if (teacher != null) {
			sc = studentCourseService.updateByList(idScore);
		}
		return "redirect:getStdCList.htm?chooseCourse.id=" + sc.getChooseCourse().getId()
				+ "&chooseCourse.terms.flag=1&addScore=0";
	}

}
