package com.mobiquityinc.exception;

import org.apache.commons.lang.exception.ExceptionUtils;



/**
 * This our Wrapper Exception Object Which Extends {@link} Exception
 * Any exception occurs will be thrown As APIException such like validation errors and so on
 * @author AhmedTaha
 */
public class APIException extends Exception {

	private static final long serialVersionUID = 1L;

	protected String code;
	protected String message;
	protected String rootCause;

	public APIException(String code, String message, Throwable thr) {
		super(thr);
		this.message = message;
		this.code = code;
		this.rootCause = ExceptionUtils.getStackTrace(thr);
	}

	public APIException(String code, String message) {

		this.message = message;
		this.code = code;
	}

	public APIException(APIException e) {
		super(e);
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setRootCause(String rootCause) {
		this.rootCause = rootCause;
	}

	public String getRootCause() {
		return rootCause;
	}
}
