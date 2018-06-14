package com.huiy.ssm.frame.core.web.filter;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ibm.db2.jcc.am.re;

/** 
 * 类功能描述
 * @author : yuanhui 
 * @date   : 2017年3月3日
 * @version 1.0
 *
 *
 */
public class LoginFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		System.out.println("过滤前----");
		// 转换为HttpServletRequest和HttpServletResponse
		HttpServletRequest request = (HttpServletRequest) req;
	    HttpServletResponse response = (HttpServletResponse) res;
//		MemcachedClientBuilder builder = new XMemcachedClientBuilder( 
//	               AddrUtil.getAddresses ("localhost:11211")); 
//		MemcachedClient memcachedClient = builder.build(); 
//	       try{ 
//	           memcachedClient.set( "hello",0,"Hello,xmemcached"); 
//	           String value = memcachedClient.get ( "hello" ); 
//	           System. out.println( "hello="+ value); 
//	  
//	           memcachedClient. delete ( "hello"); 
//	           value = memcachedClient.get("hello" ); 
//	           System. out.println( "hello="+ value); 
//	  
//	       } catch(MemcachedException e) { 
//	           System. err.println( "MemcachedClient operation fail"); 
//	           e.printStackTrace(); 
//	       } catch(TimeoutException e) { 
//	           System. err.println( "MemcachedClient operation timeout"); 
//	           e.printStackTrace(); 
//	       } catch(InterruptedException e) { 
//	           // ignore 
//	       } 
//	       try{ 
//	           memcachedClient.shutdown(); 
//	       } catch(IOException e) { 
//	           System. err.println( "Shutdown MemcachedClient fail"); 
//	           e.printStackTrace(); 
//	       } 
//		//获取spring context
//    	WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext()); 
//    	//获取memcachedClient缓存对象
//    	MemcachedClient  memcachedClient = (MemcachedClient) webApplicationContext.getBean("memcachedClient");
//    	Cookie cookie = new Cookie("token","hello");
//    	response.addCookie(cookie);
//		Cookie[] cookies = request.getCookies();
//    	try {
////		memcachedClient.set( "hello",0,"Hello,xmemcached"); 
//			String value = memcachedClient.get ( "hello" ); 
//			System. out.println( "hello="+ value);
//		} catch (TimeoutException e) {
//			e.printStackTrace();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		} catch (MemcachedException e) {
//			e.printStackTrace();
//		} 
		chain.doFilter(request, response);//调用Servlet的doService()方法是在chain.doFilter(request, response)这个方法中进行的
		System.out.println("过滤后----");
	}

	@Override
	public void destroy() {
		
	}

}
