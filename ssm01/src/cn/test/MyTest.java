package cn.test;

import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.ssmc.dao.ChooseCourseDao;
import cn.ssmc.dao.ClassTimeDao;
import cn.ssmc.dao.CollegeDao;
import cn.ssmc.dao.CourseDao;
import cn.ssmc.dao.StudentChosDao;
import cn.ssmc.dao.TeacherDao;
import cn.ssmc.dao.TermsDao;
import cn.ssmc.entity.ChooseCourse;
import cn.ssmc.entity.ClassTime;
import cn.ssmc.entity.College;
import cn.ssmc.entity.Course;
import cn.ssmc.entity.StudentChos;
import cn.ssmc.entity.Teacher;
import cn.ssmc.entity.Terms;


public class MyTest {
	private ApplicationContext applicationContext;

	@Before
	public void setUp() throws Exception {
		applicationContext = new ClassPathXmlApplicationContext("classpath:spring-common.xml");
	}
	
	
	
	@Test
	public void test333() throws Exception {
			CollegeDao collegeDao = (CollegeDao) applicationContext.getBean("collegeDao");
			College college = collegeDao.selectByPrimaryKey(1);
			college.setName("理学院");
			collegeDao.updateByPrimaryKey(college);
			System.out.println(college);
	}
	@Test
	public void test333222() throws Exception {
			CollegeDao collegeDao = (CollegeDao) applicationContext.getBean("collegeDao");
			College college = new College();
			college.setName("体育学院");
			collegeDao.insert(college);
	}
	@Test
	public void test3L() throws Exception {
			CourseDao courseDao = (CourseDao) applicationContext.getBean("courseDao");
			Course course = new Course();
			College college = new College();
			college.setId(1);
			course.setCollege(college);
			List<Course> list = courseDao.selectCourseWithCollege(course);
			for (Course course2 : list) {
				System.out.println(course2);
				System.out.println(course2.getCollege().getName());
			}
	}
	@Test
	public void testrL() throws Exception {
			ClassTimeDao classtimeDao = (ClassTimeDao) applicationContext.getBean("classTimeDao");
			List<ClassTime> list = classtimeDao.selectClassBySize(1);
			for (ClassTime classTime : list) {
				System.out.println(classTime);
			}
	}
	@Test
	public void testr22() throws Exception {
			TermsDao termsDao = (TermsDao) applicationContext.getBean("termsDao");
			Terms terms = termsDao.selectBefore1();
			System.out.println(terms);
	}
	@Test
	public void testr222() throws Exception {
			ChooseCourseDao chooseCourseDao = (ChooseCourseDao) applicationContext.getBean("chooseCourseDao");
			ChooseCourse chooseCourse = chooseCourseDao.selectByPrimaryKey(1);
			
			System.out.println(chooseCourse);
			System.out.println(chooseCourse.getClassTime().getTime()+chooseCourse.getClassTime().getClassName());
			System.out.println(chooseCourse.getCollege().getName());
			System.out.println(chooseCourse.getTeacher().getName());
	}
	@Test
	public void testr2222() throws Exception {
		ChooseCourseDao chooseCourseDao = (ChooseCourseDao) applicationContext.getBean("chooseCourseDao");
		ChooseCourse cc = new ChooseCourse();
		Course course = new Course();
		cc.setCourse(course);
		List<ChooseCourse> list = chooseCourseDao.selectChooseCourse(cc);
		for (ChooseCourse chooseCourse2 : list) {
			System.out.println(chooseCourse2);
		}
		
		/*System.out.println(chooseCourse);
		System.out.println(chooseCourse.getClassTime().getTime()+chooseCourse.getClassTime().getClassName());
		System.out.println(chooseCourse.getCollege().getName());
		System.out.println(chooseCourse.getTeacher().getName());*/
}
	
	@Test
	public void testr2322() throws Exception {
		ChooseCourseDao chooseCourseDao = (ChooseCourseDao) applicationContext.getBean("chooseCourseDao");
		ClassTime classTime = chooseCourseDao.selectClassTime(1);
		System.out.println(classTime);
}
	@Test
	public void testr2w322() throws Exception {
		StudentChosDao studentChosDao = (StudentChosDao) applicationContext.getBean("studentChosDao");
		StudentChos studentChos = studentChosDao.isExist(1, 4);
		System.out.println(studentChos);
}
	@Test
	public void testr2wsad2() throws Exception {
		ChooseCourseDao chooseCourseDao = (ChooseCourseDao) applicationContext.getBean("chooseCourseDao");
		int i = chooseCourseDao.countSelect(13);
		
		System.out.println(i);
}
	@Test
	public void testr22322() throws Exception {
		StudentChosDao studentChosDao = (StudentChosDao) applicationContext.getBean("studentChosDao");
		List<ChooseCourse> list = studentChosDao.selectCname(1);
		for (ChooseCourse cc : list) {
			System.out.println(cc.getCourse().getName()+cc.getId());
		}
}
	
	@Test
	public void testr2s2() throws Exception {
		ChooseCourseDao chooseCourseDao = (ChooseCourseDao) applicationContext.getBean("chooseCourseDao");
		ChooseCourse cc = new ChooseCourse();
		Course course = new Course();
		cc.setCourse(course);
		PageHelper.startPage(2,5);
		List<ChooseCourse> list = chooseCourseDao.selectChooseCourse(cc);
		PageInfo<ChooseCourse> pf = new PageInfo<ChooseCourse>(list);
		System.out.println("页数"+pf.getPages());
		System.out.println("记录数"+pf.getTotal());
		System.out.println("每一页的数量"+pf.getPageSize());
		System.out.println("当前"+pf.getPageNum());
		for (ChooseCourse chooseCourse2 : pf.getList()) {
			System.out.println(chooseCourse2);
		}
		
}
}
