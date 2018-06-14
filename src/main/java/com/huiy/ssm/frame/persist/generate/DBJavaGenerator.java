package com.huiy.ssm.frame.persist.generate;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

public class DBJavaGenerator {	
	public static String path = PoGenConf.rootPath;
	public static void main(String[] args) {
		JDBC jdbc = new JDBC();
	
		//指定表
		List<String> tables = PoGenConf.tablelist;
		//获取用户下所有的表
//		  tables = jdbc.queryAllTables();
			File file = new File(path);
			if(!file.exists())
				file.mkdirs();
		
		for (String table: tables) {
			//获取表中的字段信息
			List<TableInfo> info = jdbc.queryAllColumnsAboutTable(table.toUpperCase());
			String tableComment = jdbc.getTableComments(table);
			if(info.size() == 0){
				System.err.println(table + "生成错误");
				continue;
			}
			String newTable = GenerateUtil.getRightWordOfClass(table);
			
			createPO(newTable,tableComment, info,"./src/main/java/"+StringUtils.replace(PoGenConf.basePackage,".","//")+"/po/");
			createDBB(newTable,tableComment, "./src/main/java/"+StringUtils.replace(PoGenConf.basePackage,".","//")+"/dao/");		
			createDBBImpl(newTable,tableComment, "./src/main/java/"+StringUtils.replace(PoGenConf.basePackage,".","//")+"/dao/impl/");
			DBXmlGenerator.out();
		}		
	}	
	
	private static void createDBB(String table,String tableComment,String path){
		try {
			File menu = new File(path);
			if(!menu.exists()){
				menu.mkdirs();
			}
			File po = new File(path+table+"Dao.java");
			po.createNewFile();
			PrintWriter pw = new PrintWriter(po);
			pw.println("package "+PoGenConf.basePackage+".dao;");
			pw.println();
			pw.println("import java.util.List;");
			pw.println();
			pw.println("import "+PoGenConf.basePackage+".po."+table+"PO;");
			pw.println("import com.huiy.ssm.frame.persisit.vo.PaginationSupport;");
			pw.println();
			pw.println("public interface "+table+"Dao {");
			pw.println();
			pw.println("	public "+table+"PO query("+table+"PO po);");
			pw.println();
			pw.println("	public List<"+table+"PO> queryList("+table+"PO po);");
			pw.println();
			pw.println("	public PaginationSupport<"+table+"PO> queryPage("+table+"PO po, int pageNum, int maxRows);");
			pw.println();
			pw.println("	public int insert("+table+"PO po);");
			pw.println();
			pw.println("	public int update("+table+"PO po);");
			pw.println();
			pw.println("	public int delete("+table+"PO po);");			
			pw.println("}");
			
			pw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void createDBBImpl(String table,String tableComment,String path){

		try {
			File menu = new File(path);
			if(!menu.exists()){
				menu.mkdirs();
			}
			File po = new File(path+table+"DaoImpl.java");
			po.createNewFile();
			PrintWriter pw = new PrintWriter(po);
			pw.println("package "+PoGenConf.basePackage+".dao.impl;");
			pw.println();
			pw.println("import java.util.List;");
			pw.println();
			pw.println("import org.springframework.stereotype.Component;");
			pw.println();
			pw.println("import "+PoGenConf.basePackage+".dao."+table+"Dao;");
			pw.println("import "+PoGenConf.basePackage+".po."+table+"PO;");
			pw.println("import com.huiy.ssm.frame.core.dao.impl.MyBatisSupportDaoImpl;");
			pw.println("import com.huiy.ssm.frame.persisit.vo.PaginationSupport;");
			pw.println();
			
			
			pw.println("@Component(\""+table.substring(0,1).toLowerCase()+table.substring(1)+"Dao\")");
			pw.println("public class "+table+"DaoImpl extends MyBatisSupportDaoImpl implements "+table+"Dao {");
			pw.println("	public "+table+"PO query("+table+"PO po) {");
			pw.println("		return getSqlSession().selectOne(\""+table+".query\", po);");
			pw.println("	}");
			pw.println("");
			pw.println("	public List<"+table+"PO> queryList("+table+"PO po) {");
			pw.println("		return getSqlSession().selectList(\""+table+".queryList\", po);");
			pw.println("	}");
			pw.println("");
			pw.println("	public PaginationSupport<"+table+"PO> queryPage("+table+"PO po, int pageNum, int maxRows) {");
			pw.println("		return findPaginatedResult(\""+table+".queryPage\", po, pageNum, maxRows);");
			pw.println("	}");
			pw.println("");
			pw.println("	public int insert("+table+"PO po) {");
			pw.println("		return getSqlSession().insert(\""+table+".insert\", po);");
			pw.println("	}");
			pw.println("");
			pw.println("	public int update("+table+"PO po) {");
			pw.println("		return getSqlSession().update(\""+table+".update\", po);");
			pw.println("	}");
			pw.println("");
			pw.println("	public int delete("+table+"PO po) {");
			pw.println("		return getSqlSession().delete(\""+table+".delete\", po);");
			pw.println("	}");			
			pw.println("}");
			
			pw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	/**
	 * 
	 * @MethodName: createPO 
	 * @Description: 生成PO
	 * 步骤：
	 * 1、
	 * 2、
	 * @param table
	 * @param info 
	 * @return void 返回类型 
	 * @throws
	 * @remark
	 */
	private static void createPO(String table,String tableComment,List<TableInfo> info,String path){
		try {
			File menu = new File(path);
			if(!menu.exists()){
				menu.mkdirs();
			}
			File po = new File(path+table+"PO.java");
			po.createNewFile();
			PrintWriter pw = new PrintWriter(po);
			pw.println("package "+PoGenConf.basePackage+".po;");
			pw.println();
			pw.println("import com.huiy.ssm.frame.persisit.po.BasePO;");
			pw.println("/**");
			pw.println(" *" + tableComment + "  数据对象");
			pw.println(" */");
			pw.println("public class "+table+"PO extends BasePO {");
			pw.println();
			pw.println("	private static final long serialVersionUID = "+UUID.randomUUID().getMostSignificantBits()+"L;");
			pw.println();
			
			for (TableInfo tableInfo : info) {
				pw.println("	/**");
				pw.println("	 *" + tableInfo.getComment());
				pw.println("     */");
				String attribute = GenerateUtil.getRightWordOfAttribute(tableInfo.getColumn());
				if(tableInfo.getType().equals("CHAR") || tableInfo.getType().equals("VARCHAR2")){
					pw.println("	private java.lang.String "+attribute+";");
				}else if(tableInfo.getType().equals("NUMBER")){
					if(tableInfo.getDataPrecision() <= 10 && tableInfo.getDataScale() == 0){
						pw.println("	private java.lang.Integer "+attribute+";");
					}else if(tableInfo.getDataPrecision()> 10 && tableInfo.getDataScale() == 0){
						pw.println("	private java.lang.Long "+attribute+";");
					}else if(tableInfo.getDataScale() != 0){
						pw.println("	private java.math.BigDecimal "+attribute+";");
					}
				}else if(tableInfo.getType().equals("DATE")){
					pw.println("	private java.util.Date "+attribute+";");
				}else if(tableInfo.getType().equals("TIMESTAMP")){
					pw.println("	private java.util.Date "+attribute+";");
				}else{
					pw.println("	private java.lang.String "+attribute+";");
				}
				
				pw.println();
			}

			for (TableInfo tableInfo : info) {
				String attribute = GenerateUtil.getRightWordOfAttribute(tableInfo.getColumn());
				String attributeUpper = attribute.substring(0, 1).toUpperCase() + attribute.substring(1);
				
				if(tableInfo.getType().equals("CHAR") || tableInfo.getType().equals("VARCHAR2")){
					pw.println("	/**");
					pw.println("	 *设置 " + tableInfo.getComment());
					pw.println("     */");
					pw.println("	public void set"+attributeUpper+"(java.lang.String "+attribute+") {");
					pw.println("		this."+attribute+" = "+attribute+";");
					pw.println("	}");
					pw.println();
					pw.println("	/**");
					pw.println("	 *获取 " + tableInfo.getComment());
					pw.println("     */");
					pw.println("	public java.lang.String get"+attributeUpper+"() {");
					pw.println("		return "+attribute+";");
					pw.println("	}");					
				}else if(tableInfo.getType().equals("NUMBER")){
					if(tableInfo.getDataPrecision() <= 10 && tableInfo.getDataScale() == 0){
						pw.println("	/**");
						pw.println("	 *设置 " + tableInfo.getComment());
						pw.println("     */");
						pw.println("	public void set"+attributeUpper+"(java.lang.Integer "+attribute+") {");
						pw.println("		this."+attribute+" = "+attribute+";");
						pw.println("	}");
						pw.println();
						pw.println("	/**");
						pw.println("	 *获取 " + tableInfo.getComment());
						pw.println("     */");
						pw.println("	public java.lang.Integer get"+attributeUpper+"() {");
						pw.println("		return "+attribute+";");
						pw.println("	}");
					}else if(tableInfo.getDataPrecision()> 10 && tableInfo.getDataScale() == 0){
						pw.println("	/**");
						pw.println("	 *设置 " + tableInfo.getComment());
						pw.println("     */");
						pw.println("	public void set"+attributeUpper+"(java.lang.Long "+attribute+") {");
						pw.println("		this."+attribute+" = "+attribute+";");
						pw.println("	}");
						pw.println();
						pw.println("	/**");
						pw.println("	 *获取 " + tableInfo.getComment());
						pw.println("     */");
						pw.println("	public java.lang.Long get"+attributeUpper+"() {");
						pw.println("		return "+attribute+";");
						pw.println("	}");	
					}else if(tableInfo.getDataScale() != 0){
						pw.println("	/**");
						pw.println("	 *设置 " + tableInfo.getComment());
						pw.println("     */");
						pw.println("	public void set"+attributeUpper+"(java.math.BigDecimal "+attribute+") {");
						pw.println("		this."+attribute+" = "+attribute+";");
						pw.println("	}");
						pw.println();
						pw.println("	/**");
						pw.println("	 *获取 " + tableInfo.getComment());
						pw.println("     */");
						pw.println("	public java.math.BigDecimal get"+attributeUpper+"() {");
						pw.println("		return "+attribute+";");
						pw.println("	}");
					}
				}else if(tableInfo.getType().equals("DATE") || tableInfo.getType().equals("TIMESTAMP")){
					pw.println("	/**");
					pw.println("	 *设置 " + tableInfo.getComment());
					pw.println("     */");
					pw.println("	public void set"+attributeUpper+"(java.util.Date "+attribute+") {");
					pw.println("		this."+attribute+" = "+attribute+";");
					pw.println("	}");
					pw.println();
					pw.println("	/**");
					pw.println("	 *获取 " + tableInfo.getComment());
					pw.println("     */");
					pw.println("	public java.util.Date get"+attributeUpper+"() {");
					pw.println("		return "+attribute+";");
					pw.println("	}");	
				}else{
					pw.println("	/**");
					pw.println("	 *设置 " + tableInfo.getComment());
					pw.println("     */");
					pw.println("	public void set"+attributeUpper+"(java.lang.String "+attribute+") {");
					pw.println("		this."+attribute+" = "+attribute+";");
					pw.println("	}");
					pw.println();
					pw.println("	/**");
					pw.println("	 *获取 " + tableInfo.getComment());
					pw.println("     */");
					pw.println("	public java.lang.String get"+attributeUpper+"() {");
					pw.println("		return "+attribute+";");
					pw.println("	}");
				}
				
				pw.println();
			}
			
			pw.println("}");
			
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
