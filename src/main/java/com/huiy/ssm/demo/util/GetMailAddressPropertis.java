package com.huiy.ssm.demo.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetMailAddressPropertis {
	 
	private  static Logger log = LoggerFactory.getLogger(GetMailAddressPropertis.class);
	 /**
	  * 读取属性文件
	  * @param ctTypeName
	  * @return
	  */
	 public static String readPropertis( ){
		 InputStream path =Thread.currentThread().
		       		getContextClassLoader().getResourceAsStream("mailSend.properties");//获取路径并转换成流
        Properties pro = new Properties();//属性集合对象 
		 try {
			 pro.load(path);
			 return pro.getProperty("key1mail");
		} catch (IOException e) {
			return "***@***.com";
		}finally{
			try {
				path.close();
			} catch (IOException e) {
				log.error("操作属性文件导演", e);
			}
		}
	 }
	 
	 
	 /**
	  * 读取安全扫描人员邮件
	  * @param ctTypeName
	  * @return
	  */
	 public static String[] readSSEmail( ){
		 InputStream path =Thread.currentThread().
		       		getContextClassLoader().getResourceAsStream("mailSend.properties");//获取路径并转换成流
        Properties pro = new Properties();//属性集合对象 
		 try {
			 pro.load(path);
			 String ssmails = pro.getProperty("ssmail");
			 return ssmails.split(",");
		} catch (IOException e) {
			return new String[]{"***@****.com","***@****.com"};
		}finally{
			try {
				path.close();
			} catch (IOException e) {
				log.error("操作属性文件导演", e);
			}
		}
	 }
	 
	 
	 
	 public static void main(String[] args) {
		 String[] ssmailArr = readSSEmail();
		 for(int i=0;i<ssmailArr.length;i++){
				System.out.println(ssmailArr[i]);
		 }
//		System.out.println(readSSEmail());
	}
}
