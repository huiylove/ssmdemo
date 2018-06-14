package com.huiy.ssm.demo.persist.dao;

import java.util.List;

import com.huiy.ssm.demo.persist.po.UserTPO;
import com.huiy.ssm.frame.persist.vo.PaginationSupport;

public interface UserTDao {

	public UserTPO query(UserTPO po);

	public List<UserTPO> queryList(UserTPO po);

	public PaginationSupport<UserTPO> queryPage(UserTPO po, int pageNum, int maxRows);

	public int insert(UserTPO po);

	public int update(UserTPO po);

	public int delete(UserTPO po);
	
	public int queryCount(int age);
}
