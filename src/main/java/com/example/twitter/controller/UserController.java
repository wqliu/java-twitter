package com.example.twitter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.dao.DataAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.example.twitter.models.*;

@Controller
@RequestMapping(path="/demo")
public class UserController {
	@Autowired
    JdbcTemplate jdbcTemplate;    	
    
    @GetMapping(path="/getAllUsers")
    public @ResponseBody List<User> getAllUser(){
    	//List<User> result = new ArrayList<>();
    	try {
    		List<User> r = jdbcTemplate.query("SELECT * FROM user", new Object[]{}, new UserMapper());
    	    return r;
    	}
    	catch(DataAccessException e) {
    		e.printStackTrace();
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    	finally {
    	}
    	System.out.println("hello");
    	return new ArrayList<User>();
    }
}

class UserMapper implements RowMapper<User>{
	@Override
	public User mapRow(ResultSet rs, int numRow) throws SQLException {
		return new User(rs.getString("username"),rs.getString("password"));
	}
}