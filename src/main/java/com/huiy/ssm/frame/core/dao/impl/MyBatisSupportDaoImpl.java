/**
 * 
 */
package com.huiy.ssm.frame.core.dao.impl;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.huiy.ssm.frame.core.dao.MyBatisSupportDao;
import com.huiy.ssm.frame.persist.vo.PaginationSupport;

/**
 * @author user
 *
 */
public abstract class MyBatisSupportDaoImpl extends SqlSessionDaoSupport implements MyBatisSupportDao {
	
	@SuppressWarnings("unchecked")
	@Override
	public <E> PaginationSupport<E> findPaginatedResult(String id, Object parameterObject, int pageNum, int maxRows) {
		PageBounds page = new PageBounds(pageNum, maxRows);
		PageList<E> pageList =  (PageList<E>) 		this.getSqlSession().selectList(id, parameterObject, page);
		return new PaginationSupport<E>(pageList.getPaginator(),pageList);
	}
	
	 @Autowired
	 public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
	     super.setSqlSessionFactory(sqlSessionFactory);
	 }
	 
	 
}
