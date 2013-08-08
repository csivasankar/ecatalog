package org.cisco.catalog.dao;

import java.util.List;

import org.cisco.catalog.domain.ProductPromotion;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductPromotionDaoImpl implements ProductPromotionDao {

	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public ProductPromotion findByProductIdAndPromotionId(Integer productId,
			Integer promotionId) {
		Criteria crit = getSession().createCriteria(ProductPromotion.class);
		crit.add(Restrictions.eq("productId", productId));
		crit.add(Restrictions.eq("promotionId", promotionId));
		return (ProductPromotion) crit.uniqueResult();
	}

	public List<ProductPromotion> findByPromotionId(Integer promotionId) {
		Criteria crit = getSession().createCriteria(ProductPromotion.class);
		crit.add(Restrictions.eq("promotionId", promotionId));
		return crit.list();
	}

	public List<ProductPromotion> findByProductId(Integer productId) {
		Criteria crit = getSession().createCriteria(ProductPromotion.class);
		crit.add(Restrictions.eq("productId", productId));
		return crit.list();
	}

	public long count() {
		return ((Number) getSession().createCriteria(ProductPromotion.class)
				.setProjection(Projections.rowCount()).uniqueResult())
				.longValue();
	}

	public void delete(ProductPromotion productPromotion) {
		getSession().delete(productPromotion);
	}

	public ProductPromotion findOne(Integer id) {
		return (ProductPromotion) getSession().get(ProductPromotion.class, id);
	}

	public List<ProductPromotion> findAll() {
		return getSession().createCriteria(ProductPromotion.class).list();
	}

	public ProductPromotion save(ProductPromotion productPromotion) {
		Integer pk = (Integer) getSession().save(productPromotion);
		return findOne(pk);
	}

	public ProductPromotion update(ProductPromotion productPromotion) {
		getSession().update(productPromotion);
		return findOne(productPromotion.getId());
	}

}
