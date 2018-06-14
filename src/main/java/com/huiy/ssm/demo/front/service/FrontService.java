package com.huiy.ssm.demo.front.service;

import com.huiy.ssm.demo.front.xmlModelMappding.request.UserReqMapping;
import com.huiy.ssm.demo.front.xmlModelMappding.response.UserResMapping;

/** 
 * 类功能描述
 * @author : yuanhui 
 * @date   : 2017年2月24日
 * @version 1.0
 *
 *
 */
public interface FrontService {
	public UserResMapping findUserBy(UserReqMapping req);
}
