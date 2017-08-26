package cn.ssmc.teacher.service;

import java.util.List;

import cn.ssmc.entity.Teacher;

public interface TeacherService {

	Teacher selectByPrimaryKey(Integer id);

	List<Teacher> selectTeacherByCollege(Integer college_id);

	List<Teacher> selectTeacherByCollege(String college_id);

	Teacher loginTeacher(String no, String password);

	// 修改密码
	void updateByPrimaryKeySelective(String password, Integer id);

	// 修改个人信息
	void updateByPrimaryKeySelective(Integer id, String nickname, String birthday);
}
