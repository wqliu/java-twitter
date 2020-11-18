package com.example.twitter.models;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Proxy;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Calendar;

@Entity
@Proxy(lazy = false)
public class Twitter {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public long id;
    public User user;
    public String text;
    public Date date;
    public int likes;
    
    public Twitter() {}
    
    public Twitter(User u, String t) {
    	user = u;
    	text = t;
    	date = Calendar.getInstance().getTime();
    	likes = 0;
    }
    
    public long getId() {
    	return id;
    }
    
    public void setId(Long id) {
    	this.id = id;
    }
    
    public User getUser() {
    	return user;
    }
    
    public void setUser(User u) {
    	this.user = u;
    }
    
    public String getText() {
    	return text;
    }
    
    public void setText(String t) {
    	text = t;
    }
    
    public Date getDate() {
    	return date;
    }
    
    public void setDate(Date d) {
    	this.date = d;
    }
    
    public int getLikes() {
    	return likes;
    }
    
    public void setLikes(int l) {
    	this.likes = l;
    }
}
