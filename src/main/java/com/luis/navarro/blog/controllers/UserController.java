package com.luis.navarro.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.luis.navarro.blog.entities.User;
import com.luis.navarro.blog.services.SecurityService;
import com.luis.navarro.blog.services.UserService;
import com.luis.navarro.blog.utils.UserValidator;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class UserController {
	
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @GetMapping("/registration")
    public String registration(Model model) {
        if (securityService.isAuthenticated()) {
            return "redirect:/frontend/homepage";
        }
        model.addAttribute("userForm", new User());
        return "frontend/registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "frontend/registration";
        }

        userService.save(userForm);
        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout, HttpServletResponse httpResponse) {
        if (securityService.isAuthenticated()) {
            return "redirect:/welcome";
        }

        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");
        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");
        return "frontend/login";
    }
    
    @PostMapping("/login")
    public String login(Model model, String error, String logout) {
        if (securityService.isAuthenticated()) {
            return "redirect:/welcome";
        }

        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");
        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");
        return "frontend/login";
    }
	
	@GetMapping("/users")
	public String listUsers(Model model) {
		model.addAttribute("listUsers", userService.findAll());
		return "frontend/users";
	}
}
