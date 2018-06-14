package com.huiy.ssm.demo.usert.service;

import java.util.List;

import com.huiy.ssm.demo.persist.po.UserTPO;
import com.huiy.ssm.frame.persist.vo.PaginationSupport;

/** 
 * 类功能描�?
 * @author : yuanhui 
 * @date   : 2016�?2�?�?
 * @version 1.0
 *
 *
 */
public interface UserTService {
	
	public UserTPO queryById(String id);
	
	public UserTPO findUserBy(UserTPO tp);
	
	public List<UserTPO> queryList(UserTPO po);
	
	public PaginationSupport<UserTPO> queryPage(UserTPO po,int pageNum, int maxRows);
	
}

