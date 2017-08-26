package cn.ssmc.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.ssmc.admin.service.AdminService;
import cn.ssmc.entity.Admin;
import cn.ssmc.entity.Student;
import cn.ssmc.entity.Teacher;
import cn.ssmc.student.service.StudentService;
import cn.ssmc.teacher.service.TeacherService;
import cn.ssmc.util.ImgUtil;
import cn.ssmc.util.MD5Password;

@Controller
public class FrameController {
	
	@Autowired
	private AdminService adminService;
	@Autowired
	private StudentService studentService;
	@Autowired
	private TeacherService teacherService;
	
	@RequestMapping("/loginPage.htm")
	public String loginPage(HttpServletRequest request){
		return "login";
	}
	
	//登陆
	@RequestMapping("/login.htm")
	public String login(@RequestParam("no") String no,
						@RequestParam("password") String password,
						@RequestParam("code") String code,
						@RequestParam("radio") int radio,
						HttpServletRequest request){
		
		
		if(no==null||"".equals(no)||password==null||"".equals(password)){
			request.setAttribute("error", "账号或密码不能为空");
			return "login";
		}
		
		//登陆验证是否为空
		String c = (String) request.getSession().getAttribute("code");
		
		System.out.println("c:" + c + " ; code:" + code);
		
		if(c!=null){
			if(!c.equals(code)){
				request.setAttribute("error", "验证码错误");
				return "login";
			}
		}
		
		String pwdMD5 = MD5Password.md5(password,no);
		
		if (radio==1) {
			Student studentLogin = studentService.loginStudent(no, pwdMD5);
			System.out.println("我是学生");
			if(studentLogin!=null){
				//在request中放昵称，用于在主页显示欢迎
				request.setAttribute("studentNickname", studentLogin.getNickname());
				
				request.getSession().setAttribute("studentLogin", studentLogin);
				return "student/studentMain";
			}else{
				request.setAttribute("error", "用户名或密码错误");
				return "login";
			}
		}else if (radio==2){
			Teacher teacherLogin = teacherService.loginTeacher(no, pwdMD5);
			System.out.println("我是老师");
			if(teacherLogin!=null){
				//在request中放昵称，用于在主页显示欢迎
				request.setAttribute("teacherNickname", teacherLogin.getNickname());
				
				request.getSession().setAttribute("teacherLogin", teacherLogin);
				return "teacher/teacherMain";
			}else{
				request.setAttribute("error", "用户名或密码错误");
				return "login";
			}
		}else if (radio==3){
			Admin adminLogin = adminService.loginAdmin(no, pwdMD5);
			System.out.println("我是管理员");
			if(adminLogin!=null){
				//在request中放昵称，用于在主页显示欢迎
				request.setAttribute("adminNickname", adminLogin.getNickname());
				
				request.getSession().setAttribute("adminLogin", adminLogin);
				return "admin/adminMain";
			}else{
				request.setAttribute("error", "用户名或密码错误");
				return "login";
			}
		}
		request.setAttribute("error", "发生未知错误请重新登陆");
		return "login";
	}
	
	//推出
	@RequestMapping("out.htm")
	public String out(HttpServletRequest request,@RequestParam("role") String role){
		//System.out.println(request.getSession().getAttribute("adminLogin"));
		if("admin".equals(role)){
			request.getSession().removeAttribute("adminLogin");
		}else if("student".equals(role)){
			request.getSession().removeAttribute("studentLogin");
		}else if("teacher".equals(role)){
			request.getSession().removeAttribute("teacherLogin");
		}
		//System.out.println("--------"+request.getSession().getAttribute("adminLogin"));
		return "redirect:loginPage.htm";
	}
	
	//验证码
	@RequestMapping("/img.htm")
	public void imgCode(HttpServletRequest request,HttpServletResponse response) throws IOException{
		 //1设置输出格式
		response.setContentType("image/jpeg");
	     //设置响应头信息，不缓存此图片
		response.setHeader("Param", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", -1);//立即过期
	    
		Map<String, Object> map = ImgUtil.getImg();
		
		String code = (String) map.get("code");
		
		BufferedImage img = (BufferedImage) map.get("img");
		
		request.getSession().setAttribute("code",code);
		//输出图片
		ImageIO.write(img, "JPEG", response.getOutputStream());
	}
	
	@RequestMapping("/main.htm")
	public String mainPage(){
		return "mainPage";
	}
	
	@RequestMapping("/404.htm")
	public String errorPage(){
		return "tips/404";
	}
}
