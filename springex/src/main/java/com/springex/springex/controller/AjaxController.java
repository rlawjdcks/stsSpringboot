package com.springex.springex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class AjaxController {

	@GetMapping("/ajax")
	public String ajax() {
		return "ajax1";
	}
	
}
