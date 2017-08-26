package cn.ssmc.sc.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.ssmc.dao.ChooseCourseDao;
import cn.ssmc.dao.StudentChosDao;
import cn.ssmc.dao.StudentDao;
import cn.ssmc.entity.ChooseCourse;
import cn.ssmc.entity.Course;
import cn.ssmc.entity.PageBean;
import cn.ssmc.entity.Student;
import cn.ssmc.entity.StudentChos;
import cn.ssmc.sc.service.StudentCourseService;

@Service
public class StudentCourseServiceImpl implements StudentCourseService {

	@Autowired
	private StudentChosDao studentChosDao;
	@Autowired
	private StudentDao studentDao;
	@Autowired
	private ChooseCourseDao chooseCourseDao;

	// 添加学生选课记录
	// 先根据SEssion中学生的ID查到学生的信息，
	// 在根据url传过来的选课ID的值添加到表中
	@Override
	public int insert(StudentChos studentChos) {
		return studentChosDao.insert(studentChos);
	}

	// 添加学生选课记录
	// 先根据SEssion中学生的ID查到学生的信息，
	// 在根据url传过来的选课ID的值添加到表中
	public int insert(int studID, int ccID) {
		Student student = studentDao.selectByPrimaryKey(studID);

		ChooseCourse chooseCourse = new ChooseCourse();
		chooseCourse.setId(ccID);

		StudentChos studentChos = new StudentChos();
		studentChos.setStudent(student);
		studentChos.setChooseCourse(chooseCourse);
		int insertID = studentChosDao.insert(studentChos);
		chooseCourseDao.updateSelected(ccID);
		return insertID;
	}

	public int insert(String studIDstring, String ccIDstring) {
		Integer studId = Integer.parseInt(studIDstring);
		Integer ccId = Integer.parseInt(ccIDstring);

		return insert(studId, ccId);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		StudentChos sc = studentChosDao.selectGetChooseByKey(id);
		chooseCourseDao.updateSelected2(sc.getChooseCourse().getId());
		return studentChosDao.deleteByPrimaryKey(id);
	}

	@Override
	public int deleteByPrimaryKey(String idString) {
		int id = Integer.parseInt(idString);
		return deleteByPrimaryKey(id);
	}

	@Override
	public StudentChos isExist(int ccID, int stdID) {

		return studentChosDao.isExist(ccID, stdID);
	}

	@Override
	public StudentChos isExist(String ccIDString, String stdIDString) {
		Integer ccID = Integer.parseInt(ccIDString);
		Integer stdID = Integer.parseInt(stdIDString);
		return isExist(ccID, stdID);
	}

	@Override
	public int selectStudNum(int studId) {
		// TODO Auto-generated method stub
		return studentChosDao.selectStudNum(studId);
	}

	@Override
	public int selectStudNum(String stringStudId) {
		int studId = Integer.parseInt(stringStudId);
		return selectStudNum(studId);
	}

	@Override
	public StudentChos selectGetChooseByKey(int scID) {
		// TODO Auto-generated method stub
		return studentChosDao.selectGetChooseByKey(scID);
	}

	@Override
	public List<StudentChos> selectWithChooseStudView(Integer studId, Integer termFlag) {
		// TODO Auto-generated method stub
		return studentChosDao.selectWithChooseStudView(studId, termFlag);
	}

	@Override
	public List<StudentChos> selectWithChooseView(StudentChos studentChos) {

		return studentChosDao.selectWithChooseView(studentChos);
	}

	@Override
	public StudentChos selectByKeyScore(int scID) {
		// TODO Auto-generated method stub
		return studentChosDao.selectByKeyScore(scID);
	}

	@Override
	public int updateByPrimaryKeySelective(StudentChos record) {
		// TODO Auto-generated method stub
		return studentChosDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<ChooseCourse> selectCname(int teacherID) {
		// TODO Auto-generated method stub
		return studentChosDao.selectCname(teacherID);
	}

	@Override
	public List<StudentChos> getIDScore(String idScore) {
		System.out.println("idScore" + idScore);
		List<StudentChos> list = new ArrayList<StudentChos>();
		String[] oneObjs = idScore.split(",");
		for (int i = 0; i < oneObjs.length; i++) {
			String[] oneObj = oneObjs[i].split("-");
			Integer id = Integer.parseInt(oneObj[0]);
			Integer score = Integer.parseInt(oneObj[1]);
			StudentChos sc = new StudentChos();
			sc.setId(id);
			sc.setScore(score);
			list.add(sc);
		}
		return list;
	}

	@Override
	public StudentChos updateByList(String idScore) {
		List<StudentChos> list = getIDScore(idScore);
		StudentChos sc1 = selectGetChooseByKey(list.get(0).getId());
		for (StudentChos sc : list) {
			updateByPrimaryKeySelective(sc);
		}
		return sc1;
	}

	@Override
	public PageBean selectWCVPage(PageBean pb, StudentChos studentChos, int addScore) {
		// 删除如果记录数整除页数余1 说明在在删除 当前页要减一页
		List<StudentChos> list = null;
		if (addScore == 1) {
			list = selectWithChooseView(studentChos);
		} else {
			if (pb.getMaxSize() != 0 && (pb.getMaxSize() % pb.getPageSize()) == 1) {
				pb.setcPage(pb.getcPage() - 1);
			}
			PageHelper.startPage(pb.getcPage(), pb.getPageSize());
			list = selectWithChooseView(studentChos);
			PageInfo<StudentChos> pf = new PageInfo<StudentChos>(list);
			pb.setMaxSize(pf.getTotal());
			// 如果总pAge为0，显示第一页
			if (pf.getPages() == 0) {
				pb.setMaxPage(1);
			} else {
				// 否则显示总page为总page
				pb.setMaxPage(pf.getPages());
			}
		}
		pb.setList(list);
		return pb;
	}

}
