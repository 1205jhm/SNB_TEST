package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entity.SnbUser;
import com.example.demo.service.UserService;

@Controller
public class UserController {

	@Autowired
	UserService userService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup() {
		return "signup";
	}
	
	@RequestMapping(value = "/join.do", method = RequestMethod.POST)
	public @ResponseBody int join(@ModelAttribute SnbUser snbuser) {
		return userService.join(snbuser);
	}
}
