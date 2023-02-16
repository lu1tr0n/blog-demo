package com.luis.navarro.blog.services;

import java.util.List;

import com.luis.navarro.blog.entities.User;

public interface UserService {

	/**
	 * @return
	 */
	public List<User> findAll();
	
	/**
	 * @param user
	 */
	public void save(User user);
	
	/**
	 * @param username
	 * @return
	 */
	public User findByUsername(String username);
	
	/**
	 * @param email
	 * @return
	 */
	public User findByEmail(String email);
}
