package com.huiy.ssm.demo.front.xmlModelMappding.response;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/** 
 * 类功能描述
 * @author : yuanhui 
 * @date   : 2017年2月10日
 * @version 1.0
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Rsp")
public class UserInfoResMapping extends BaseRes{
	
	@XmlElement(name = "count")
	private String count;	//总记录数
	
	@XmlElement(name = "orderList")
	private List<UserResMapping> orderList;  //交易明细
}
