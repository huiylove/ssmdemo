package com.huiy.ssm.demo.usert.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.huiy.ssm.demo.front.service.ConvertorService;
import com.huiy.ssm.demo.front.service.FrontService;
import com.huiy.ssm.demo.front.xmlModelMappding.request.UserReqMapping;
import com.huiy.ssm.demo.front.xmlModelMappding.response.UserResMapping;
import com.huiy.ssm.demo.persist.dao.UserTDao;
import com.huiy.ssm.demo.persist.po.UserTPO;
import com.huiy.ssm.demo.usert.service.UserTService;
import com.huiy.ssm.frame.core.exception.CommonException;
import com.huiy.ssm.frame.persist.vo.PaginationSupport;

/** 
 * 类功能描�?
 * @author : yuanhui 
 * @date   : 2016�?2�?�?
 * @version 1.0
 *
 *
 */

@Service("userService")  
public class UserTServiceImpl implements UserTService{
  
    @Resource  
    private UserTDao userTDao;  
    
	@Resource(name = "frontService")
	private FrontService frontService;
	
    @Resource(name = "convertorService")
	private ConvertorService ConvertorService;
    
	@Override
	public UserTPO queryById(String id) {
		UserTPO po = new UserTPO();
		po.setId(id);
		return userTDao.query(po);
	}

	@Override
	public UserTPO findUserBy(UserTPO tp) {
		UserTPO po = new UserTPO();
		UserReqMapping req = new UserReqMapping();
		req.setId(po.getId());
		UserResMapping ures = null;
		try {
			ures = frontService.findUserBy(req);
			po =  ConvertorService.copyProperties(ures, UserTPO.class);
		} catch (Exception e) {
			throw new CommonException("查询用户数据有误",e);
		}
		return po;
	}

	@Override
	public PaginationSupport<UserTPO> queryPage(UserTPO po, int pageNum,
			int maxRows) {
		return userTDao.queryPage(po, pageNum, maxRows);
	}

	@Override
	public List<UserTPO> queryList(UserTPO po) {
		return userTDao.queryList(po);
	}

}
