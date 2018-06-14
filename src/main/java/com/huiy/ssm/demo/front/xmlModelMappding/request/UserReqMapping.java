package com.huiy.ssm.demo.front.xmlModelMappding.request;

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
@XmlRootElement(name = "Req")
public class UserReqMapping extends BaseReq{
	
	@XmlElement(name = "id")
	private String id;	
	


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
}
