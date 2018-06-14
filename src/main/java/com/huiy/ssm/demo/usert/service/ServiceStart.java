package com.huiy.ssm.demo.usert.service;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/** 
 * 类功能描述
 * @author : yuanhui 
 * @date   : 2017年8月10日
 * @version 1.0
 *
 *
 */
public class ServiceStart {
	public static void main(String[] args){
		ClassPathXmlApplicationContext context =  new ClassPathXmlApplicationContext("spring-mybatis.xml","spring-dubbo-server.xml");
//		context.start();
		System.out.println("start ssm dubbo");
        while (true) {
            Thread.yield();
        }
        
	}
}
