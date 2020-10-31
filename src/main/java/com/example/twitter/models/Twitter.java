package com.example.twitter.models;
import java.util.Date;
import java.util.Calendar;

public class Twitter {
    public User user;
    public String text;
    public Date date;
    public int likes;
    
    public Twitter(User u, String t) {
    	user = u;
    	text = t;
    	date = Calendar.getInstance().getTime();
    	likes = 0;
    }
}
