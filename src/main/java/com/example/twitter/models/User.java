package com.example.twitter.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
	
	public String username;
	private String password;
	
	public User(String u, String p){
		username = u;
		password = p;
	}
	
	public boolean resetPassword(String newPassword){
		password = newPassword;
		return true;
	}
}
