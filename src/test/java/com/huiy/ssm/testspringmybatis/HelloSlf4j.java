package com.huiy.ssm.testspringmybatis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/** 
 * 类功能描述
 * @author : yuanhui 
 * @date   : 2016年12月13日
 * @version 1.0
 * slf4j功能测试
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner�? 
@ContextConfiguration("/spring-mybatis.xml")  
public class HelloSlf4j {
	private  Logger logger = LoggerFactory.getLogger(HelloSlf4j.class);
	
	@Test  
	public void testSlf4j() { 
		logger.debug("This is debug message");
        logger.info("This is info message");
        logger.warn("This is warn message");
        logger.error("This is error message");
	}

}
