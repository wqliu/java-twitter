package com.example.twitter.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.Calendar;

@Entity
public class Twitter {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    //private User user;
    public String location;
    public Date date;
    
    public Twitter(User u) {
    	location = "";
    	date = Calendar.getInstance().getTime();
    	//user = u;
    }
}
