package cn.ssmc.chooseCourse.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.ssmc.chooseCourse.service.ChooseCourseService;
import cn.ssmc.dao.ChooseCourseDao;
import cn.ssmc.dao.ClassTimeDao;
import cn.ssmc.dao.TermsDao;
import cn.ssmc.entity.ChooseCourse;
import cn.ssmc.entity.ClassTime;
import cn.ssmc.entity.PageBean;
import cn.ssmc.entity.Terms;

@Service
public class ChooseCourseServiceImpl implements ChooseCourseService {

	@Autowired
	private ChooseCourseDao chooseCourseDao;

	@Autowired
	private ClassTimeDao classTimeDao;

	@Autowired
	private TermsDao termsDao;

	@Override
	public List<ChooseCourse> selectChooseCourse(ChooseCourse chooseCourse) {

		List<ChooseCourse> list = chooseCourseDao.selectChooseCourse(chooseCourse);

		return list;
	}

	@Override
	public ClassTime selectClassTime(Integer classTimeId) {

		ClassTime classTime = chooseCourseDao.selectClassTime(classTimeId);

		return classTime;
	}

	@Override
	public ClassTime selectClassTime(String ccIdString) {
		Integer ccId = Integer.parseInt(ccIdString);
		return selectClassTime(ccId);
	}

	// 删除后要把教室变成空的
	@Override
	public int deleteByPrimaryKey(Integer id) {
		ChooseCourse chooseCourse = chooseCourseDao.selectByPrimaryKey(id);

		chooseCourseDao.deleteByPrimaryKey(id);
		ClassTime classTime = chooseCourse.getClassTime();
		classTime.setFlag(0);
		classTimeDao.updateByPrimaryKeySelective(classTime);
		return 0;
	}

	@Override
	public int deleteByPrimaryKey(String idString) {
		Integer id = Integer.parseInt(idString);
		return deleteByPrimaryKey(id);
	}

	@Override
	public void insert(ChooseCourse record) {

		Terms terms = termsDao.selectNow();
		record.setTerms(terms);
		System.out.println(record.getCourse().getName());
		System.out.println(record);

		// 教室被用了
		ClassTime ct = record.getClassTime();
		ct.setFlag(1);

		classTimeDao.updateByPrimaryKeySelective(ct);

		record.setSelected(0);
		record.setFlag(0);

		chooseCourseDao.insert(record);
	}

	@Override
	public int countSelect(int ccID) {

		return chooseCourseDao.countSelect(ccID);
	}

	@Override
	public int countSelect(String ccIDstring) {
		int ccId = Integer.parseInt(ccIDstring);
		return countSelect(ccId);
	}

	// 跳转修改页面的同时要把信息查出来
	@Override
	public ChooseCourse selectByPrimaryKey(Integer id) {
		ChooseCourse chooseCourse = chooseCourseDao.selectByPrimaryKey(id);
		return chooseCourse;
	}

	@Override
	public void updateByPrimaryKey(ChooseCourse record) {

		Terms terms = termsDao.selectNow();
		record.setTerms(terms);

		// 教室被用了
		ClassTime ct = record.getClassTime();
		ct.setFlag(1);
		classTimeDao.updateByPrimaryKeySelective(ct);
		record.setFlag(0);

		chooseCourseDao.updateByPrimaryKey(record);
	}

	@Override
	public PageBean selectCCPage(ChooseCourse chooseCourse, PageBean pb) {
		
		if (pb.getMaxPage() != 0) {//如果拿到最大值 执行添加操作
			pb.setcPage(pb.getMaxPage());
			System.out.println("拿到了最大值，执行添加的操作");
			//如果记录数整除页数 说明再添加的要多一页 所以在当前页上加一页
			if(pb.getMaxSize()!=0 && (pb.getMaxSize()%pb.getPageSize())==0){
				
				pb.setcPage(pb.getMaxPage()+1);
			}
		}
		//删除如果记录数整除页数余1 说明在在删除 当前页要减一页
		if(pb.getMaxSize()!=0 && (pb.getMaxSize()%pb.getPageSize())==1){
			
			pb.setcPage(pb.getcPage()-1);
		}
		
		PageHelper.startPage(pb.getcPage(), pb.getPageSize());
		List<ChooseCourse> list = selectChooseCourse(chooseCourse);
		PageInfo<ChooseCourse> pf = new PageInfo<ChooseCourse>(list);
		pb.setMaxSize(pf.getTotal());
		
		//如果总pAge为0，显示第一页
		if (pf.getPages() == 0) {
			pb.setMaxPage(1);
		} else {
			//否则显示总page为总page
			pb.setMaxPage(pf.getPages());
		}
		
		if(pf.getPages() < pb.getcPage()){
			pb.setcPage(pf.getPages());
		}
		pb.setList(list);
		return pb;
	}

}
