/**
 * 
 */
package com.huiy.ssm.frame.persist.vo;

import java.util.Collection;

import com.github.miemiedev.mybatis.paginator.domain.Paginator;

/**
 * @author user
 *
 */
public class PaginationSupport<E> {
	   /**
	    * 分页返回参数	
	    */
	   private Paginator paginator;
	   
	   /**
	    * 分页数据对象
	    */
	   private Collection<E> list;
	   
	   
	   

	public PaginationSupport(Paginator paginator, Collection<E> list) {
		super();
		this.paginator = paginator;
		this.list = list;
	}

	public Paginator getPaginator() {
		return paginator;
	}

	public void setPaginator(Paginator paginator) {
		this.paginator = paginator;
	}

	public Collection<E> getList() {
		return list;
	}

	public void setList(Collection<E> list) {
		this.list = list;
	}
	   
	   
	   

}
