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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import java.sql.*;

import com.example.twitter.models.*;

@Controller
@RequestMapping(path="/demo")
public class UserController {
	@Autowired
    JdbcTemplate jdbcTemplate;    	
    static final String JDBC_DRIVER = "com.example.twitter.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://18.224.93.66/DBTEST";

	 //  Database credentials
    static final String USER = "remote";
    static final String PASS = "657@SJTUw";
	
    @GetMapping(path="/test")
    public @ResponseBody String test() {
    	return "hello world!";
    }
    @GetMapping(path="/getAllUser")
    public @ResponseBody ArrayList<User> getAll(){
    	Connection conn = null;
    	Statement stmt = null;
    	ArrayList<User> a = new ArrayList<User>();
     	try {
    	    conn = DriverManager.getConnection(DB_URL,USER,PASS);
    	    stmt = conn.createStatement();
    	    String sql;
    	    sql = "SELECT * FROM user";
    	    ResultSet rs = stmt.executeQuery(sql);
    	    while(rs.next()) {
    	    	a.add(new User(rs.getString("username"),rs.getString("password")));
    	    }
    	    rs.close();
    	    stmt.close();
    	    conn.close();
    	    System.out.println(a);
    	    return a;
    	}
    	catch(SQLException se) {
    		se.printStackTrace();
    	}
     	catch(Exception e) {
     	    e.printStackTrace();	
     	}
     	finally {
     		try{
     	      if(stmt!=null)
     	        stmt.close();
     	    }catch(SQLException se2){
     	    }// nothing we can do
     	    try{
     	       if(conn!=null)
     	          conn.close();
     	    }catch(SQLException se){
     	       se.printStackTrace();
     	    }//end finally try
       }//end try
       return a;
    }
    
    @GetMapping(path="/getAllTest2")
    public @ResponseBody List<User> getAll2(){
    	ArrayList<User> result = new ArrayList<>();
    	try {
    	    List<Map<String,Object>> r = jdbcTemplate.queryForList("SELECT * FROM user");
    	    for(int i=0;i<r.size();i++) {
    	    	Map<String,Object> m = r.get(i);
    	    	String username = m.get("username").toString();
    	    	String password = m.get("password").toString();
    	    	result.add(new User(username,password));
    	    }
    	    return result;
    	}
    	catch(DataAccessException e) {
    		e.printStackTrace();
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    	finally {
    		
    	}
    	return result;
    }
}
