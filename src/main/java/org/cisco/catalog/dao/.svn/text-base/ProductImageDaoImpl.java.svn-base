package org.cisco.catalog.dao;

import java.util.List;

import org.cisco.catalog.domain.Image;
import org.cisco.catalog.domain.ProductImage;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductImageDaoImpl implements ProductImageDao {

	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public ProductImage findByImage(Image image) {
		Criteria crit = getSession().createCriteria(ProductImage.class);
		crit.add(Restrictions.eq("image", image));
		return (ProductImage) crit.uniqueResult();
	}

	public Integer findMaxDisplayOrder(Integer productId) {
		return (Integer) getSession().createCriteria(ProductImage.class)
				.setProjection(Projections.max("displayOrder")).uniqueResult();
	}

	public long count() {
		return ((Number) getSession().createCriteria(ProductImage.class)
				.setProjection(Projections.rowCount()).uniqueResult())
				.longValue();
	}

	public void delete(ProductImage productImage) {
		getSession().delete(productImage);
	}

	public ProductImage findOne(Integer id) {
		return (ProductImage) getSession().get(ProductImage.class, id);
	}

	public List<ProductImage> findAll() {
		return getSession().createCriteria(ProductImage.class).list();
	}

	public ProductImage save(ProductImage productImage) {
		Integer pk = (Integer) getSession().save(productImage);
		return findOne(pk);
	}

	public ProductImage update(ProductImage productImage) {
		getSession().update(productImage);
		return findOne(productImage.getId());
	}

}
