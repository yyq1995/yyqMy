package cn.ssmc.student.service;

import cn.ssmc.entity.Student;

public interface StudentService {
	// 通过查询学生的用户名与密码判断是否登录成功
	Student loginStudent(String no, String password);

	Student selectByPrimaryKey(Integer id);

	void updateStudentByPassword(String newPwd, Integer id);

	// 修改个人信息
	void updateByPrimaryKeySelective(Integer id, String nickname, String birthday);

}
