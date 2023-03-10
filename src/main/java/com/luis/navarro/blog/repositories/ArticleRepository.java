package com.luis.navarro.blog.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luis.navarro.blog.entities.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Serializable> {

}
