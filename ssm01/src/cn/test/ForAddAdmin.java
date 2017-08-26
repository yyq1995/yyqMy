package cn.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.ssmc.dao.AdminDao;
import cn.ssmc.dao.StudentDao;
import cn.ssmc.dao.TeacherDao;
import cn.ssmc.entity.Admin;
import cn.ssmc.entity.College;
import cn.ssmc.entity.Major;
import cn.ssmc.entity.Student;
import cn.ssmc.entity.Teacher;
import cn.ssmc.util.MD5Password;

public class ForAddAdmin {
	private ApplicationContext applicationContext;

	@Before
	public void setUp() throws Exception {
		applicationContext = new ClassPathXmlApplicationContext("classpath:spring-common.xml");
	}
	
	//测试用添加管理员信息
		@Test
		public void test3332() throws Exception {
				AdminDao adminDao= (AdminDao) applicationContext.getBean("adminDao");
				for(int i=0;i<2;i++){
					Admin admin = new Admin();
					admin.setName("汪汪"+i);
					admin.setNo("1234"+i);
					String pwd = MD5Password.md5("1234"+i, "1234"+i);
					admin.setPassword(pwd);
					admin.setSex(1);
					
					admin.setAddress("北京");
					adminDao.insert(admin);
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
