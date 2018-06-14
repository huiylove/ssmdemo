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
public class UserResMapping extends BaseRes{
	
	@XmlElement(name = "id")
	private String id;	//总记录数
	
	@XmlElement(name = "userName")
	private String userName;	//用户姓名
	
	@XmlElement(name = "age")
	private String age;	//用户年龄
	
	@XmlElement(name = "password")
	private String password;	//用户密码

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
