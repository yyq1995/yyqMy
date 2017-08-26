package cn.ssmc.admin.service;

import cn.ssmc.entity.Admin;

public interface AdminService {

	Admin loginAdmin(Admin admin);

	Admin selectByPrimaryKey(Integer id);

	// 登陆
	Admin loginAdmin(String no, String password);
	
	//密码修改
	void updateByPrimaryKeySelective(String password,Integer id);
	
	//修改个人信息
	void updateByPrimaryKeySelective(Integer id,String nickname,String birthday);
}
