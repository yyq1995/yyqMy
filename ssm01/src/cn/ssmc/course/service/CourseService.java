package cn.ssmc.course.service;

import java.util.List;

import cn.ssmc.entity.Course;
import cn.ssmc.entity.PageBean;

public interface CourseService {

	int deleteByPrimaryKey(Integer id);

	int deleteByPrimaryKey(String idString);

	int insert(Course record);

	int insertSelective(Course record);

	Course selectByPrimaryKey(Integer id);

	Course selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(Course record);

	int updateByPrimaryKey(Course record);

	List<Course> selectCourseWithCollege(Course course);

	PageBean selectCWCPage(Course course,PageBean pb);
	
	List<Course> selectCourseByCollegeId(String collegeId);

	int selectCountByChoos(int id);
}
