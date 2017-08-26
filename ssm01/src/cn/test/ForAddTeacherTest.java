package cn.test;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.ssmc.dao.TeacherDao;
import cn.ssmc.entity.College;
import cn.ssmc.entity.Teacher;

public class ForAddTeacherTest {
	private ApplicationContext applicationContext;

	@Before
	public void setUp() throws Exception {
		applicationContext = new ClassPathXmlApplicationContext("classpath:spring-common.xml");
	}
	
	//测试用添加老师信息
		@Test
		public void test3332() throws Exception {
				TeacherDao teacherDao= (TeacherDao) applicationContext.getBean("teacherDao");
				for(int i=0;i<1;i++){
					Teacher teacher = new Teacher();
					teacher.setName("83老师"+i);
					teacher.setAddress("浙江省-温州市-瓯海区");
					teacher.setNickname("瞄瞄就是喵喵");
					teacher.setNo("AB1017"+i);
					teacher.setPassword("1234");
					teacher.setSex(1);
					College college=new College();
					college.setId(6);
					teacher.setCollege(college);
					teacherDao.insert(teacher);
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
