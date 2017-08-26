package cn.ssmc.college.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.ssmc.college.service.CollegeService;
import cn.ssmc.entity.College;

@Controller
public class CollegeController {
	@Autowired
	private CollegeService collegeService;
	
	@RequestMapping("/collegeList.htm")
	public void getCollefeList(){
		List<College> list = collegeService.selectCollegeList();
	}
}
