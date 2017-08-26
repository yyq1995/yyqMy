package cn.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.ssmc.dao.ChooseCourseDao;
import cn.ssmc.dao.StudentChosDao;
import cn.ssmc.dao.StudentDao;
import cn.ssmc.dao.TeacherDao;
import cn.ssmc.entity.ChooseCourse;
import cn.ssmc.entity.College;
import cn.ssmc.entity.Major;
import cn.ssmc.entity.Student;
import cn.ssmc.entity.StudentChos;
import cn.ssmc.entity.Teacher;
import cn.ssmc.entity.Terms;

public class ForAddStudentChoos {
	private ApplicationContext applicationContext;

	@Before
	public void setUp() throws Exception {
		applicationContext = new ClassPathXmlApplicationContext("classpath:spring-common.xml");
	}
	
	//测试用添加xueshengxuanke 
		@Test
		public void test3332() throws Exception {
			StudentChosDao  studentChosDao = (StudentChosDao) applicationContext.getBean("studentChosDao");
			StudentDao studentDao= (StudentDao) applicationContext.getBean("studentDao");
			for (int i = 62; i <65 ; i++) {
				
				Student student = studentDao.selectByPrimaryKey(i);
				ChooseCourse chooseCourse = new ChooseCourse();
				chooseCourse.setId(1);
				
				StudentChos sc = new StudentChos();
				sc.setStudent(student);
				sc.setChooseCourse(chooseCourse);
				studentChosDao.insert(sc);
			}
		
		}
		@Test
		public void test33322() throws Exception {
				StudentDao studentDao= (StudentDao) applicationContext.getBean("studentDao");
				for(int i=22;i<61;i++){
					studentDao.deleteByPrimaryKey(i);
				}
		}
		@Test
		public void test333222() throws Exception {
				StudentDao studentDao= (StudentDao) applicationContext.getBean("studentDao");
				Student student = studentDao.selectByPrimaryKey(62);
				System.out.println(student.getCollege().getName());
		}
		@Test
		public void test33332() throws Exception {
				TeacherDao teacherDao= (TeacherDao) applicationContext.getBean("teacherDao");
				Teacher teacher =  teacherDao.selectByPrimaryKey(1);
				System.out.println(teacher);
				System.out.println(teacher.getCollege().getName());
		}
		@Test
		public void test2233332() throws Exception {
				TeacherDao teacherDao= (TeacherDao) applicationContext.getBean("teacherDao");
				List<Teacher> list = teacherDao.selectTeacherByCollege(1);
				for (Teacher teacher : list) {
					System.out.println(teacher);
				}
		}
		
		
		@Test
		public void test323() throws Exception {
			StudentChosDao  studentChosDao = (StudentChosDao) applicationContext.getBean("studentChosDao");
			List<StudentChos> list = studentChosDao.selectWithChooseStudView(66,1);
			for (StudentChos studentChos : list) {
				System.out.println(studentChos);
				System.out.println(studentChos.getChooseCourse().getCollege().getName());
				System.out.println(studentChos.getChooseCourse().getCourse().getName());
				System.out.println(studentChos.getChooseCourse().getTerms().getFlag());

			}
		}
		@Test
		public void test233() throws Exception {
			StudentChosDao  studentChosDao = (StudentChosDao) applicationContext.getBean("studentChosDao");
			StudentChos sc = new StudentChos();
			
			ChooseCourse chooseCourse = new ChooseCourse();
			Terms term = new Terms();
			term.setFlag(2);
			chooseCourse.setId(1);;
			chooseCourse.setTerms(term);
			Student student =  new Student();
			student.setName("1");;
			sc.setStudent(student);
			sc.setChooseCourse(chooseCourse);
			List<StudentChos> list = studentChosDao.selectWithChooseView(sc);
			for (StudentChos studentChos : list) {
				System.out.println(studentChos.getStudent().getName());
			}
		}
}
