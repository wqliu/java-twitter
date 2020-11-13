package com.example.twitter.controller;

import com.example.twitter.dao.*;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.twitter.models.User;
import java.util.List;
//import com.example.twitter.jpa.UserRepository; 
//import org.springframework.context.support.ClassPathXmlApplicationContext;


@Controller
@RequestMapping(path="/java-twitter")
public class UserController {
	//public UserRepository userRepository;
	@Autowired
    public UserDao userDao;
    public UserController() {
    	//ClassPathXmlApplicationContext  r = new ClassPathXmlApplicationContext("applicationContext.xml");    
        //userDao = new UserDao();
    	//userDao=(UserDao)r.getBean(UserDao.class);  
        //System.out.println(userDao.template.getSessionFactory().getCurrentSession().getFlushMode());
    }
	
    @Transactional
	@PostMapping(path="/addUser")
	public @ResponseBody boolean addUser(@RequestBody User user) {
		//System.out.println(user.username);
		//System.out.println(user.id);
		userDao.saveUser(user);
		return true;
	}
	
    
	@GetMapping(path="/getUserById/{id}")
	public @ResponseBody User getUserById(@PathVariable(value="id") int id){
		return userDao.getUserById(id);
	}
	
	@GetMapping(path="/getAllUsers")
	public @ResponseBody List<User> getAllUsers(){
		return userDao.getAllUsers();
	}
	
	@PostMapping(path="/updateUser")
	public @ResponseBody boolean updateUserById(@RequestBody User u) {
		userDao.updateUser(u);
		return true;
	}
	
	@Transactional
	@DeleteMapping(path="/deleteUser")
	public @ResponseBody boolean deleteUserById(@RequestBody User u) {
	    userDao.deleteUser(u);
	    return true;
	}
	
}



