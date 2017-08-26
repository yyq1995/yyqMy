package cn.ssmc.student.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.ssmc.entity.Student;
import cn.ssmc.student.service.StudentService;
import cn.ssmc.util.AddressDateUtil;
import cn.ssmc.util.MD5Password;

@Controller
@RequestMapping("/student")
public class StudentController {
	@Autowired
	private StudentService studentService;

	@RequestMapping("studentInfo.htm")
	public String studentInfo(HttpServletRequest request){
		//通过id查询学生的个人信息
		Student student = (Student) request.getSession().getAttribute("studentLogin");
		System.out.println(student);
		Date birthday= student.getBirthday();
		String address = student.getAddress();
		
		//request.setAttribute("student", student);
		//现在单独往页面放生日,省市区
		request.setAttribute("birthday", AddressDateUtil.getBirthday(birthday));
		
		return "student/studentInfo";
	}

	//点击学生修改密码按钮，跳转到修改密码页面
	@RequestMapping("studentChangePwdPage.htm")
	public String dispatcher(){
		return "student/studentChangePwd";
	}
	
	//修改密码
		@RequestMapping("studentChangePwd.htm")
		public String studentChangePwd(HttpServletRequest request,@RequestParam("oldPwd") String oldPwd,@RequestParam("newPwd") String newPwd,@RequestParam("rePwd") String rePwd){
			
			//先要验证旧密码是否输对了
			Student studentLogin = (Student) request.getSession().getAttribute("studentLogin");
			
			String oldPwdMD5 = MD5Password.md5(oldPwd,studentLogin.getNo());
			
			String newPwdMD5 = MD5Password.md5(newPwd,studentLogin.getNo());
			
			if(oldPwd==null||"".equals(oldPwd)){
				request.setAttribute("error1", "不能为空");
				return "student/studentChangePwd";
			}
			
			if(newPwd==null||"".equals(newPwd)){
				request.setAttribute("error2", "不能为空");
				return "student/studentChangePwd";
			}
			
			if(rePwd==null||"".equals(rePwd)){
				request.setAttribute("error3", "不能为空");
				return "student/studentChangePwd";
			}
			
			if(studentLogin.getPassword().equals(oldPwdMD5)){
				if(newPwd.equals(rePwd)){
					studentService.updateStudentByPassword(newPwdMD5, studentLogin.getId());
					request.setAttribute("out",1);
					request.setAttribute("role", studentLogin);
					request.getSession().removeAttribute("studentLogin");
					return "tips/success";
				}else{
					request.setAttribute("error3", "确认密码不一致");
					return "student/studentChangePwd";
				}
			}else{
				request.setAttribute("error3", "旧密码不正确");
				return "student/studentChangePwd";
			}
		}

		//修改个人信息
		@RequestMapping("updateStudentInfo.htm")
		public String updateStudentInfo(HttpServletRequest request,@RequestParam("nickname") String nickname,@RequestParam("birthday") String birthday){
			
			Student studentLogin = (Student) request.getSession().getAttribute("studentLogin");
			
			studentService.updateByPrimaryKeySelective(studentLogin.getId(),nickname, birthday);
			
			return "tips/success";
		}
}
