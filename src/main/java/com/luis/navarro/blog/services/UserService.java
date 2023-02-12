package com.luis.navarro.blog.services;

import java.util.List;

import com.luis.navarro.blog.entities.User;

public interface UserService {

	public List<User> findAll();
	
	public void save(User user);
	
	public User findByUsername(String username);
}
