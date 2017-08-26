package cn.ssmc.classtime.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;

import cn.ssmc.classtime.service.ClassTimeService;
import cn.ssmc.entity.ClassTime;

@Controller
@RequestMapping("/classTime")
public class ClassTimeController {

	@Autowired
	private ClassTimeService classTimeService;
	
	@RequestMapping("classTimeListAjax.htm")
	public void getClassTimeListAjax(HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		String max = request.getParameter("max");
		
		List<ClassTime> classTimeList = classTimeService.selectClassBySize(max);
		
		Gson gson = new Gson();
		String json = gson.toJson(classTimeList);
		
		System.out.println("json:" + json);
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json;charset=utf-8");
		
		response.getWriter().print(json);
		
	}
	
}
