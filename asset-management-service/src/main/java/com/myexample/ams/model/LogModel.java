package com.myexample.ams.model;

import java.util.Date;

public class LogModel {

	private Date dateTime;
	private String log;

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public String getLog() {
		return log;
	}

	public void setLog(String log) {
		this.log = log;
	}

	@Override
	public String toString() {
		return "LogModel [dateTime=" + dateTime + ", log=" + log + "]";
	}

}
