package cn.ssmc.admin.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.ssmc.admin.service.AdminService;
import cn.ssmc.entity.Admin;
import cn.ssmc.util.AddressDateUtil;
import cn.ssmc.util.MD5Password;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	//管理员查看个人信息
	@RequestMapping("adminInfo.htm")
	public String adminInfo(HttpServletRequest request){
		
		Admin admin = (Admin) request.getSession().getAttribute("adminLogin");
		
		Date birthday= admin.getBirthday();
		String address = admin.getAddress();
		
		//request.setAttribute("admin", admin);
		//现在单独往页面放生日,省市区
		request.setAttribute("birthday", AddressDateUtil.getBirthday(birthday));
		
		return "admin/adminInfo";
	}
	
	//管理员进入修改密码的页面
	@RequestMapping("adminChangePwdPage.htm")
	public String adminChangePwdPage(){
		return "admin/adminChangePwd";
	}
	
	//修改密码
	@RequestMapping("adminChangePwd.htm")
	public String adminChangePwd(HttpServletRequest request,@RequestParam("oldPwd") String oldPwd,@RequestParam("newPwd") String newPwd,@RequestParam("rePwd") String rePwd){
		
		//先要验证旧密码是否输对了
		Admin adminLogin = (Admin) request.getSession().getAttribute("adminLogin");
		
		String oldPwdMD5 = MD5Password.md5(oldPwd,adminLogin.getNo());
		
		String newPwdMD5 = MD5Password.md5(newPwd,adminLogin.getNo());
		
		if(oldPwd==null||"".equals(oldPwd)){
			request.setAttribute("error1", "不能为空");
			return "admin/adminChangePwd";
		}
		
		if(newPwd==null||"".equals(newPwd)){
			request.setAttribute("error2", "不能为空");
			return "admin/adminChangePwd";
		}
		
		if(rePwd==null||"".equals(rePwd)){
			request.setAttribute("error3", "不能为空");
			return "admin/adminChangePwd";
		}
		
		if(adminLogin.getPassword().equals(oldPwdMD5)){
			if(newPwd.equals(rePwd)){
				adminService.updateByPrimaryKeySelective(newPwdMD5, adminLogin.getId());
				request.setAttribute("out",1);
				request.setAttribute("role", adminLogin);
				request.getSession().removeAttribute("adminLogin");
				return "tips/success";
			}else{
				request.setAttribute("error3", "确认密码不一致");
				return "admin/adminChangePwd";
			}
		}else{
			request.setAttribute("error3", "旧密码不正确");
			return "admin/adminChangePwd";
		}
	}
	
	//修改个人信息
	@RequestMapping("updateAdminInfo.htm")
	public String updateAdminInfo(HttpServletRequest request,@RequestParam("nickname") String nickname,@RequestParam("birthday") String birthday){
		
		Admin adminLogin = (Admin) request.getSession().getAttribute("adminLogin");
		
		adminService.updateByPrimaryKeySelective(adminLogin.getId(),nickname, birthday);
		
		return "tips/success";
	}
}
