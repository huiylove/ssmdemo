package com.huiy.ssm.testspringmybatis;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.huiy.ssm.demo.persist.dao.UserTDao;
import com.huiy.ssm.demo.persist.po.UserTPO;
import com.huiy.ssm.demo.usert.service.UserTService;


@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner�? 
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})  
public class TestMyBatis {  
	
    private static Logger logger = Logger.getLogger(TestMyBatis.class);  
//  private ApplicationContext ac = null;  
//    @Resource  
//    private UserTService userService = null;  
    @Resource  
    private UserTDao userTDao;  
    
//  @Before  
//  public void before() {  
//      ac = new ClassPathXmlApplicationContext("applicationContext.xml");  
//      userService = (IUserService) ac.getBean("userService");  
//  }  
  
    @Test
    public void test1() {  
//        UserTPO user = userService.queryById("1");
    	  
        // System.out.println(user.getUserName());  
        // logger.info("值："+user.getUserName());  
//          logger.info(JSON.toJSONString(user));    
//          logger.debug(JSON.toJSONString(user));
//        logger.error(JSON.toJSONString(user));
    	 logger.info("dddddddddwsssss"+userTDao.queryCount(24));
           
    }  
}  