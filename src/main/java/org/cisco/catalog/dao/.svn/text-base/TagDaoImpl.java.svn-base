package org.cisco.catalog.dao;

import java.util.List;

import org.cisco.catalog.domain.Tag;
import org.cisco.catalog.util.Sort;
import org.cisco.catalog.util.Sort.Direction;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TagDaoImpl implements TagDao {

	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public Tag findByName(String name) {
		Criteria crit = getSession().createCriteria(Tag.class);
		crit.add(Restrictions.eq("name", name));
		return (Tag) crit.uniqueResult();
	}

	public long count() {
		return ((Number) getSession().createCriteria(Tag.class)
				.setProjection(Projections.rowCount()).uniqueResult())
				.longValue();
	}

	public void delete(Tag tag) {
		getSession().delete(tag);
	}

	public Tag findOne(Integer id) {
		return (Tag) getSession().get(Tag.class, id);
	}

	public Tag save(Tag tag) {
		Integer pk = (Integer) getSession().save(tag);
		return findOne(pk);
	}

	public Tag update(Tag tag) {
		getSession().update(tag);
		return findOne(tag.getId());
	}

	public List<Tag> findAll(Sort sort) {
		Criteria crit = getSession().createCriteria(Tag.class);
		if (sort.getDir() == Direction.ASC) {
			crit.addOrder(Order.asc(sort.getFeild()));
		} else {
			crit.addOrder(Order.desc(sort.getFeild()));
		}
		return crit.list();
	}

}
