package com.example.twitter.models;
//import javax.persistence.*; 
import com.fasterxml.jackson.annotation.*;

//@Entity
public class User {
	public int id;
	public String username;
	
	public User() {}
	
	@JsonCreator
	public User(@JsonProperty("username") String u){
		username = u;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String u) {
		username = u;
	}
}
