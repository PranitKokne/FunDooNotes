package com.fundoonotes.read.model;

import java.util.Date;

/**
 * Purpose: The class consist of fields which are used to send the error
 * response to the user.
 * 
 * @author Pranit_Kokne
 * @version 1.0
 * @since 07-08-2018
 */
public class ErrorDetails {

	private Date timeStamp;
	private String message;
	private String details;

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public ErrorDetails(Date timeStamp, String message, String details) {
		super();
		this.timeStamp = timeStamp;
		this.message = message;
		this.details = details;
	}

}
