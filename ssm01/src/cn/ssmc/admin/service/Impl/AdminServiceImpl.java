package cn.ssmc.admin.service.Impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ssmc.admin.service.AdminService;
import cn.ssmc.dao.AdminDao;
import cn.ssmc.entity.Admin;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminDao adminDao;

	// 登陆查询
	@Override
	public Admin loginAdmin(Admin admin) {

		Admin adminLogin = adminDao.loginAdmin(admin);

		return adminLogin;
	}

	// 查询个人信息
	@Override
	public Admin selectByPrimaryKey(Integer id) {

		Admin admin = adminDao.selectByPrimaryKey(id);

		return admin;
	}

	// 新写的登陆
	@Override
	public Admin loginAdmin(String no, String password) {

		Admin admin = new Admin();
		admin.setNo(no);
		admin.setPassword(password);

		Admin adminLogin = adminDao.loginAdmin(admin);

		return adminLogin;
	}

	//修改密码
	@Override
	public void updateByPrimaryKeySelective(String password ,Integer id) {
		
		Admin admin = new Admin();
		admin.setPassword(password);
		admin.setId(id);
		
		adminDao.updateByPrimaryKeySelective(admin);
	}

	@Override
	public void updateByPrimaryKeySelective(Integer id,String nickname, String birthday) {
		Admin admin = new Admin();
		admin.setNickname(nickname);
		admin.setId(id);
		//暂时不修改生日
		//admin.setBirthday(birthday);
		
		adminDao.updateByPrimaryKeySelective(admin);
	}
}
