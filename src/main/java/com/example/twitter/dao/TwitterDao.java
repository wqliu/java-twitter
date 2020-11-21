package com.example.twitter.dao;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.Proxy;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.twitter.models.Twitter;
import org.hibernate.SessionFactory;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@Proxy(lazy = false)
public class TwitterDao {
	@Autowired
    private SessionFactory _sessionFactory;
	
	public Session getCurrentSession() {
		return _sessionFactory.getCurrentSession();
	}
	
	public void saveTwitter(Twitter t) {
	    getCurrentSession().save(t);	
	}
	
	public Twitter getTwitterById(long id) {
	    return getCurrentSession().get(Twitter.class, id);	
	}
	
	public void update(Twitter t) {
		getCurrentSession().update(t);
	}
	
	public void delete(long id) {
		getCurrentSession().delete(id);
	}
	
	public void likeTwitter(long id) {
        Session s = getCurrentSession();
        Twitter t = s.load(Twitter.class, id);
        t.setLikes(t.getLikes()+1);
        s.save(t);
	}
}
