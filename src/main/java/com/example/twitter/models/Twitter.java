package com.example.twitter.models;
import java.util.Date;
import java.util.Calendar;

public class Twitter {

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
