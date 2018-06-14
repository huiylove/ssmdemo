package com.huiy.ssm.demo.usert.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/** 
 * 类功能描述
 * @author : yuanhui 
 * @date   : 2018年4月17日
 * @version 1.0
 */
//@Controller
//@RequestMapping("/weixin")

@RestController("/weixin")
public class UserTestController {
	
    private static final Logger logger  = LoggerFactory.getLogger(UserTestController.class);

	
	 @RequestMapping(method = RequestMethod.GET)
    //@ResponseBody
	 protected final String bind(HttpServletRequest request, HttpServletResponse response) throws IOException {
		 response.getWriter().write("getMethod");
		 return null;
	 }
	 
	 @RequestMapping(method = RequestMethod.POST)
	 //@ResponseBody
	 protected final String process(HttpServletRequest request) throws ServletException, IOException {
	 	logger.info("postMethod");   
	 	return "postMethod";
	 }

}
