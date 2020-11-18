package com.example.twitter.dao;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.twitter.models.Twitter;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class TwitterDao {
	@Autowired
    private SessionFactory _sessionFactory;
	
	public Session getCurrentSession() {
		return _sessionFactory.getCurrentSession();
	}
	
	public void saveTwitter(Twitter t) {
	    getCurrentSession().save(t);	
	}
	
}
