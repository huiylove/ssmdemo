package com.huiy.ssm.demo.front.service;

/**
 *  XML 与 java bean 转换器
 * @author user
 *
 */
public interface ConvertorService {

	/**
	 * java bean 转换成  xml 字符串
	 * 默认字符编码  UTF-8
	 * @param obj
	 * @return
	 */
	public String convertToXml(Object obj);
	
	/**
	 * java bean 转换成  xml 字符串
	 * 默认字符编码  UTF-8
	 * @param obj
	 * @return
	 */
	public String convertToXml(Object obj, boolean format);
	
	/**
	 * java bean 转换成 xml 字符串
	 * @param obj
	 * @param encoding
	 * @return
	 */
	public String convertToXml(Object obj, String encoding, boolean format);
	
	/**
	 * 把 xml 字符串转换成 java bean
	 * 默认字符编码 UTF-8
	 * @param xml
	 * @param c
	 * @return
	 */
	public <T> T converyToJavaBean(String xml, Class<T> c);
	
	/**
	 * 把 xml 字符串转换成 java bean
	 * @param xml
	 * @param c
	 * @param encoding
	 * @return
	 */
	public <T> T converyToJavaBean(String xml, Class<T> c, String encoding);
	
	/**
	 * 把obj的属性值拷贝到T的同名属性下，并返回T的实例
	 * 支持同名不同类型间转换，但不支持java.util.Date
	 * @param obj
	 * @param c
	 * @return
	 */
	public <T> T copyProperties(Object obj, Class<T> c);
	
	/**
	 * 把obj的属性值拷贝到T的同名属性下，并返回T的实例
	 * ignoreProperties指定的属性对应的值不做拷贝
	 * 支持同名不同类型间转换，但不支持java.util.Date
	 * @param obj
	 * @param c
	 * @return
	 */
	public <T> T copyProperties(Object obj, Class<T> c, String...ignoreProperties);
	
	/**
	 * 把obj的属性值拷贝到T的同名属性下，并返回T的实例
	 * simple 为true，仅仅拷贝同名属性值，不支持同名类型转换
	 * simple 为false, 支持同名不同类型间的值拷贝和类型转换
	 * 但不支持java.util.Date
	 * @param obj
	 * @param c
	 * @return
	 */
	public <T> T copyPropertiesSimple(Object obj, Class<T> c, boolean simple);
	
}
