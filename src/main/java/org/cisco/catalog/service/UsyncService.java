package org.cisco.catalog.service;

import java.util.List;
import org.cisco.catalog.domain.Usync;

public interface UsyncService {

	public abstract long countAllUsyncs();

	public abstract void deleteUsync(Usync usync);

	public abstract Usync findUsync(Integer id);

	public abstract Usync findByUuid(String uuid);
	
	public abstract List<Usync> findAllUsyncs();

	public abstract void saveUsync(Usync usync);

	public abstract Usync updateUsync(Usync usync);

}
