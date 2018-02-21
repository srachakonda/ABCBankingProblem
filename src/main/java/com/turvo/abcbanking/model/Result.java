package com.turvo.abcbanking.model;

import java.io.Serializable;

/**
 * @author <a href="sampath.rachakonda@imaginea.com">srachakonda</a>
 * @version $Revision: 1.0$, $Date: 20-Feb-2018
 */
public class Result implements Serializable {

	private static final long serialVersionUID = 1L;

	private String status;

	private String message;
	
	private String error;

	private Object data;
	
	public Result() {
		
	}

	public Result(String status, String message, Object data) {
		this.status = status;
		this.message = message;
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
