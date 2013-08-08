package org.cisco.catalog.dao;

import org.cisco.catalog.domain.BlobData;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BlobDataDaoImpl implements BlobDataDao {

	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public void delete(BlobData blobData) {
		getSession().delete(blobData);
	}

	public BlobData findOne(Integer id) {
		return (BlobData) getSession().get(BlobData.class, id);
	}

	public BlobData save(BlobData blobData) {
		Integer pk = (Integer) getSession().save(blobData);
		return findOne(pk);
	}

	public BlobData update(BlobData blobData) {
		getSession().update(blobData);
		return findOne(blobData.getId());
	}

}
