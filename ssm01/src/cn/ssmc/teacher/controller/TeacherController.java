package cn.ssmc.teacher.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;

import cn.ssmc.entity.Course;
import cn.ssmc.entity.Teacher;
import cn.ssmc.teacher.service.TeacherService;
import cn.ssmc.util.AddressDateUtil;
import cn.ssmc.util.MD5Password;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

	@Autowired
	private TeacherService teacherService;
	@RequestMapping("teacherListAjax.htm")
	public void getTeacherListAjax(HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		String collegeId = request.getParameter("collegeId");
		
		List<Teacher> teacherList = teacherService.selectTeacherByCollege(collegeId);
		
		Gson gson = new Gson();
		String json = gson.toJson(teacherList);
		
		System.out.println("json:" + json);
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json;charset=utf-8");
		
		response.getWriter().print(json);
		
	}
	@RequestMapping("teacherInfo.htm")
	public String teacherInfo(HttpServletRequest request){
		Teacher teacher = (Teacher) request.getSession().getAttribute("teacherLogin");
		
		Date birthday= teacher.getBirthday();
		String address = teacher.getAddress();
		
		//request.setAttribute("teacher", teacher);
		//现在单独往页面放生日,省市区
		request.setAttribute("birthday", AddressDateUtil.getBirthday(birthday));
		
		return "teacher/teacherInfo";
	}
	
		//进入修改密码的页面
		@RequestMapping("teacherChangePwdPage.htm")
		public String teacherChangePwdPage(){
			return "teacher/teacherChangePwd";
		}
		
		//修改密码
		@RequestMapping("teacherChangePwd.htm")
		public String teacherChangePwd(HttpServletRequest request,@RequestParam("oldPwd") String oldPwd,@RequestParam("newPwd") String newPwd,@RequestParam("rePwd") String rePwd){
			
			//先要验证旧密码是否输对了
			Teacher teacherLogin = (Teacher) request.getSession().getAttribute("teacherLogin");
			
			String oldPwdMD5 = MD5Password.md5(oldPwd,teacherLogin.getNo());
			
			String newPwdMD5 = MD5Password.md5(newPwd,teacherLogin.getNo());
			
			if(oldPwd==null||"".equals(oldPwd)){
				request.setAttribute("error1", "不能为空");
				return "teacher/teacherChangePwd";
			}
			
			if(newPwd==null||"".equals(newPwd)){
				request.setAttribute("error2", "不能为空");
				return "teacher/teacherChangePwd";
			}
			
			if(rePwd==null||"".equals(rePwd)){
				request.setAttribute("error3", "不能为空");
				return "teacher/teacherChangePwd";
			}
			
			if(teacherLogin.getPassword().equals(oldPwdMD5)){
				if(newPwd.equals(rePwd)){
					teacherService.updateByPrimaryKeySelective(newPwdMD5, teacherLogin.getId());
					request.setAttribute("out",1);
					request.setAttribute("role", teacherLogin);
					request.getSession().removeAttribute("teacherLogin");
					return "tips/success";
				}else{
					request.setAttribute("error3", "确认密码不一致");
					return "teacher/teacherChangePwd";
				}
			}else{
				request.setAttribute("error3", "旧密码不正确");
				return "teacher/teacherChangePwd";
			}
		}
		
		//修改个人信息
		@RequestMapping("updateTeacherInfo.htm")
		public String updateTeacherInfo(HttpServletRequest request,@RequestParam("nickname") String nickname,@RequestParam("birthday") String birthday){
			
			Teacher teacherLogin = (Teacher) request.getSession().getAttribute("teacherLogin");
			
			teacherService.updateByPrimaryKeySelective(teacherLogin.getId(),nickname, birthday);
			
			return "tips/success";
		}
	
}
