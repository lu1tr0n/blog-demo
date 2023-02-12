package com.luis.navarro.blog.services;

public interface SecurityService {

	public boolean isAuthenticated();
    public void autoLogin(String username, String password);
}
