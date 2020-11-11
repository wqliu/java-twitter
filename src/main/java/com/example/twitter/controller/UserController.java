package com.example.twitter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.hibernate.FlushMode;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.twitter.dao.UserDao;
import com.example.twitter.models.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;  

@Controller
@RequestMapping(path="/java-twitter")
public class UserController {
    //public JdbcTemplate jdbcTemplate;    	
	//@Autowired
    public UserDao userDao;
    public UserController() {
    	ClassPathXmlApplicationContext  r = new ClassPathXmlApplicationContext("applicationContext.xml");    
        //userDao = new UserDao();
    	userDao=(UserDao)r.getBean(UserDao.class);  
        //System.out.println(userDao.template.getSessionFactory().getCurrentSession().getFlushMode());
    }
	
    @Transactional
	@PostMapping(path="/addUser")
	public @ResponseBody boolean addUser(@RequestBody User user) {
		userDao.saveUser(user);
		return true;
	}
	
	@GetMapping(path="/getUserById/{id}")
	public @ResponseBody User getUserById(@PathVariable(value="id") int id){
		return userDao.getUserById(id);
	}
	
	@PostMapping(path="/updateUser")
	public @ResponseBody boolean updateUserById(User u) {
		userDao.updateUser(u);
		return true;
	}
	
	@DeleteMapping(path="/deleteUser")
	public @ResponseBody boolean deleteUserById(User u) {
	    userDao.deleteUser(u);
	    return true;
	}
	
	
	
	public static void main(String[] args) {
		UserController a = new UserController();
		//a.userDao.save(new User("hello"));
	}
}



