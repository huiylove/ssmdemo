package com.huiy.ssm.frame.core.exception;

/**
 * 控制层异常信息
 * @author ***
 * @date 2014年3月7日下午1:52:47
 */
public class ControllerException extends RuntimeException {

	/**
	 * @fields serialVersionUID 
	 */
	private static final long serialVersionUID = 4081863331832266720L;

	public ControllerException() {
		super();
	}

	public ControllerException(String message, Throwable cause) {
		super(message, cause);
	}

	public ControllerException(String message) {
		super(message);
	}

	public ControllerException(Throwable cause) {
		super(cause);
	}

}
