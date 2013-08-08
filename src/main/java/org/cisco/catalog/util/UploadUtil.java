package org.cisco.catalog.util;

import org.springframework.stereotype.Component;

@Component
public class UploadUtil {

	private String uploadPath = System.getProperty("com.ibm.websphere.servlet.temp.dir");

	public String getUploadPath() {
		if (!uploadPath.endsWith("/")) {
			return uploadPath + "/";
		}
		return uploadPath;
	}
}
