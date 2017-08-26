package cn.ssmc.course.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.ssmc.course.service.CourseService;
import cn.ssmc.dao.CourseDao;
import cn.ssmc.entity.Course;
import cn.ssmc.entity.PageBean;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseDao courseDao;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		int deleteId = courseDao.deleteByPrimaryKey(id);
		return deleteId;
	}

	@Override
	public int insert(Course course) {
		int insertID = courseDao.insert(course);
		return insertID;
	}

	@Override
	public int insertSelective(Course course) {
		int insertID = courseDao.insertSelective(course);
		return insertID;
	}

	@Override
	public Course selectByPrimaryKey(Integer id) {
		Course course = courseDao.selectByPrimaryKey(id);
		return course;
	}

	@Override
	public int updateByPrimaryKeySelective(Course course) {
		System.out.println("----update:-----" + course);
		int updateId = courseDao.updateByPrimaryKeySelective(course);
		System.out.println(updateId);

		return updateId;
	}

	@Override
	public int updateByPrimaryKey(Course course) {
		int updateId = courseDao.updateByPrimaryKey(course);
		return updateId;
	}

	@Override
	public List<Course> selectCourseWithCollege(Course course) {

		List<Course> list = courseDao.selectCourseWithCollege(course);

		return list;
	}

	@Override
	public Course selectByPrimaryKey(String idString) {
		Integer id = Integer.parseInt(idString);
		Course course = courseDao.selectByPrimaryKey(id);
		return course;
	}

	@Override
	public int deleteByPrimaryKey(String idString) {
		int id = Integer.parseInt(idString);
		int deleteId = deleteByPrimaryKey(id);
		return deleteId;
	}

	@Override
	public List<Course> selectCourseByCollegeId(String collegeId) {

		Integer id = Integer.parseInt(collegeId);
		List<Course> list = courseDao.selectCourseByCollegeId(id);

		return list;
	}

	@Override
	public PageBean selectCWCPage(Course course, PageBean pb) {
		if (pb.getMaxPage() != 0) {// 如果拿到最大值 执行添加操作
			pb.setcPage(pb.getMaxPage());
			System.out.println("拿到了最大值，执行添加的操作");
			// 如果记录数整除页数 说明再添加的要多一页 所以在当前页上加一页
			if (pb.getMaxSize() != 0 && (pb.getMaxSize() % pb.getPageSize()) == 0) {

				pb.setcPage(pb.getMaxPage() + 1);
			}
		}
		// 删除如果记录数整除页数余1 说明在在删除 当前页要减一页
		if (pb.getMaxSize() != 0 && (pb.getMaxSize() % pb.getPageSize()) == 1) {

			pb.setcPage(pb.getcPage() - 1);
		}

		
		PageHelper.startPage(pb.getcPage(), pb.getPageSize());
		List<Course> list = selectCourseWithCollege(course);
		PageInfo<Course> pf = new PageInfo<Course>(list);
		pb.setMaxSize(pf.getTotal());
		pb.setMaxPage(pf.getPages());
		// 如果总pAge为0，显示第一页
		if (pf.getPages() == 0) {
			pb.setMaxPage(1);
		} else {
			// 否则显示总page为总page
			pb.setMaxPage(pf.getPages());
		}
		if(pf.getPages() < pb.getcPage()){
			pb.setcPage(pf.getPages());
		}
		pb.setList(list);
		return pb;
	}

	@Override
	public int selectCountByChoos(int id) {
		// TODO Auto-generated method stub
		return courseDao.selectCountByChoos(id);
	}

}
