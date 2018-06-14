package com.huiy.ssm.testspringmybatis;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.huiy.ssm.demo.cache.UService;
import com.huiy.ssm.demo.persist.po.UserTPO;

/** 
 * 类功能描述
 * @author : yuanhui 
 * @date   : 2017年8月4日
 * @version 1.0
 *
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner�? 
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"}) 
public class TestCache {
	
	private static Logger logger = Logger.getLogger(TestCache.class);  
//    
    @Resource  
    private UService uService;

	@Test
	public void test() {
		 // 第一次查询，应该走数据库
 	    logger.info("first query..."); 
	    uService.queryById("2");
	    // 第二次查询，应该不查数据库，直接返回缓存的值
	    logger.info("second query..."); 
	    UserTPO upo = uService.queryById("2");
	    upo.setAge("28");
	    logger.info("query user id..."+upo.getId()); 
	 // 开始更新其中一个    account1.setId(1212);
//	    uService.removeUser("1");
	    uService.updateUser(upo);
	    logger.info("query user AGE..."+ uService.queryById("2").getAge()); 

	}

}
