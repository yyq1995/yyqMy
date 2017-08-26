package cn.ssmc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.ssmc.entity.Admin;
import cn.ssmc.entity.Student;
import cn.ssmc.entity.Teacher;

public class CommonInterceptor extends HandlerInterceptorAdapter{

	//拦截器
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Admin admin =  (Admin)request.getSession().getAttribute("adminLogin");
		Student student = (Student) request.getSession().getAttribute("studentLogin");
		Teacher teacher = (Teacher) request.getSession().getAttribute("teacherLogin");
		//System.out.println(admin);
		String uri =  request.getRequestURI();
		System.out.println("uri:"+uri);
		String[] u = uri.split("/");
		String uStr = u[u.length-1];
		System.out.println(uStr);
		//如果路径允许直接进入就放行，如果不允许则进行判断
		if("loginPage.htm".equals(uStr)||"login.htm".equals(uStr)||"img.htm".equals(uStr)||"out.htm".equals(uStr)||"404.htm".equals(uStr)){
			if("login.htm".equals(uStr)&&request.getParameter("no")==null){
				request.setAttribute("interceptorTips", "请先登陆!");
				request.getRequestDispatcher("/loginPage.htm").forward(request, response);
		        System.out.println("-------拒绝---------");
		        return false; 
			}
			System.out.println("-------放行---------");
		    return true; 
		}else if(admin != null || student!=null || teacher!=null){  
            
			System.out.println("-------放行---------");
		    return true;  
        } 
		request.setAttribute("interceptorTips", "请先登陆!");
		request.getRequestDispatcher("/loginPage.htm").forward(request, response);
        System.out.println("-------拒绝---------");
        return false; 
	}
}
