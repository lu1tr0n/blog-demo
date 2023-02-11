package com.luis.navarro.blog.controllers;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ArticleController {

	@GetMapping("/article")
	public String getAll(Model model, @Param("name") String name) {
		model.addAttribute("name", name);
		return "frontend/article";
	}
}
