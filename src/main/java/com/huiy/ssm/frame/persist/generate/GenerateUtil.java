package com.huiy.ssm.frame.persist.generate;

public class GenerateUtil {
	
	
	
	
	public static String getJdbcTypeBySqlType(String sqlType){
		String tmpSqlType = sqlType.toUpperCase();
		if ("CHARACTER".equals(tmpSqlType)){
			return "CHAR";
		}else if ("DATE".equals(tmpSqlType)){
			return "Timestamp";
		}else{
			return sqlType;
		}
	}
	
	
	
	
	/**
	 * 
	 * @MethodName: getRightWordOfClass 
	 * @Description: 返回类名
	 * 步骤：
	 * 1、
	 * 2、
	 * @param word
	 * @return 
	 * @return String 返回类型 
	 * @throws
	 * @remark
	 */
	public static String getRightWordOfClass(String word){
		String w = getRightWordOfAttributeT(word);
		return w.substring(0, 1).toUpperCase() + w.substring(1);
	}
	public static String getRightWordOfAttributeT(String word){
		
		word = word.toLowerCase();
		if (word.equals("p_tpl_no")){
			return "ptplNo";
		}
		if (word.equals("etrstx_tpcd")){
			return "etrstxTpCd";
		}
		StringBuilder newWord = new StringBuilder();
		int index = 0;
		while(true){
			int flag = word.indexOf("_", index);
			if(flag == -1){
				newWord.append(word.substring(index));
				break;
			}
			newWord.append(word.substring(index, flag));			
			newWord.append(word.substring(flag + 1,flag + 2).toUpperCase());
			index = flag + 2;
		}
		
		return newWord.toString();	
	}
	public static void main(String[] args) {
		GenerateUtil.getRightWordOfAttribute("U_G_R_PASSWORD");
	}
	/**
	 * 
	 * @MethodName: getRightWordOfAttribute 
	 * @Description: 返回属性名
	 * 步骤：
	 * 1、
	 * 2、
	 * @param word
	 * @return 
	 * @return String 返回类型 
	 * @throws
	 * @remark
	 */
	public static String getRightWordOfAttribute(String word){
	
		word = word.toLowerCase();
		if (word.equals("p_tpl_no")){
			return "ptplNo";
		}
		if (word.equals("etrstx_tpcd")){
			return "etrstxTpCd";
		}
		StringBuilder newWord = new StringBuilder();
		int index = 0;
		while(true){
			int flag = word.indexOf("_", index);
			if(flag == -1){
				newWord.append(word.substring(index));
				break;
			}
			newWord.append(word.substring(index, flag));			
			if(index==0&&newWord.toString().length()==1)newWord.append(word.substring(flag + 1,flag + 2));
			else newWord.append(word.substring(flag + 1,flag + 2).toUpperCase());
			index = flag + 2;
		}
		
		return newWord.toString();	
	}
}
