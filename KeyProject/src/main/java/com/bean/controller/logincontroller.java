package com.bean.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class logincontroller {

	@RequestMapping("/login")
	public String loginPage(Model model, String error, String logout) {
		if (error != null) {
			model.addAttribute("error", "Invalid username or password.");
		}
		if (logout != null) {
			model.addAttribute("message", "You have been logged out successfully.");
		}
		return "login"; // login.jsp
	}

}
