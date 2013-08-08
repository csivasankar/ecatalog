package org.cisco.catalog.dao;

import java.util.Calendar;
import java.util.List;

import org.cisco.catalog.domain.Cartoon;
import org.cisco.catalog.domain.Image;
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
public class CartoonDaoImpl implements CartoonDao {

	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public List<Cartoon> findAllByState(boolean state, Sort sort) {
		Criteria crit = getSession().createCriteria(Cartoon.class);
		crit.add(Restrictions.eq("state", state));
		if(sort.getDir() == Direction.ASC) {
			crit.addOrder(Order.asc(sort.getFeild()));
		} else {
			crit.addOrder(Order.desc(sort.getFeild()));
		}
		return crit.list();
	}

	public Integer findMaxSortOrder() {
		Criteria crit = getSession().createCriteria(Cartoon.class);
		crit.setProjection(Projections.max("sortOrder"));
		return (Integer) crit.uniqueResult();
	}

	public List<Cartoon> findByModifiedAfter(Calendar dateTime, Sort sort) {
		Criteria crit = getSession().createCriteria(Cartoon.class);
		crit.add(Restrictions.gt("modified", dateTime));
		if (sort.getDir() == Direction.ASC) {
			crit.addOrder(Order.asc(sort.getFeild()));
		} else {
			crit.addOrder(Order.desc(sort.getFeild()));
		}
		return crit.list();
	}

	public Cartoon findByImage(Image image) {
		Criteria crit = getSession().createCriteria(Cartoon.class);
		crit.add(Restrictions.eq("image", image));
		return (Cartoon) crit.uniqueResult();
	}

	public Cartoon findByIcon(Image icon) {
		Criteria crit = getSession().createCriteria(Cartoon.class);
		crit.add(Restrictions.eq("icon", icon));
		return (Cartoon) crit.uniqueResult();
	}

	public Cartoon findByNameAndState(String name, boolean state) {
		Criteria crit = getSession().createCriteria(Cartoon.class);
		crit.add(Restrictions.eq("name", name));
		crit.add(Restrictions.eq("state", state));
		return (Cartoon) crit.uniqueResult();
	}

	public long count() {
		return ((Number) getSession().createCriteria(Cartoon.class)
				.setProjection(Projections.rowCount()).uniqueResult())
				.longValue();
	}

	public Cartoon findOne(Integer id) {
		return (Cartoon) getSession().get(Cartoon.class, id);
	}

	public List<Cartoon> findAll(Sort sort) {
		Criteria crit = getSession().createCriteria(Cartoon.class);
		if (sort.getDir() == Direction.ASC) {
			crit.addOrder(Order.asc(sort.getFeild()));
		} else {
			crit.addOrder(Order.desc(sort.getFeild()));
		}
		return crit.list();
	}

	public Cartoon save(Cartoon cartoon) {
		Integer pk = (Integer) getSession().save(cartoon);
		return findOne(pk);
	}

	public Cartoon update(Cartoon cartoon) {
		getSession().update(cartoon);
		return findOne(cartoon.getId());
	}

}
