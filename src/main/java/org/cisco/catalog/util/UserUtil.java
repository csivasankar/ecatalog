package org.cisco.catalog.util;

import java.util.Map;
import java.util.TreeMap;

import org.cisco.catalog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserUtil {

	private static Map<String, String> userMap = new TreeMap<String, String>();

	@Autowired
	UserService userService;
	
	public boolean isValidUser(String authUser) {
		if(!userMap.containsKey(authUser)) {
			if(userService.findByUsername(authUser) != null) {
				userMap.put(authUser, authUser);
			}
		}
		return userMap.containsKey(authUser);
	}
	
	public void deleteUser(String authUser) {
		if(userMap.containsKey(authUser)) {
			userMap.remove(authUser);
		}
	}
}
