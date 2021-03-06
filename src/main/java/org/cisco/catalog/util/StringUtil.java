package org.cisco.catalog.util;

import org.apache.commons.lang3.StringUtils;

public class StringUtil {

	public static boolean isEmptyTrim(String val) {
		return val == null ? true : isEmpty(val.trim());
	}

	private static boolean isEmpty(String val) {
		return val == null ? true : val.isEmpty();
	}

	public static String cleanEmptyParagraphs(String val) {
		val = StringUtils.replace(val, "<p></p>", "");
		val = StringUtils.replace(val, "\r\n", "");
		return StringUtils.replace(val, "<p><br></p>", "");
	}

	public static String get(String str) {
		return isEmptyTrim(str) ? "" : str.trim();
	}
}
