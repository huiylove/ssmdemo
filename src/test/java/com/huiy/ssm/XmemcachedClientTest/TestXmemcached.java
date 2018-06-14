package com.huiy.ssm.XmemcachedClientTest;

import java.util.concurrent.TimeoutException;

import javax.annotation.Resource;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner�? 
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml","classpath:spring-xmemcached.xml"})  
public class TestXmemcached {  
	
    private static Logger logger = Logger.getLogger(TestXmemcached.class);  

    @Resource  
    private MemcachedClient memcachedClient;  
    
//  @Before  
//  public void before() {  
//      ac = new ClassPathXmlApplicationContext("applicationContext.xml");  
//      userService = (IUserService) ac.getBean("userService");  
//  }  
  
    @Test
    public void test() {  
    	 try {
			memcachedClient.set("openid",0,"yuanhui");
	    	logger.info("memcached-value-----"+memcachedClient.get("openid"));
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MemcachedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
           
    }  
}  