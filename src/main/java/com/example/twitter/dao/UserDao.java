package com.example.twitter.dao;
import org.springframework.transaction.annotation.*;
import com.example.twitter.models.User;
import org.springframework.orm.hibernate5.HibernateTemplate; 
import java.util.List;
import java.util.ArrayList;

@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
public class UserDao {
	public HibernateTemplate template;  
	public void setTemplate(HibernateTemplate template) {  
	    this.template = template;  
	}  
	  
	public void saveUser(User u){  
		//System.out.println("11111111");
	    template.save(u);  
	    //System.out.println("22222222");
	}  
	  
	public void updateUser(User u){  
	    template.update(u);  
	}  
	  
	public void deleteUser(User u){  
	    template.delete(u);  
	}  
	 
	public User getUserById(int id){  
	    User u=(User)template.get(User.class,id);  
	    return u;  
	}  
	
	public List<User> getUsers(){  
	    List<User> list=new ArrayList<User>();  
	    list=template.loadAll(User.class);  
	    return list;  
	}  
}
