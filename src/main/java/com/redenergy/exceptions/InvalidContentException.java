package com.redenergy.exceptions;


/**
 * Checked exception 
 * 
 * @author moj
 *
 */
public class InvalidContentException extends Exception {

	private static final long serialVersionUID = -3435198333019655439L;

	public InvalidContentException(String message) {
		super(message);
	}

	public InvalidContentException(String message, Throwable cause) {
		super(message, cause);
	}

}
