package org.cisco.catalog.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateUtil {

	public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

	public static Date getPSTDate(Date datetime) {
		TimeZone tz = Calendar.getInstance().getTimeZone();
		if (!tz.getDisplayName().equals("PST")) {
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
			sdf.setTimeZone(TimeZone.getTimeZone("PST"));
			String dateStr = sdf.format(datetime);
			sdf.setTimeZone(tz);
			try {
				datetime = sdf.parse(dateStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return datetime;
	}

	public static boolean isValidDate(String datetime) {
		return true;
	}

	public static Calendar getPSTDate() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getPSTDate(new Date()));
		return cal;
	}

	public static Calendar getPSTDate(Calendar cal) {
		cal.setTime(getPSTDate(cal.getTime()));
		return cal;
	}
	
	/*
	 * public static void main(String[] a) { System.out.println(getPSTDate(new
	 * Date())); System.out.println(getPSTDate().getTime()); }
	 */
}
