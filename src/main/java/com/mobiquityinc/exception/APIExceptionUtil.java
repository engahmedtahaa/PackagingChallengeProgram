package com.mobiquityinc.exception;



/**
 * This Class for wrap our APIException Class and Custom error code and custom messages
 * @author AhmedTaha
 *
 */
/**
 * @author AhmedTaha
 *
 */
public class APIExceptionUtil {

	public final static String GENERAL_EXCEPTION = "PACKAGINCHALLLENG-101";
	public final static String ACCESS_EXCEPTION = "PACKAGINCHALLLENG-102";
	public final static String IO_EXCEPTION = "PACKAGINCHALLLENG-103";
	public final static String INCORRECT_PARAMETER_EXCEPTION = "PACKAGINCHALLLENG-104";
	public final static String GENERAL_EXCEPTION_MSG = "General Error Please Try Valid Inputs";
	public final static String IO_EXCEPTION_MSG = "Error during opening File Please Check file Path";
	public final static String EXCEPTION_PARSING_MSG = "Error during parsing File please check file format";


	public APIExceptionUtil() {
		super();
	}

	/**
	 * This Method to wrap Throwable to APIexception
	 * Add Custom Error Code
	 * @param thr
	 * @return APIException
	 */
	public static final APIException wrap(Throwable thr) {
		return new APIException(APIExceptionUtil.GENERAL_EXCEPTION, thr.getMessage(), thr);
	}
}
