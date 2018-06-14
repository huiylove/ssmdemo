package com.huiy.ssm.demo.front.xmlModelMappding.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class BaseRes {

	@XmlElement(name = "rcd")
	private String rcd;	//响应码
	
	@XmlElement(name = "rDesc")
	private String rDesc;	//响应描述
	
	public String getRcd() {
		return rcd;
	}

	public void setRcd(String rcd) {
		this.rcd = rcd;
	}
	
	public String getrDesc() {
		return rDesc;
	}

	public void setrDesc(String rDesc) {
		this.rDesc = rDesc;
	}
	
}
