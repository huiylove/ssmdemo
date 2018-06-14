package com.huiy.ssm.frame.core.web.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.rubyeye.xmemcached.MemcachedClient;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/** 
 * 类功能描述
 * @author : yuanhui 
 * @date   : 2017年3月3日
 * @version 1.0
 *
 *
 */
public class AllInterceptor implements HandlerInterceptor{

	@Resource(name="memcachedClient")
	private MemcachedClient  memcachedClient ;
	
	/* 
	 * 在Controller执行之前，执行方法里面的内容，注意该方法是有返回值的，当方法返回false时整个请求就结束了
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
	     System.out.println("preHandle");
	     return true;
	}

	/*
	 * 在Controller执行之后，视图渲染之前执行方法里面的内容，也就是说postHandle方法可以对Model进行操作
	 */
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		 System.out.println("postHandle");
	}

	/* 
	 * 在整个视图渲染完毕之后执行方法里面的内容，主要用于释放一些资源
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		 System.out.println("afterCompletion");
	}

}
