package org.cisco.catalog.dao;

import java.util.List;

import org.cisco.catalog.domain.Image;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ImageDaoImpl implements ImageDao {

	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public long count() {
		return ((Number) getSession().createCriteria(Image.class)
				.setProjection(Projections.rowCount()).uniqueResult())
				.longValue();
	}

	public void delete(Image image) {
		getSession().delete(image);
	}

	public Image findOne(Integer id) {
		return (Image) getSession().get(Image.class, id);
	}

	public List<Image> findAll() {
		return getSession().createCriteria(Image.class).list();
	}

	public Image save(Image image) {
		Integer pk = (Integer) getSession().save(image);
		return findOne(pk);
	}

	public Image update(Image image) {
		getSession().update(image);
		return findOne(image.getId());
	}

	public List<Image> findImageEntries(int startIndex, int maxRecords) {
		return getSession().createCriteria(Image.class)
				.setFirstResult(startIndex).setMaxResults(maxRecords)
				.addOrder(Order.asc("id")).list();
	}
}
