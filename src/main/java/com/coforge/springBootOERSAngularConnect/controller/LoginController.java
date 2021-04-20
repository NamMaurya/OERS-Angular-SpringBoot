package com.coforge.springBootOERSAngularConnect.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("http://localhost:4200")
@RestController
//@RequestMapping(value="/login")
public class LoginController {

	@GetMapping(value = "/")
	public String login() {
		return "login success";
	}

}