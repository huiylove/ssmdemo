/**
 * 创建于: 2015年3月23日 下午2:33:50<br>
 * 所属项目:SSPT
 * 文件名称:CommonMessageException.java
 * 作者:颜曦
 * 版权信息:
 */
package com.huiy.ssm.frame.core.exception;

/**
 * 类功能描述
 * CommonMessageException.java
 *
 * @history 2015年3月23日 ** 创建CommonMessageException.java
 * 
 * @author **
 * @version 0.1.0
 */
public class CommonMessageException extends RuntimeException {

	/**
	 * @fields serialVersionUID 
	 */
	private static final long serialVersionUID = 4081863331832266720L;

	public CommonMessageException() {
		super();
	}

	public CommonMessageException(String message) {
		super(message);
	}

	public CommonMessageException(Throwable cause) {
		super(cause);
	}


}
