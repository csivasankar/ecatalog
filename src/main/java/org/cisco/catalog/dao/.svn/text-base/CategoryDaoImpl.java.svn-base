package org.cisco.catalog.dao;

import java.util.Calendar;
import java.util.List;

import org.cisco.catalog.domain.Category;
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
public class CategoryDaoImpl implements CategoryDao {

	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public List<Category> findAllByState(boolean state, Sort sort) {
		Criteria crit = getSession().createCriteria(Category.class);
		crit.add(Restrictions.eq("state", state));
		if (sort.getDir() == Direction.ASC) {
			crit.addOrder(Order.asc(sort.getFeild()));
		} else {
			crit.addOrder(Order.desc(sort.getFeild()));
		}
		return crit.list();
	}

	public Integer findMaxSortOrder() {
		Criteria crit = getSession().createCriteria(Category.class);
		crit.setProjection(Projections.max("sortOrder"));
		return (Integer) crit.uniqueResult();
	}

	public long countAllByState(boolean state) {
		return ((Number) getSession().createCriteria(Category.class)
				.add(Restrictions.eq("state", state))
				.setProjection(Projections.rowCount()).uniqueResult())
				.longValue();
	}

	public List<Category> findByModifiedAfter(Calendar dateTime, Sort sort) {
		Criteria crit = getSession().createCriteria(Category.class);
		crit.add(Restrictions.gt("modified", dateTime));
		if (sort.getDir() == Direction.ASC) {
			crit.addOrder(Order.asc(sort.getFeild()));
		} else {
			crit.addOrder(Order.desc(sort.getFeild()));
		}
		return crit.list();
	}

	public Category findByNameAndState(String name, boolean state) {
		Criteria crit = getSession().createCriteria(Category.class);
		crit.add(Restrictions.eq("name", name));
		crit.add(Restrictions.eq("state", state));
		return (Category) (crit.uniqueResult());
	}

	public long count() {
		return ((Number) getSession().createCriteria(Category.class)
				.setProjection(Projections.rowCount()).uniqueResult())
				.longValue();
	}

	public Category findOne(Integer id) {
		return (Category) getSession().get(Category.class, id);
	}

	public Category save(Category category) {
		Integer pk = (Integer) getSession().save(category);
		return findOne(pk);
	}

	public Category update(Category category) {
		getSession().update(category);
		return findOne(category.getId());
	}

	public List<Category> findAll(Sort sort) {
		Criteria crit = getSession().createCriteria(Category.class);
		if (sort.getDir() == Direction.ASC) {
			crit.addOrder(Order.asc(sort.getFeild()));
		} else {
			crit.addOrder(Order.desc(sort.getFeild()));
		}
		return crit.list();
	}

}
