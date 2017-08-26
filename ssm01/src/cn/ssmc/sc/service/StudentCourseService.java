package cn.ssmc.sc.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.ssmc.entity.ChooseCourse;
import cn.ssmc.entity.Course;
import cn.ssmc.entity.PageBean;
import cn.ssmc.entity.StudentChos;

public interface StudentCourseService {

	// 传学生的id 看学生已经选了那些课
	// 传学生的id 看学生已经选了哪些课
	// 学生的idSESSION里面去，termflag 当前学期传2 ，
	List<StudentChos> selectWithChooseStudView(@Param("studId") Integer studId, @Param("termFlag") Integer termFlag);

	// 传选课的ID 查看有哪些学生选了该选课
	// 传选课的学期 2-当前学期哪些学生选了该选课
	// 传选课的学期1- 上学期学生的成绩
	List<StudentChos> selectWithChooseView(StudentChos studentChos);

	PageBean selectWCVPage(PageBean pb,StudentChos studentChos,int addScore);
	
	// 学生增加选课
	int insert(StudentChos record);

	// 添加学生选课记录
	// 先根据SEssion中学生的ID查到学生的信息，
	// 在根据url传过来的选课ID的值添加到表中
	int insert(int studID, int ccID);

	int insert(String studIDstring, String ccIDstring);

	// 删除学生选课
	int deleteByPrimaryKey(Integer id);

	int deleteByPrimaryKey(String idString);

	StudentChos isExist(int ccID, int stdID);

	StudentChos isExist(String ccID, String stdID);

	int selectStudNum(int studId);

	int selectStudNum(String studId);

	// 拿到cc的值
	StudentChos selectGetChooseByKey(int scID);

	StudentChos selectByKeyScore(int scID);

	int updateByPrimaryKeySelective(StudentChos record);

	// 批量修改成绩
	List<StudentChos> getIDScore(String idScore);

	StudentChos updateByList(String idScore);

	List<ChooseCourse> selectCname(int teacherID);
}
