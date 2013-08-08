package org.cisco.catalog.dao;

import java.util.List;

import org.cisco.catalog.domain.Category;
import org.cisco.catalog.domain.Promotion;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PromotionDaoImpl implements PromotionDao {

	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public List<Promotion> findByCategory(Category category) {
		Criteria crit = getSession().createCriteria(Promotion.class);
		crit.add(Restrictions.eq("category", category));
		return crit.list();
	}

	public long count() {
		return ((Number) getSession().createCriteria(Promotion.class)
				.setProjection(Projections.rowCount()).uniqueResult())
				.longValue();
	}

	public void delete(Promotion promotion) {
		getSession().delete(promotion);
	}

	public Promotion findOne(Integer id) {
		return (Promotion) getSession().get(Promotion.class, id);
	}

	public List<Promotion> findAll() {
		return getSession().createCriteria(Promotion.class).list();
	}

	public Promotion save(Promotion promotion) {
		Integer pk = (Integer) getSession().save(promotion);
		return findOne(pk);
	}

	public Promotion update(Promotion promotion) {
		getSession().update(promotion);
		return findOne(promotion.getId());
	}
}
