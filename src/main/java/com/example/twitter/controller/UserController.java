package com.example.twitter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.dao.DataAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.example.twitter.models.*;

@Controller
@RequestMapping(path="/java-twitter")
public class UserController {
	@Autowired
    JdbcTemplate jdbcTemplate;    	
    
	@PostMapping(path="/addUser")
	public @ResponseBody boolean addUser(@RequestBody Map<String,String> user) {
		try {
		    jdbcTemplate.update("insert into user(username,sex) values (?,?)",user.get("username"),user.get("sex"));
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	@GetMapping(path="/getUserById/{id}")
	public @ResponseBody String getUserById(@PathVariable(value="id") String id){
		try { 
			return jdbcTemplate.queryForObject("select username from user where id=?",new Object[] {Integer.parseInt(id)},String.class);
		}
		catch(Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	@PostMapping(path="/updateUserById/{id}")
	public @ResponseBody boolean updateUserById(@PathVariable(value="id") String id,@RequestBody String json) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			Map<String,Object> map = mapper.readValue(json, Map.class);
			String username = map.get("username").toString();
			String sex = map.get("sex").toString();
			jdbcTemplate.update("update user set username = ? where id = ?",username,Integer.parseInt(id));
			jdbcTemplate.update("update user set sex = ? where id = ?",sex,Integer.parseInt(id));
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	@DeleteMapping(path="deleteUserById/{id}")
	public @ResponseBody boolean deleteUserById(@PathVariable(value="id") String id) {
		try {
			jdbcTemplate.update("delete from user where id = ?",Integer.parseInt(id));
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	/*
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
    */
}



/*
class UserMapper implements RowMapper<User>{
	@Override
	public User mapRow(ResultSet rs, int numRow) throws SQLException {
		return new User(rs.getString("username"));
	}
}
*/