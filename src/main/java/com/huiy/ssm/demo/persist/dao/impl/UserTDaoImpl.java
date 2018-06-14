package com.huiy.ssm.demo.persist.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.huiy.ssm.demo.persist.dao.UserTDao;
import com.huiy.ssm.demo.persist.po.UserTPO;
import com.huiy.ssm.frame.core.dao.impl.MyBatisSupportDaoImpl;
import com.huiy.ssm.frame.persist.vo.PaginationSupport;

@Component("userTDao")
public class UserTDaoImpl extends MyBatisSupportDaoImpl implements UserTDao {
	
	public UserTPO query(UserTPO po) {
		return getSqlSession().selectOne("UserT.query", po);
	}

	public List<UserTPO> queryList(UserTPO po) {
		return getSqlSession().selectList("UserT.queryList", po);
	}

	public PaginationSupport<UserTPO> queryPage(UserTPO po, int pageNum, int maxRows) {
		return findPaginatedResult("UserT.queryPage", po, pageNum, maxRows);
	}

	public int insert(UserTPO po) {
		return getSqlSession().insert("UserT.insert", po);
	}

	public int update(UserTPO po) {
		return getSqlSession().update("UserT.update", po);
	}

	public int delete(UserTPO po) {
		return getSqlSession().delete("UserT.delete", po);
	}
	
	public int queryCount(int age){
		return getSqlSession().selectOne("UserT.queryCount", age);
	}
}
