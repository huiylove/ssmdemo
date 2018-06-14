package com.huiy.ssm.frame.core.exception;

/**
 * Dao层异常信息
 * @author ***
 * @date 2014年3月7日下午1:51:30
 */
public class DaoException extends RuntimeException {

	/**
	 * @fields serialVersionUID 
	 */
	private static final long serialVersionUID = 8350049272861703406L;

	public DaoException() {
		super();
	}

	public DaoException(String message, Throwable cause) {
		super(message, cause);
	}

	public DaoException(String message) {
		super(message);
	}

	public DaoException(Throwable cause) {
		super(cause);
	}

}
