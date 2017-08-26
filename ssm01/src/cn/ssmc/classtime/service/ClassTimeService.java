package cn.ssmc.classtime.service;

import java.util.List;

import cn.ssmc.entity.ClassTime;

public interface ClassTimeService {

	List<ClassTime> selectClassBySize(int classSize);
	List<ClassTime> selectClassBySize(String classSize);
    int updateByPrimaryKeySelective(ClassTime record);

}
