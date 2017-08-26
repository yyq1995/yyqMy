package cn.ssmc.college.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ssmc.college.service.CollegeService;
import cn.ssmc.dao.CollegeDao;
import cn.ssmc.entity.College;
@Service
public class CollegeServiceImpl implements CollegeService{

	@Autowired
	private CollegeDao collegeDao;
	
	@Override
	public List<College> selectCollegeList() {
		return collegeDao.selectCollegeList();
	}

}
