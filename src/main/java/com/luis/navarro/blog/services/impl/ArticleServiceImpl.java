package com.luis.navarro.blog.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luis.navarro.blog.entities.Article;
import com.luis.navarro.blog.repositories.ArticleRepository;
import com.luis.navarro.blog.services.ArticleService;

@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private ArticleRepository repo;
	
	@Override
	public List<Article> findAll() {
		return repo.findAll();
	}

}
