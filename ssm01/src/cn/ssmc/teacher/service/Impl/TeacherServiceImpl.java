package cn.ssmc.teacher.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ssmc.dao.TeacherDao;
import cn.ssmc.entity.Teacher;
import cn.ssmc.teacher.service.TeacherService;

@Service
public class TeacherServiceImpl implements TeacherService{

	@Autowired
	private TeacherDao teacherDao;
	@Override
	public Teacher selectByPrimaryKey(Integer id) {
		
		return teacherDao.selectByPrimaryKey(id);
	}

	@Override
	public List<Teacher> selectTeacherByCollege(Integer college_id) {
		// TODO Auto-generated method stub
		return teacherDao.selectTeacherByCollege(college_id);
	}
	@Override
	public List<Teacher> selectTeacherByCollege(String college_id) {

		Integer collegeId = Integer.parseInt(college_id);
		List<Teacher> list = teacherDao.selectTeacherByCollege(collegeId);
		
		return list;
	}
	@Override
	public Teacher loginTeacher(String no, String password) {

		Teacher teacher = new Teacher();
		
		teacher.setNo(no);
		teacher.setPassword(password);
		
		Teacher teacherLogin = teacherDao.loginTeacher(teacher);
		
		return teacherLogin;
	}
	@Override
	public void updateByPrimaryKeySelective(String password, Integer id) {
		Teacher teacher = new Teacher();
		teacher.setPassword(password);
		teacher.setId(id);
		
		teacherDao.updateByPrimaryKeySelective(teacher);
	}

	@Override
	public void updateByPrimaryKeySelective(Integer id, String nickname, String birthday) {
		Teacher teacher = new Teacher();
		teacher.setNickname(nickname);
		teacher.setId(id);
		//暂时不修改生日
		//teacher.setBirthday(birthday);
		
		teacherDao.updateByPrimaryKeySelective(teacher);
	}


}
