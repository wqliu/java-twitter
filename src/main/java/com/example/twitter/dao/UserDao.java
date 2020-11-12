package com.example.twitter.dao;
import org.springframework.transaction.annotation.*;
import com.example.twitter.models.User;
import org.springframework.orm.hibernate5.HibernateTemplate; 
import java.util.List;
import java.util.ArrayList;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class UserDao {
	//public HibernateTemplate template;
	@Autowired
    private SessionFactory _sessionFactory;
	public void setTemplate(HibernateTemplate template) {  
	    //.template = template;  
	}
	
	private Session getSession() {
        return _sessionFactory.getCurrentSession();
    }
	
	public void saveUser(User u){  
		//System.out.println(u.id);
	    //template.persist(u);
		getSession().save(u);
	    //System.out.println("22222222");
	}  
	  
	public User getUserById(int id) {
		return getSession().get(User.class, id);
	}
	
	public void updateUser(User u){  
	    getSession().update(u);  
	}  
	  
	public void deleteUser(User u){  
		User u2 = getSession().get(User.class, u.id);
		System.out.println(u2.username);
	    getSession().delete(u2);  
	}  
	 
	
	public List<User> getUsers(){  
	    List<User> list=new ArrayList<User>();  
	    //list=template.loadAll(User.class);  
	    return list;  
	}  
}
