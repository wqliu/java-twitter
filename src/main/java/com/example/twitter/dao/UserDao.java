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
	
	private Session getSession() {
        return _sessionFactory.getCurrentSession();
    }
	
	public void saveUser(User u){  
		getSession().save(u);
	}  
	  
	public User getUserById(int id) {
		return getSession().get(User.class, id);
	}
	
	public List<User> getAllUsers(){
		return getSession().createQuery("from User").list();
	}
	
	public void updateUser(User u){
		getSession().saveOrUpdate(u);
	    //getSession().createQuery(String.format("update User set username = %s where id = %d", u.username, u.id)).executeUpdate();  
	}  
	  
	public void deleteUser(User u){  
		//User u2 = getSession().get(User.class, u.id);
		//System.out.println(u2.username);
	    getSession().delete(u);  
	}  
	 
	
	public List<User> getUsers(){  
	    List<User> list=new ArrayList<User>();  
	    //list=template.loadAll(User.class);  
	    return list;  
	}  
}
