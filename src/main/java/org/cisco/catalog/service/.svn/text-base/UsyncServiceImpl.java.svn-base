package org.cisco.catalog.service;

import java.util.List;
import org.cisco.catalog.dao.UsyncDao;
import org.cisco.catalog.domain.Usync;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UsyncServiceImpl implements UsyncService {

	@Autowired
	UsyncDao usyncDao;

	public long countAllUsyncs() {
		return usyncDao.count();
	}

	public void deleteUsync(Usync usync) {
		usyncDao.delete(usync);
	}

	public Usync findUsync(Integer id) {
		return usyncDao.findOne(id);
	}

	public List<Usync> findAllUsyncs() {
		return usyncDao.findAll();
	}

	public void saveUsync(Usync usync) {
		usyncDao.save(usync);
	}

	public Usync updateUsync(Usync usync) {
		return usyncDao.update(usync);
	}

	public Usync findByUuid(String uuid) {
		return usyncDao.findByUuid(uuid);
	}
}
