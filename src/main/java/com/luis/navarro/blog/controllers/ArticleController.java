package com.luis.navarro.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.luis.navarro.blog.services.ArticleService;

@Controller
public class ArticleController {
	
	@Autowired
	private ArticleService articleService;

	@GetMapping("/article")
	public String getAll(Model model, @Param("name") String name) {
		
		model.addAttribute("article_list", articleService.findAll());
		return "frontend/article";
	}
}
