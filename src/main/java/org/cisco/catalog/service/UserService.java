package org.cisco.catalog.service;

import java.util.List;
import org.cisco.catalog.domain.User;

public interface UserService {

	public abstract long countAllUsers();

	public abstract void deleteUser(User user);

	public abstract User findUser(Integer id);
	
	public abstract User findByUsername(String name);

	public abstract List<User> findAllUsers();

	public abstract void saveUser(User user);

	public abstract User updateUser(User user);

}
