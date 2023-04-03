/**
 * 
 */
package com.rewards.controller.exception;

/**
 * @author bappana
 *
 */
public class CustomerNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7804240985024930403L;
	private String message;

	public CustomerNotFoundException(String message) {
		this.setMessage(message);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
