package com.psql.project.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.psql.project.vo.UserDTO;





@Controller
@RequestMapping("user")
public class UserController {
	@Autowired
	UserService uService;
	
	
	@ResponseBody
	@RequestMapping(path = "/", method = RequestMethod.POST)
	public String userJoin(UserDTO userDTO){
		
		System.out.println(userDTO.getUserName()+ " user join");
		try {
			
			uService.addUser(userDTO);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return "success";
	}
	
	@ResponseBody
	@RequestMapping(path ="/", method = RequestMethod.GET)
	public String userTest(){
		try {
			uService.testUser();

		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return "hello";
	}
	

}
