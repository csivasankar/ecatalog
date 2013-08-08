package org.cisco.catalog.dao;

import java.util.List;

import org.cisco.catalog.domain.Upload;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UploadDaoImpl implements UploadDao {

	@Autowired
	private SessionFactory sessionFactory;

	public UploadDaoImpl() {

	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public long count() {
		return ((Number) getSession().createCriteria(Upload.class)
				.setProjection(Projections.rowCount()).uniqueResult())
				.longValue();
	}

	public void delete(Upload upload) {
		getSession().delete(upload);
	}

	public Upload findOne(Integer id) {
		return (Upload) getSession().get(Upload.class, id);
	}

	public List<Upload> findAll() {
		return getSession().createCriteria(Upload.class).list();
	}

	public Upload save(Upload upload) {
		Integer pk = (Integer) getSession().save(upload);
		return findOne(pk);
	}

	public Upload update(Upload upload) {
		getSession().update(upload);
		return findOne(upload.getId());
	}

	public List<Upload> findUploadEntries(int startIndex, int maxRecords) {
		return getSession().createCriteria(Upload.class)
				.setFirstResult(startIndex).setMaxResults(maxRecords)
				.addOrder(Order.asc("id")).list();
	}

}
