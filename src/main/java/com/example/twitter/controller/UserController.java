package com.example.twitter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

import java.sql.*;

import com.example.twitter.models.*;

@Controller
@RequestMapping(path="/demo")
public class UserController {
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
}
