package org.cisco.catalog.dao;

import java.util.List;

import org.cisco.catalog.domain.User;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	
	public User findByUsername(String username) {
		Criteria crit = getSession().createCriteria(User.class);
		crit.add(Restrictions.eq("username", username));
		return (User) crit.uniqueResult();
	}

	
	public long count() {
		return ((Number) getSession().createCriteria(User.class)
				.setProjection(Projections.rowCount()).uniqueResult())
				.longValue();
	}

	
	public void delete(User user) {
		getSession().delete(user);
	}

	
	public User findOne(Integer id) {
		return (User) getSession().get(User.class, id);
	}

	
	public List<User> findAll() {
		return getSession().createCriteria(User.class).list();
	}

	
	public User save(User user) {
		Integer pk = (Integer) getSession().save(user);
		return findOne(pk);
	}

	
	public User update(User user) {
		getSession().update(user);
		return findOne(user.getId());
	}

}
