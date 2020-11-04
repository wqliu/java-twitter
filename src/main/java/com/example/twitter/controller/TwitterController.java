package com.example.twitter.controller;
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
    JdbcTemplate jdbcTemplate; 
	
	@PostMapping(path="/postTwitter")
	public @ResponseBody Boolean postTwitter(@RequestBody Map<String, Object> payload) {
		try {
			String username = payload.get("username").toString();
			int id = jdbcTemplate.queryForObject("select id from user where username=?",new Object[]{username}, int.class);
			try {
				String text = payload.get("twitter").toString();
	            Date today = Calendar.getInstance().getTime();
			    jdbcTemplate.update("insert into twitter(userId,text,date,likes) values(?,?,?,?)", id, text, today, 0);
			}catch(Exception e) {
				e.printStackTrace();
			    return false;	
			}
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
}
