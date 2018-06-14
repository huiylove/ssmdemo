package com.huiy.ssm.demo.socket.netty;
/** 
 * 类功能描述
 * @author : yuanhui 
 * @date   : 2018年4月19日
 * @version 1.0
 */
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;


public enum Calculator {
	Instance;
	private final static ScriptEngine jse = new ScriptEngineManager().getEngineByName("JavaScript");
	public Object cal(String expression) throws ScriptException{
	return jse.eval(expression);
}
}
