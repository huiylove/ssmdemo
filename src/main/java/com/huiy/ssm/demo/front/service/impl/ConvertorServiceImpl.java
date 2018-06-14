package com.huiy.ssm.demo.front.service.impl;

import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.huiy.ssm.demo.front.service.ConvertorService;

/**
 *  XML 与 java bean 转换器
 * @author user
 *
 */
@Component("convertorService")
public class ConvertorServiceImpl implements ConvertorService {

	private static Logger logger = LoggerFactory.getLogger(ConvertorServiceImpl.class);
	
	/**
	 * java bean 转换成  xml 字符串
	 * 默认字符编码  UTF-8
	 * @param obj
	 * @return
	 */
	@Override
	public String convertToXml(Object obj) {
		 return convertToXml(obj, true);
	}
	
	/**
	 * java bean 转换成  xml 字符串
	 * 默认字符编码  UTF-8
	 * @param obj
	 * @return
	 */
	@Override
	public String convertToXml(Object obj, boolean format) {
		 return convertToXml(obj, "UTF-8", format);
	}

	/**
	 * java bean 转换成 xml 字符串
	 * @param obj
	 * @param encoding
	 * @return
	 */
	@Override
	public String convertToXml(Object obj, String encoding, boolean format) {
		String result = null;
		try {
			JAXBContext context = JAXBContext.newInstance(obj.getClass());
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, format);
			marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);
			StringWriter writer = new StringWriter();
			marshaller.marshal(obj, writer);
			result = writer.toString();
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
		return result;
	}

	/**
	 * 把 xml 字符串转换成 java bean
	 * 默认字符编码 UTF-8
	 * @param xml
	 * @param c
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> T converyToJavaBean(String xml, Class<T> c) {
		T t = null;
		try {
			JAXBContext context = JAXBContext.newInstance(c);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			t = (T) unmarshaller.unmarshal(new StringReader(xml));
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return t;
	}

	/**
	 * 把 xml 字符串转换成 java bean
	 * @param xml
	 * @param c
	 * @param encoding
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> T converyToJavaBean(String xml, Class<T> c, String encoding) {
		T t = null;
		try {
			JAXBContext context = JAXBContext.newInstance(c);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			t = (T) unmarshaller.unmarshal(new StringReader(new String(xml.getBytes(), encoding)));
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return t;
	}

	/**
	 * 把obj的属性值拷贝到T的同名属性下，并返回T的实例
	 * 支持同名不同类型间转换，但不支持java.util.Date
	 * @param obj
	 * @param c
	 * @return
	 */
	public <T> T copyProperties(Object obj, Class<T> c) {
		return copyPropertiesSimple(obj, c, false);
	}
	
	/**
	 * 把obj的属性值拷贝到T的同名属性下，并返回T的实例
	 * ignoreProperties指定的属性对应的值不做拷贝
	 * 不支持同名不同类型间转换，但支持java.util.Date
	 * @param obj
	 * @param c
	 * @return
	 */
	public <T> T copyProperties(Object obj, Class<T> c, String...ignoreProperties) {
		T instance = null;
		try {
			instance = c.newInstance();
		} catch (InstantiationException e) {
			logger.error(e.getMessage());
			return instance;
		} catch (IllegalAccessException e) {
			logger.error(e.getMessage());
			return instance;
		}
		
		org.springframework.beans.BeanUtils.copyProperties(obj, instance, ignoreProperties);
		
		return instance;
	}
	
	/**
	 * 把obj的属性值拷贝到T的同名属性下，并返回T的实例
	 * simple 为true，仅仅拷贝同名属性值，不支持同名类型转换，但支持java.util.Data类型
	 * simple 为false, 支持同名不同类型间的值拷贝和类型转换
	 * 但不支持java.util.Date
	 * @param obj
	 * @param c
	 * @return
	 */
	public <T> T copyPropertiesSimple(Object obj, Class<T> c, boolean simple) {
		T instance = null;
		try {
			instance = c.newInstance();
		} catch (InstantiationException e) {
			logger.error(e.getMessage());
			return instance;
		} catch (IllegalAccessException e) {
			logger.error(e.getMessage());
			return instance;
		}
		
		if (simple) {
			org.springframework.beans.BeanUtils.copyProperties(obj, instance);
		} else {
			try {
				org.apache.commons.beanutils.BeanUtils.copyProperties(instance, obj);
			} catch (IllegalAccessException e) {
				logger.error(e.getMessage());
				return instance;
			} catch (InvocationTargetException e) {
				logger.error(e.getMessage());
				return instance;
			}
		}
		
		return instance;
	}
	
}
