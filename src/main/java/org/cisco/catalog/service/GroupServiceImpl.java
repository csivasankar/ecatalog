package org.cisco.catalog.service;

import java.util.Calendar;
import java.util.List;
import org.cisco.catalog.dao.GroupDao;
import org.cisco.catalog.domain.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GroupServiceImpl implements GroupService {

	@Autowired
	GroupDao groupDao;

	public long countAllGroups() {
		return groupDao.count();
	}

	public void deleteGroup(Group group) {
		groupDao.delete(group);
	}

	public Group findGroup(Integer id) {
		return groupDao.findOne(id);
	}

	public List<Group> findAllGroups() {
		return groupDao.findAll();
	}

	public void saveGroup(Group group) {
		populateModifiedDate(group);
		groupDao.save(group);
	}

	public Group updateGroup(Group group) {
		populateModifiedDate(group);
		return groupDao.update(group);
	}

	private void populateModifiedDate(Group group) {
		group.setModified(Calendar.getInstance());
	}

	public Group findByName(String name) {
		return groupDao.findByName(name);
	}
}
