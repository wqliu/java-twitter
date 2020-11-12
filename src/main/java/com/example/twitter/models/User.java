package com.example.twitter.models;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id; 
import com.fasterxml.jackson.annotation.*;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
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
