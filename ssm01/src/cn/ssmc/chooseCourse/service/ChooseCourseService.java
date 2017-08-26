package cn.ssmc.chooseCourse.service;

import java.util.List;

import cn.ssmc.entity.ChooseCourse;
import cn.ssmc.entity.ClassTime;
import cn.ssmc.entity.PageBean;

public interface ChooseCourseService {

	List<ChooseCourse> selectChooseCourse(ChooseCourse chooseCourse);

	PageBean selectCCPage(ChooseCourse chooseCourse,PageBean pb);
	
	ClassTime selectClassTime(Integer ccId);

	ClassTime selectClassTime(String ccIdString);

	int deleteByPrimaryKey(Integer id);

	int deleteByPrimaryKey(String idString);

	void insert(ChooseCourse record);

	// 点击删除时要先查看是否还有学生选课
	int countSelect(int ccID);

	int countSelect(String ccIDstring);

	// 跳转修改页面的时候要查询
	ChooseCourse selectByPrimaryKey(Integer id);

	// 修改
	void updateByPrimaryKey(ChooseCourse record);

}
