package cn.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.ssmc.dao.StudentDao;
import cn.ssmc.dao.TeacherDao;
import cn.ssmc.entity.College;
import cn.ssmc.entity.Major;
import cn.ssmc.entity.Student;
import cn.ssmc.entity.Teacher;
import cn.ssmc.util.MD5Password;

public class ForAddStudent {
	private ApplicationContext applicationContext;

	@Before
	public void setUp() throws Exception {
		applicationContext = new ClassPathXmlApplicationContext("classpath:spring-common.xml");
	}
	
	//测试用添加老师信息
		@Test
		public void test3332() throws Exception {
				StudentDao studentDao= (StudentDao) applicationContext.getBean("studentDao");
				for(int i=0;i<2;i++){
					Student  student = new Student();
					College college  = new College();
					college.setId(1);//1理学院 5 艺术学院
					college.setName("理学院");
					Major major =  new Major();
					major.setId(1);//1应用统计1班 2服装设计1班
					major.setName("应用统计1班");
					student.setCollege(college);
					student.setMajor(major);
					student.setName("汪汪1"+i);
					student.setNo("111"+i);
					String pwd = MD5Password.md5("111"+i, "111"+i);
					student.setPassword(pwd);
					student.setSex(1);
					student.setAddress("北京");
					studentDao.insert(student);
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
}
