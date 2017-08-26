package cn.ssmc.classtime.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ssmc.classtime.service.ClassTimeService;
import cn.ssmc.dao.ClassTimeDao;
import cn.ssmc.entity.ClassTime;
@Service
public class ClassTimeServiceImpl implements ClassTimeService{

	@Autowired
	private ClassTimeDao ClassTimeDao;
	
	
	
	@Override
	public List<ClassTime> selectClassBySize(int classSize) {
		
		return ClassTimeDao.selectClassBySize(classSize);
	}
	@Override
	public List<ClassTime> selectClassBySize(String classSize) {
		
		//页面应该规范一下，不能输入小于0或大于某个值
		int size = 0;
		int cs = Integer.parseInt(classSize);
		if(cs >= 0 && cs <= 40){
			size = 0;
		}else if(cs>40){
			size = 1;
		}
		List<ClassTime> list = selectClassBySize(size);
		
		return list;
	}

	@Override
	public int updateByPrimaryKeySelective(ClassTime record) {
		// TODO Auto-generated method stub
		return ClassTimeDao.updateByPrimaryKeySelective(record);
	}
	

}
