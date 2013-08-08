package org.cisco.catalog.dao;

import java.util.Calendar;
import java.util.List;

import org.cisco.catalog.domain.Category;
import org.cisco.catalog.domain.Product;
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
public class ProductDaoImpl implements ProductDao {

	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public List<Product> findAllByState(boolean state) {
		Criteria crit = getSession().createCriteria(Product.class);
		crit.add(Restrictions.eq("state", state));
		return crit.list();
	}

	public List<Product> findByCategoryAndState(Category category, boolean state) {
		Criteria crit = getSession().createCriteria(Product.class);
		crit.add(Restrictions.eq("category", category));
		crit.add(Restrictions.eq("state", state));
		return crit.list();
	}

	public List<Product> findByModifiedAfter(Calendar dateTime) {
		Criteria crit = getSession().createCriteria(Product.class);
		crit.add(Restrictions.gt("modified", dateTime));
		return crit.list();
	}

	public List<Product> findByTag(Tag tag) {
		Criteria crit = getSession().createCriteria(Product.class);
		crit.add(Restrictions.eq("tag", tag));
		return crit.list();
	}

	public List<Product> findByCategoryAndState(Category category,
			boolean state, Sort sort, int firstRes, int maxRes) {
		Criteria crit = getSession().createCriteria(Product.class);
		crit.add(Restrictions.eq("category", category));
		crit.add(Restrictions.eq("state", state));
		if (sort.getDir() == Direction.ASC) {
			crit.addOrder(Order.asc(sort.getFeild()));
		} else {
			crit.addOrder(Order.desc(sort.getFeild()));
		}
		crit.setFirstResult(firstRes).setMaxResults(maxRes);
		return crit.list();
	}

	public long count() {
		return ((Number) getSession().createCriteria(Product.class)
				.add(Restrictions.eq("state", true))
				.setProjection(Projections.rowCount()).uniqueResult())
				.longValue();
	}
	
	public long countByCategory(Category category) {
		return ((Number) getSession().createCriteria(Product.class)
				.add(Restrictions.eq("state", true))
				.add(Restrictions.eq("category", category))
				.setProjection(Projections.rowCount()).uniqueResult())
				.longValue();
	}

	public Product findOne(Integer id) {
		return (Product) getSession().get(Product.class, id);
	}

	public List<Product> findAll() {
		return getSession().createCriteria(Product.class).list();
	}

	public Product save(Product entity) {
		Integer pk = (Integer) getSession().save(entity);
		return findOne(pk);
	}

	public List<Product> findAllByState(boolean state, Sort sort, int firstRes, int maxRes) {
		Criteria crit = getSession().createCriteria(Product.class);
		crit.add(Restrictions.eq("state", state));
		if (sort.getDir() == Direction.ASC) {
			crit.addOrder(Order.asc(sort.getFeild()));
		} else {
			crit.addOrder(Order.desc(sort.getFeild()));
		}
		crit.setFirstResult(firstRes).setMaxResults(maxRes);
		return crit.list();
	}

	public Product update(Product product) {
		return (Product) getSession().merge(product);
	}
	
	public List<Product> findAllByCategoryAndSortOrder(boolean state, Sort sort, Category category) {
		Criteria crit = getSession().createCriteria(Product.class);
		crit.add(Restrictions.eq("state", state));
		if (sort.getDir() == Direction.ASC) {
			crit.addOrder(Order.asc(sort.getFeild()));
		} else {
			crit.addOrder(Order.desc(sort.getFeild()));
		}
		if (category != null) {
			crit.add(Restrictions.eq("category", category));
		} else {
			crit.add(Restrictions.isNull("category"));
		}
		return crit.list();
	}
	
	public Integer findMaxSortOrder(boolean state, Category category) {
		Criteria crit = getSession().createCriteria(Product.class);
		crit.add(Restrictions.eq("state", state));
		crit.setProjection(Projections.max("sortOrder"));
		crit.add(Restrictions.eq("category", category));
		return (Integer) crit.uniqueResult();
	}

}
