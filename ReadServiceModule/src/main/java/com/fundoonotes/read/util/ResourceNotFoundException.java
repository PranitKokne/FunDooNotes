package com.fundoonotes.read.util;

/**
 * Purpose: The class is used to send the custom exception to user in case if
 * the resource is not present.
 * 
 * @author Pranit_Kokne
 * @version 1.0
 * @since 07-08-2018
 */
public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(String message) {
		super(message);
	}

}