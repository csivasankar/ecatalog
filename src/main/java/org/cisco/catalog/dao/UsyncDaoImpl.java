package org.cisco.catalog.dao;

import java.util.List;

import org.cisco.catalog.domain.Usync;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UsyncDaoImpl implements UsyncDao {

	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public Usync findByUuid(String uuid) {
		Criteria crit = getSession().createCriteria(Usync.class);
		crit.add(Restrictions.eq("uuid", uuid));
		return (Usync) crit.uniqueResult();
	}

	public long count() {
		return ((Number) getSession().createCriteria(Usync.class)
				.setProjection(Projections.rowCount()).uniqueResult())
				.longValue();
	}

	public void delete(Usync usync) {
		getSession().delete(usync);
	}

	public Usync findOne(Integer id) {
		return (Usync) getSession().get(Usync.class, id);
	}

	public List<Usync> findAll() {
		return getSession().createCriteria(Usync.class).list();
	}

	public Usync save(Usync usync) {
		Integer pk = (Integer) getSession().save(usync);
		return findOne(pk);
	}

	public Usync update(Usync usync) {
		getSession().update(usync);
		return findOne(usync.getId());
	}

}
