/**
 * 
 */
package com.huiy.ssm.frame.core.dao;

import com.huiy.ssm.frame.persist.vo.PaginationSupport;


/**
 * @author user
 *
 */
public interface MyBatisSupportDao {

	<E>  PaginationSupport<E> findPaginatedResult(String id, Object parameterObject, int pageNum, int maxRows);

}
