/**
 * 
 */
package com.huiy.ssm.frame.persist.po;

import java.io.Serializable;

import net.sf.json.JSONObject;

/**
 * @author user
 *
 */
public class BasePO implements IPagePO, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 672256781985752657L;
	
	
	
	

	@Override
	public void setRownumber(String rownumber) {
		// TODO Auto-generated method stub
		
	}
	
	
	public String toJsonString(){
		JSONObject json = JSONObject.fromObject(this);
		return json.toString();
	}

}
