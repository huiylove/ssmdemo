package com.huiy.ssm.frame.persist.generate;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class DBXmlGenerator {

 
 public static void main(String[] args) {
 
  //List list = read(file);
	// String abc = "com.abc.def";
	 //StringUtils.replace(abc, ".", "\\");
	 //System.out.println(StringUtils.replace(abc, ".", "\\"));
	 
	 
  
  out();

 }
 public  static List read(String file) {
  try{
  BufferedReader in = new BufferedReader(new InputStreamReader(new BufferedInputStream(new FileInputStream(file))));
  List list = new ArrayList();
  String str;
  while((str = in.readLine())  != null) {
   System.out.println(str);
   list.add(str);
  }
  in.close();
  return list;
  } catch(IOException e){
   e.printStackTrace();
   throw new RuntimeException("文件读取错误",e);
  }
 }
 public static void out() {
  try{
	  JDBC jdbc = new JDBC();
//	  STM_ACG_DT	系统账务日期
//	  ERSTRCVPYAR	委托代收付合�?
//	  EOD_RCNCL_FILE_RGS_INF	日终对账文件登记信息
//	  PER_SBSTRCVPY_TXN_INF	单笔代收付交易信�?
//
//	  SBSTRCVPY_EOD_RCNCL_INF	代收付日终对账信�?
//	  SBSTRCVPY_EOD_RCNCL_ADJ_LIST	代收付日终对账调整清�?
//	  SBSTRCVPY_EOD_RCNCL_INF_DTL	代收付日终对账信息明�?
	  List<String> tableList = PoGenConf.tablelist;
//	   tableList = jdbc.queryAllTables();

	  String newFile = "./src/main/resources/sqlmap/";
	  File menu = new File(newFile);
		if(!menu.exists()){
			menu.mkdirs();
		}
	  TableInfo info = new TableInfo();
	  
			for (int i = 0; i < tableList.size(); i++) {
				
				String classname = GenerateUtil.getRightWordOfClass(tableList.get(i).toUpperCase());
				
				List<String> pkList = jdbc.queryPkForTable(tableList.get(i));
				
				List<TableInfo> columnsList = jdbc.queryAllColumnsAboutTable(tableList.get(i));
				List <TableInfo> notkeyList = new ArrayList();
				List <TableInfo> keyList = new ArrayList();
				for(int a = 0;a < columnsList.size();a ++){
					if(pkList != null && pkList.size() > 0){
						if(!pkList.contains(columnsList.get(a).getColumn())){
							TableInfo info1 = new TableInfo();
							info1.setColumn(columnsList.get(a).getColumn());
							info1.setType(columnsList.get(a).getType());
							notkeyList.add(info1);
						}
					}else{
						TableInfo info1 = new TableInfo();
						info1.setColumn(columnsList.get(a).getColumn());
						info1.setType(columnsList.get(a).getType());
						notkeyList.add(info1);
					}
				}
				
				for(int a = 0;a < columnsList.size();a ++){
					if(pkList != null && pkList.size() > 0){
						if(pkList.contains(columnsList.get(a).getColumn())){
							TableInfo info1 = new TableInfo();
							info1.setColumn(columnsList.get(a).getColumn());
							info1.setType(columnsList.get(a).getType());
							keyList.add(info1);
						}
					}
				}
				
				//newFile = DBJavaGenerator.path+"/sqlmap/" + classname + ".xml";
				newFile = "./src/main/resources/sqlmap/" + classname + ".xml";
				File file1 = new File(newFile);
				if (file1.exists()) {

				} else {
					try {
						file1.createNewFile();
					} catch (IOException ex) {
						System.out.println(ex.getMessage());
					}
				}
				BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
						new BufferedOutputStream(new FileOutputStream(newFile))));


				// for(int i = 0;i < 10 ;i++){
				String a = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
				out.write(a + "\r\n");
				a = "<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\" >";
				out.write(a + "\r\n");
				a = "<mapper namespace=\"" + classname +
						"\">";
				out.write(a + "\r\n");
				out.write("" + "\r\n");
				out.write("" + "\r\n");
				out.write("<!-- 请不要在公共mapping文件内加入自己写的方法，应放到XXXX_C_Custom.xml�?!!!!! -->" + "\r\n");
				a = "<resultMap id=\"" +classname + "PO" + "\"" + " type=\""+PoGenConf.basePackage+".po." +
						classname + "PO" +
						"\" >";
				out.write(a + "\r\n");
				if(columnsList != null && columnsList.size()> 0){
					for(int x = 0;x< columnsList.size();x++){
						a = "    <result column=\"" +
								columnsList.get(x).getColumn() +
								"\" property=\"" +
								GenerateUtil.getRightWordOfAttribute(columnsList.get(x).getColumn()) +
								"\" />";
						out.write(a + "\r\n");
					}
	
				}
				a = 		"</resultMap>";
				out.write(a + "\r\n");
				
/*				for(int v=0;v < tableList.size();v++){
					a = "<sqlMap resource=\"com/ccb/eabs/persist/po/mapping/" +
							GenerateUtil.getRightWordOfClass(tableList.get(v)) +
							".xml\" />";
					out.write(a + "\r\n");
				}
				*/
				
				// query
				a = "        <select id=\"query\" parameterType=\""+PoGenConf.basePackage+".po." + classname + 
						"PO\" resultMap=\"" + classname +
						"PO\">";
				out.write(a + "\r\n");
				a = "            <![CDATA[";
				out.write(a + "\r\n");
				a = "                select " ;
				if(columnsList != null && columnsList.size() >0){
					for(int x = 0;x < columnsList.size()-1;x++ ){
						a = a + columnsList.get(x).getColumn() + ",";
					}
					a = a + columnsList.get(columnsList.size()-1).getColumn();
				}
				out.write(a + "\r\n");
				a = "                from " + tableList.get(i);
						
				out.write(a + "\r\n");
	/*			if(pkList != null && pkList.size() >0){
					a = "                where " +
							pkList.get(0) + 
							" = #" +
							GenerateUtil.getRightWordOfAttribute(pkList.get(0)) +
							"#";
					out.write(a + "\r\n");
				
				}
				if(pkList != null && pkList.size() >1){
					for(int x = 1;x < pkList.size() ;x++ ){
						a = "                and " +
								pkList.get(x) + 
								" = #" +
								GenerateUtil.getRightWordOfAttribute(pkList.get(x)) +
								"#";
						out.write(a + "\r\n");
					}
				}
				a = "            ]]>";
				out.write(a + "\r\n");
				a = "        </select>";*/
				/////////////////////////////////////////////////
				a = "            ]]>";
				out.write(a + "\r\n");
				a = "            WHERE 1 = 1 ";
				out.write(a + "\r\n");
				
				if(columnsList != null && columnsList.size() >0){
					for(int x = 0;x < columnsList.size();x++ ){
						
						//<if test="dicId != null"> and dic_id = #{dicId}</if>
						
						a = "<if test=\""+ GenerateUtil.getRightWordOfAttribute(columnsList.get(x).getColumn()) + " != null and "+GenerateUtil.getRightWordOfAttribute(columnsList.get(x).getColumn())+" != '' \"> and " + columnsList.get(x).getColumn() + " = #{" + GenerateUtil.getRightWordOfAttribute(columnsList.get(x).getColumn()) + "}</if>"; 
						out.write(a + "\r\n");
					}
					
				}
				////////////////////////////////////////
				a = "        </select>";
				out.write(a + "\r\n");

				// queryList
				a = "        <select id=\"queryList\" parameterType=\""+PoGenConf.basePackage+".po." +
						classname +"PO\" " +
						"resultMap=\"" +
						  classname +
						"PO\">";
				out.write(a + "\r\n");
				a = "            <![CDATA[";
				out.write(a + "\r\n");
				a = "                select " ;
				if(columnsList != null && columnsList.size() >0){
					for(int x = 0;x < columnsList.size()-1;x++ ){
						a = a + columnsList.get(x).getColumn() + ",";
					}
					a = a + columnsList.get(columnsList.size()-1).getColumn();
				}
				out.write(a + "\r\n");
				a = "                from " + tableList.get(i);
						
				out.write(a + "\r\n");
				a = "            ]]>";
				out.write(a + "\r\n");
				
				/////////////////////////////////////////////////
				a = "            WHERE 1 = 1 ";
				out.write(a + "\r\n");
				
				if(columnsList != null && columnsList.size() >0){
					for(int x = 0;x < columnsList.size();x++ ){
						a = "<if test=\""+ GenerateUtil.getRightWordOfAttribute(columnsList.get(x).getColumn()) + " != null and "+GenerateUtil.getRightWordOfAttribute(columnsList.get(x).getColumn())+" != '' \"> and " + columnsList.get(x).getColumn() + " = #{" + GenerateUtil.getRightWordOfAttribute(columnsList.get(x).getColumn()) + "}</if>";
						out.write(a + "\r\n");
					}
					
				}
				
				if(keyList != null && keyList.size() >0){
					for(int x = 0;x < keyList.size();x++ ){
						String col = keyList.get(x).getColumn();
						if ("LGC_SN".equalsIgnoreCase(col)){
							col = " to_number(LGC_SN) ";
						}
						if (x== 0){
							
							a = " ORDER BY " + col;
						}else{
							a = " , " + col;
						}
						out.write(a);
					}
					out.write("\r\n");
				}
				
				////////////////////////////////////////
				a = "        </select>";
				out.write(a + "\r\n");

				// queryPage_COUNT
				/*a = "       <select id=\"queryPage_COUNT\" parameterType=\""+PoGenConf.basePackage+".po." +
						classname +"PO\" " +
						"resultType=\"java.lang.Integer\">";
				out.write(a + "\r\n");
				a = "            <![CDATA[";
				out.write(a + "\r\n");
				a = "                select count(1)";
				out.write(a + "\r\n");
				a = "                from " +
						tableList.get(i);
				out.write(a + "\r\n");
				a = "            ]]>";
				out.write(a + "\r\n");
				/////////////////////////////////////////////////
				//a = "            <![CDATA[";
				//out.write(a + "\r\n");
				a = "            WHERE 1 = 1 ";
				out.write(a + "\r\n");
				
				if(columnsList != null && columnsList.size() >0){
					for(int x = 0;x < columnsList.size();x++ ){
						a = "<if test=\""+ GenerateUtil.getRightWordOfAttribute(columnsList.get(x).getColumn()) + " != null\"> and " + columnsList.get(x).getColumn() + " = #{" + GenerateUtil.getRightWordOfAttribute(columnsList.get(x).getColumn()) + "}</if>";
						out.write(a + "\r\n");
					}
				}
				//a = "            ]]>";
				//out.write(a + "\r\n");
				////////////////////////////////////////
				a = "        </select>";
				out.write(a + "\r\n");*/

				// queryPage_FORCE
				a = "        <select id=\"queryPage\" parameterType=\""+PoGenConf.basePackage+".po." +
						classname +"PO\" " +
						"resultMap=\"" +
						  classname +
						"PO\">";
				out.write(a + "\r\n");
				a = "            <![CDATA[";
				//out.write(a + "\r\n");
				//a = "                select * from ( select row_.*, rownum rownum_ from (";
				out.write(a + "\r\n");
				a = "                select " ;
				if(columnsList != null && columnsList.size() >0){
					for(int x = 0;x < columnsList.size()-1;x++ ){
						a = a + columnsList.get(x).getColumn() + ",";
					}
					a = a + columnsList.get(columnsList.size()-1).getColumn();
				}
				out.write(a + "\r\n");
				a = "                from " +
						tableList.get(i);
				out.write(a + "\r\n");
				a = "            ]]>";
				out.write(a + "\r\n");
				//a = "            <![CDATA[";
				//out.write(a + "\r\n");
				a = "            WHERE 1 = 1 ";
				out.write(a + "\r\n");
				
				if(columnsList != null && columnsList.size() >0){
					for(int x = 0;x < columnsList.size();x++ ){
						a = "<if test=\""+ GenerateUtil.getRightWordOfAttribute(columnsList.get(x).getColumn()) + " != null and "+GenerateUtil.getRightWordOfAttribute(columnsList.get(x).getColumn())+" != '' \"> and " + columnsList.get(x).getColumn() + " = #{" + GenerateUtil.getRightWordOfAttribute(columnsList.get(x).getColumn()) + "}</if>";
						out.write(a + "\r\n");
					}
				}
				//a = "            <![CDATA[";
				//out.write(a + "\r\n");
				//a = "                ) row_ ) where rownum_ <= #maxNum# and rownum_ > #startNum#";
				//out.write(a + "\r\n");
				//a = "            ]]>";
				//out.write(a + "\r\n");
				
				//a = "            ]]>";
				//out.write(a + "\r\n");
				
				if(keyList != null && keyList.size() >0){
					for(int x = 0;x < keyList.size();x++ ){
						String col = keyList.get(x).getColumn();
						if ("LGC_SN".equalsIgnoreCase(col)){
							col = " to_number(LGC_SN) ";
						}
						if (x== 0){
							
							a = " ORDER BY " + col;
						}else{
							a = " , " + col;
						}
						out.write(a);
					}
					out.write("\r\n");
				}
				

				a = "        </select>";
				out.write(a + "\r\n");

				// insert
				a = "        <insert id=\"insert\" parameterType=\""+PoGenConf.basePackage+".po." +
						classname  + "PO\">";
				out.write(a + "\r\n");
				//a = "            <![CDATA[";
				//out.write(a + "\r\n");
				a = "                insert into " +
						tableList.get(i) +
						"(";
				out.write(a + "\r\n");
				a = "";
/*				if(columnsList != null && columnsList.size() >0){
					for(int x = 0;x < columnsList.size()-1;x++ ){
						a = a + columnsList.get(x).getColumn() + ",";
					}
					a = a + columnsList.get(columnsList.size()-1).getColumn();
				}*/
				if(keyList != null && keyList.size() > 0 ) {
					for(int b = 0;b < keyList.size() -1;b ++){
						a = "                     "+keyList.get(b).getColumn() + ",";
						out.write(a + "\r\n");
					}
					a = "                     "+keyList.get(keyList.size()-1).getColumn() ;
					out.write(a + "\r\n");
				}
				
				if(notkeyList != null && notkeyList.size() > 0){
					a = "                ";
					out.write(a + "\r\n");
					for(int x = 0;x < notkeyList.size();x++){
						//<if test="dicName != null">dic_name</if>
						a = "<if test=\""+ GenerateUtil.getRightWordOfAttribute(notkeyList.get(x).getColumn()) + " != null and "+GenerateUtil.getRightWordOfAttribute(notkeyList.get(x).getColumn())+" != '' \"> " +"," +notkeyList.get(x).getColumn() +"</if>";
						out.write(a + "\r\n");
					
					}
					// a = "<if test=\""+ GenerateUtil.getRightWordOfAttribute(notkeyList.get(notkeyList.size() -1 ).getColumn()) + " != null\"> " + notkeyList.get(notkeyList.size() -1 ).getColumn() +"</if>";
					//out.write(a + "\r\n");
				}

				a = " )               values (";
				out.write(a + "\r\n");
				if(keyList != null && keyList.size() > 0 ) {
					for(int b = 0;b < keyList.size() -1;b ++){
						//a = keyList.get(b).getColumn() + ",";
						a = "                     "+"#{" +
								GenerateUtil.getRightWordOfAttribute(keyList.get(b).getColumn()) +
								":" +
								GenerateUtil.getJdbcTypeBySqlType(keyList.get(b).getType())+
								"},";
						out.write(a + "\r\n");
					}
					a="                     "+"#{" +
					GenerateUtil.getRightWordOfAttribute(keyList.get(keyList.size()-1).getColumn()) +
					":" +
					GenerateUtil.getJdbcTypeBySqlType(keyList.get(keyList.size()-1).getType())+
					"}";
					out.write(a + "\r\n");
				}
				
				if(notkeyList != null && notkeyList.size() > 0){
					a = "                ";
					out.write(a + "\r\n");
					for(int x = 0;x < notkeyList.size();x++){
						
						//<if test="dicName != null">#{dicId}</if>  
					
						/*a = "<isNotEmpty   prepend= \", \"   property= \"" +
								GenerateUtil.getRightWordOfAttribute(notkeyList.get(x).getColumn()) +
								"\">" +
								notkeyList.get(x).getColumn() +
								"</isNotEmpty>";*/
						a = "<if test=\""+ GenerateUtil.getRightWordOfAttribute(notkeyList.get(x).getColumn()) + " != null and "+GenerateUtil.getRightWordOfAttribute(notkeyList.get(x).getColumn())+" != '' \"> ,#{" +
								GenerateUtil.getRightWordOfAttribute(notkeyList.get(x).getColumn()) +
								":" +
								GenerateUtil.getJdbcTypeBySqlType(notkeyList.get(x).getType())+
								"}</if>";
						out.write(a + "\r\n");
					
					}
				}
				a = "                 )";
				out.write(a + "\r\n");
				//a = "            ]]>";
				//out.write(a + "\r\n");
				a = "    </insert>";
				out.write(a + "\r\n");

				// update
				a = "        <update id=\"update\" parameterType=\""+PoGenConf.basePackage+".po." +
						classname  + "PO\">";
				out.write(a + "\r\n");
				a = "           <![CDATA[ update " +
						tableList.get(i) +
						" set " ;
				out.write(a + "\r");
				if(keyList != null && keyList.size() > 0 ) {
					for(int b = 0;b < keyList.size() -1;b ++){
						a = keyList.get(b).getColumn() +
								" = #{" +
								GenerateUtil.getRightWordOfAttribute(keyList.get(b).getColumn()) +
								":" +
								GenerateUtil.getJdbcTypeBySqlType(keyList.get(b).getType())+
								"},";
						out.write(a + "\r");
					}
					a = keyList.get(keyList.size()-1).getColumn() +
							" = #{" +
							GenerateUtil.getRightWordOfAttribute(keyList.get(keyList.size()-1).getColumn()) +
							":" +
							GenerateUtil.getJdbcTypeBySqlType(keyList.get(keyList.size()-1).getType())
							 +
							"}";
					out.write(a + "\r");
				}
				
				a = "]]>";
				out.write(a + "\r\n");
				/*if(pkList != null && pkList.size() > 0){
					for(int x = 0;x < pkList.size();x++){
						a = 
						out.write(a + "\r\n");
					}
				}	*/
				
				
				if(notkeyList != null && notkeyList.size() > 0){
					for(int x = 0;x < notkeyList.size() ;x++){
					a = "                ";
					out.write(a + "\r");
					
					a = "<if test=\""+ GenerateUtil.getRightWordOfAttribute(notkeyList.get(x).getColumn()) + " != null and "+GenerateUtil.getRightWordOfAttribute(notkeyList.get(x).getColumn()) +" != '' \"> ," + notkeyList.get(x).getColumn() + " = #{" +
							GenerateUtil.getRightWordOfAttribute(notkeyList.get(x).getColumn()) +
							":" +
							GenerateUtil.getJdbcTypeBySqlType(notkeyList.get(x).getType())+
							"}</if>";
					out.write(a + "\r\n");
					
					
					/*a = "               <isNotNull property=\"" +
							GenerateUtil.getRightWordOfAttribute(notkeyList.get(x).getColumn()) +
							"\" prepend=\",\"><![CDATA[" +
							notkeyList.get(x).getColumn() +
							" = #" +
							GenerateUtil.getRightWordOfAttribute(notkeyList.get(x).getColumn()) +
							":" +
							("DATE".equalsIgnoreCase(notkeyList.get(x).getType())? "Timestamp":notkeyList.get(x).getType())+
							"#]]></isNotNull>";*/
					a = "                 ";
					out.write(a + "\r\n");
					}
					/*a = "<if test=\""+ GenerateUtil.getRightWordOfAttribute(notkeyList.get(notkeyList.size() - 1).getColumn()) + " != null\"> " + notkeyList.get(notkeyList.size() - 1).getColumn() + " #{" +
							GenerateUtil.getRightWordOfAttribute(notkeyList.get(notkeyList.size() - 1).getColumn()) +
							":" +
							("DATE".equalsIgnoreCase(notkeyList.get(notkeyList.size() - 1).getType())? "Timestamp":notkeyList.get(notkeyList.size() - 1).getType())+
							"}#</if>";
					out.write(a + "\r\n");*/
				}
				
				
				
				a = "                <![CDATA[ ";
						out.write(a + "\r\n");
						if(pkList != null && pkList.size() >0){
							a = "                where " +
									pkList.get(0) + 
									" = #{" +
									GenerateUtil.getRightWordOfAttribute(pkList.get(0)) +
									"}";
							out.write(a + "\r\n");
						
						}
						if(pkList != null && pkList.size() >1){
							for(int x = 1;x < pkList.size() ;x++ ){
								a = "                and " +
										pkList.get(x) + 
										" = #{" +
										GenerateUtil.getRightWordOfAttribute(pkList.get(x)) +
										"}";
								out.write(a + "\r\n");
							}
						}
						a= "]]>";
				out.write(a + "\r\n");
				a = "             </update>";
				out.write(a + "\r\n");

				// delete
				a = "        <delete id=\"delete\"  parameterType=\""+PoGenConf.basePackage+".po." +
						classname  + "PO\">";
				out.write(a + "\r\n");
				a = "           <![CDATA[";
				out.write(a + "\r\n");
				a = "                delete from " + tableList.get(i);
				out.write(a + "\r\n");
				if(pkList != null && pkList.size() >0){
					a = "                where " +
							pkList.get(0) + 
							" = #{" +
							GenerateUtil.getRightWordOfAttribute(pkList.get(0)) +
							"}";
					out.write(a + "\r\n");
				
				}
				if(pkList != null && pkList.size() >1){
					for(int x = 1;x < pkList.size() ;x++ ){
						a = "                and " +
								pkList.get(x) + 
								" = #{" +
								GenerateUtil.getRightWordOfAttribute(pkList.get(x)) +
								"}";
						out.write(a + "\r\n");
					}
				}
				a = "               ]]>";
				out.write(a + "\r\n");
				a = "                </delete>";
				out.write(a + "\r\n");

				a = "</mapper>";
				out.write(a + "\r\n");
				// }
				out.flush();
				out.close();
				System.out.print(tableList.get(i) + ".xml成功\r\n");
	  }
			System.out.print( "完成！！！！！！！！！！！！！！！！！！");
  } catch(IOException e) {
   e.printStackTrace();
   throw new RuntimeException("文件写入错误",e);
  }
 }
}

