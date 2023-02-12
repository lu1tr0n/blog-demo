package com.luis.navarro.blog.services.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.luis.navarro.blog.entities.Role;
import com.luis.navarro.blog.entities.User;
import com.luis.navarro.blog.repositories.RoleRepository;
import com.luis.navarro.blog.repositories.UserRepository;
import com.luis.navarro.blog.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private RoleRepository roleRepo;

	@Override
	public List<User> findAll() {
		return userRepo.findAll();
	}

	@Override
	public void save(User user) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		Set<Role> roles = new HashSet<>(roleRepo.findAll());
		user.setRoles(roles);
		userRepo.save(user);
	}

	@Override
	public User findByUsername(String username) {
		return userRepo.findByUsername(username);
	}

}
