package com.java.onlinevoting;

public class User {
    private String username;
    private String password;
    private int userId;
    private boolean isAdmin;
 
    public User(String username, String password, int userId, boolean isAdmin) {
        this.username = username;
        this.password = password;
        this.userId = userId;
        this.isAdmin = isAdmin;
    }
 
    public User(int int1, String username2, boolean boolean1) {
		// TODO Auto-generated constructor stub
	}
 
	public String getUsername() {
        return username;
    }
 
    public String getPassword() {
        return password;
    }
 
    public int getUserId() {
        return userId;
    }
 
    public boolean isAdmin() {
        return isAdmin;
    }
}
 
 