package org.cisco.catalog.domain.json;

import java.text.ParseException;
import java.util.Date;

import org.cisco.catalog.util.DateUtil;

public class BaseDto implements JSONDto {

	private Date datetime = new Date();

	public Date getDatetime() throws ParseException {
		return DateUtil.getPSTDate(datetime);
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}
}
