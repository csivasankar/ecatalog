package org.cisco.catalog.dao;

import java.util.List;

import org.cisco.catalog.domain.Group;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GroupDaoImpl implements GroupDao {

	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public Group findByName(String name) {
		Criteria crit = getSession().createCriteria(Group.class);
		crit.add(Restrictions.eq("name", name));
		return (Group) crit.uniqueResult();
	}

	public long count() {
		return ((Number) getSession().createCriteria(Group.class)
				.setProjection(Projections.rowCount()).uniqueResult())
				.longValue();
	}

	public void delete(Group group) {
		getSession().delete(group);
	}

	public Group findOne(Integer id) {
		return (Group) getSession().get(Group.class, id);
	}

	public List<Group> findAll() {
		return getSession().createCriteria(Group.class).list();
	}

	public Group save(Group group) {
		Integer pk = (Integer) getSession().save(group);
		return findOne(pk);
	}

	public Group update(Group group) {
		getSession().update(group);
		return findOne(group.getId());
	}

}
