package com.huiy.ssm.demo.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** 
 * 类功能描述
 * @author : yuanhui 
 * @date   : 2017年7月21日
 * @version 1.0
 *
 *
 */
public class KeyWordFilter {
	private static Pattern pattern = null;
	
    public static void initPattern() {
		StringBuffer patternBuf = new StringBuffer("");
		try {
			InputStream path =Thread.currentThread().
		       		getContextClassLoader().getResourceAsStream("words.properties");//获取路径并转换成流
			Properties pro = new Properties();//属性集合对象 
			pro.load(path);
			Enumeration enu = pro.propertyNames();
			patternBuf.append("(");
			while (enu.hasMoreElements()) {
				patternBuf.append((String) enu.nextElement() + "|");
			}
			patternBuf.deleteCharAt(patternBuf.length() - 1);
			patternBuf.append(")");
		     pattern = Pattern.compile(new String(patternBuf.toString()
			 .getBytes("ISO-8859-1"),"UTF-8"));
			} catch (IOException ioEx) {
				ioEx.printStackTrace();
			}
    }
    
    public static boolean doFilter(String str) {
    	Matcher m = pattern.matcher(str);
    	while (m.find())//查找符合pattern的字符串  
    	{
             return true;
    	}  
    	return false;
    }
    
    public static void main(String[] args) throws IOException{
//    	KeyWordFilter.initPattern();
//    	System.out.println(KeyWordFilter.doFilter("新增hhhhhh新增"));
    	 
    }
}
