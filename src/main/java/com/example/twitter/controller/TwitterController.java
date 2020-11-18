package com.example.twitter.controller;

import com.example.twitter.dao.TwitterDao;
import com.example.twitter.models.Twitter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.Calendar;
import java.util.Map;

@Controller
@RequestMapping(path="/java-twitter")
public class TwitterController {
	@Autowired
	TwitterDao twitterDao;
    //JdbcTemplate jdbcTemplate; 
	@PostMapping(path="/postTwitter")
	public @ResponseBody Boolean postTwitter(@RequestBody Twitter twitter) {
		System.out.println(twitter.text);
		System.out.println(twitter.user.username);
	    twitterDao.saveTwitter(twitter);
	    return true;
	}
	/*
	@PostMapping(path="/deleteTwitter/{id}")
	public @ResponseBody Boolean deleteTwitter(@PathVariable(value = "id") int id) {
		try {
			jdbcTemplate.update("delete from twitter where id = ?",id);
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	@PostMapping(path="/likeTwitter/{id}")
	public @ResponseBody Boolean likeTwitter(@PathVariable(value = "id") int id) {
		try {
			jdbcTemplate.update("update twitter set likes = likes+1 where id = ?",id);
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	*/
	
	public static void main(String[] args) {
		
	}
}
