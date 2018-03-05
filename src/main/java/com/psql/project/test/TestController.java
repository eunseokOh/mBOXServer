package com.psql.project.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("test")
@Controller
public class TestController {
	
	@RequestMapping("/")
	public String testIndex(){
		return "test/test";
	}
	
}
