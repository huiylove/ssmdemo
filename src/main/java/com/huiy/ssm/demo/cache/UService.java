package com.huiy.ssm.demo.cache;

import javax.annotation.Resource;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.huiy.ssm.demo.persist.dao.UserTDao;
import com.huiy.ssm.demo.persist.po.UserTPO;

/** 
 * 类功能描述
 * @author : yuanhui 
 * @date   : 2017年8月4日
 * @version 1.0
 *
 *
 */
@Component("uService")
public class UService {
	
	 @Resource  
	 private UserTDao userTDao; 

	@Cacheable(value="userCache",key="#id")
	public UserTPO queryById(String id) {
		System.out.println("real query user."+id); 
		UserTPO po = new UserTPO();
		po.setId(id);
		return userTDao.query(po);
	}
	
	@CacheEvict(value="userCache",key="#id")
	public void removeUser(String id){
		UserTPO po = new UserTPO();
		po.setId(id);
		userTDao.delete(po);
	}
	
	@CachePut(value="userCache",key="#upo.getId()")
	public void updateUser(UserTPO upo){
		System.out.println("real update user."+upo.getId()); 
		userTDao.update(upo);
	}
}
