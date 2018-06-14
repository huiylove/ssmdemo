/**
 * 
 */
package com.huiy.ssm.frame.persist.generate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Administrator
 *
 */
public interface PoGenConf {
	
	public static String driverClass = "com.ibm.db2.jcc.DB2Driver";
	String connectionURL = "jdbc:db2://localhost:50000/sample2";
	String userId = "db2admin";
	String password = "db2";
	String basePackage = "com.huiy.ssm.demo.persist";
	//PO com.ccb.eabs.persist.po
	//DAO com.ccb.eabs.persist.dao
	//DAOImpl com.ccb.eabs.persist.dao.impl
	String rootPath = "d://classes";
	List<String> tablelist =  Arrays.asList(new String[]{
			"user_t"
	}); 

	

}
