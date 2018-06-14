package com.huiy.ssm.testspringmybatis;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/** 
 * 类功能描述
 * @author : yuanhui 
 * @date   : 2017年8月4日
 * @version 1.0
 *
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner�? 
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml","classpath:spring-dubbo-server.xml"}) 
public class TestDubboServer {
	
	private static Logger logger = Logger.getLogger(TestDubboServer.class);  
	

	@Test
	public void test() {
		logger.warn("dubbo two step success!!!!!!");
        while (true) {
            Thread.yield();
        }
	}
}
