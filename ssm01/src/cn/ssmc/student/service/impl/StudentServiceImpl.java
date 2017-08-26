package cn.ssmc.student.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ssmc.dao.StudentDao;
import cn.ssmc.entity.Student;
import cn.ssmc.student.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {
	@Autowired
	private StudentDao studentDao;

	// 登陆
	@Override
	public Student loginStudent(String no, String password) {

		Student student = new Student();
		student.setNo(no);
		student.setPassword(password);

		Student studentLogin = studentDao.loginStudent(student);

		return studentLogin;
	}

	@Override
	public Student selectByPrimaryKey(Integer id) {
		return studentDao.selectByPrimaryKey(id);
	}


	// 修改学生密码
	@Override
	public void updateStudentByPassword(String newPwd, Integer id) {
		Student student = new Student();
		student.setPassword(newPwd);
		student.setId(id);
		studentDao.updateByPrimaryKeySelective(student);
	}

	@Override
	public void updateByPrimaryKeySelective(Integer id, String nickname, String birthday) {
		Student student = new Student();
		student.setNickname(nickname);
		student.setId(id);
		// 暂时不修改生日
		// student.setBirthday(birthday);

		studentDao.updateByPrimaryKeySelective(student);
	}
}
