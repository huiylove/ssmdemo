package com.huiy.ssm.frame.core.utils;

import java.io.*;
import java.util.*;

/**
 * 自动生成版本更新文件
 * 
 * @author Aillans create date:2012-6-7
 * 
 */
public final class CreateDirectoryUtils {

	private static final String PROJECT_PATH = System.getProperty("user.dir");
	private static final String PROJECT_NAME = System.getProperty("user.dir").substring(System.getProperty("user.dir").lastIndexOf(File.separator));
	private static List<File> allFile = new ArrayList<File>();
	private static Set<File> innerClassFiles = new HashSet<File>();

	/**
	 * 列出指定目录的所有文件
	 * 
	 * @param 目录
	 * @return
	 */
	private static List<File> listAllFile(File directory) {
		File[] file = directory.listFiles();
		for (File file2 : file) {
			if (file2.isFile()) {
				allFile.add(file2);
				// 列出所有的内部类
				String file2Name = file2.getName();
				if (file2Name.indexOf("$") != -1 && file2Name.endsWith(".class")) {
					innerClassFiles.add(file2);
				}
			} else {
				listAllFile(file2);
			}
		}
		return allFile;
	}

	/**
	 * 获取版本更新所需文件
	 * 
	 * @param fileNames
	 * @return
	 */
	private static Map<String, File> createFinalFiles(List<String> fileNames) {
		Set<File> set = new HashSet<File>();
		// 列出项目中所有文件
		List<File> files = listAllFile(new File(PROJECT_PATH));
		for (String fileName : fileNames) {
			String fileNamewithOutExtend = fileName.substring(0, fileName.lastIndexOf("."));
			String extend = fileName.substring(fileName.lastIndexOf(".") + 1);
			// 获取版本更新所需所有非java类文件
			if (!extend.equals("java")) {
				for (File file : files) {
					String fileName2 = file.getName();
					if (fileName2.equals(fileName)) {
						set.add(file);
					}
				}
				// 获取java类文件的*.class和*.java
			} else {
				for (File file : files) {
					String classFileName = fileNamewithOutExtend + ".class";
					String fileName2 = file.getName();
					if (fileName2.equals(fileName) || fileName2.equals(classFileName)) {
						set.add(file);
					}
				}
			}
		}
		// 去除名字相同的文件
		Map<String, File> finalFiles = removeTheSameNameFileAndGetTheInnerClass(set);
		return finalFiles;
	}

	/**
	 * 去除一个项目中同名的文件，使得每个文件的唯一性
	 * eg:例如：ApplyLogBean在wbp中有两个，更新的时候自动取ApplyLogBean.java最后更新的那个，
	 * 并根据*.java的路径找到*.class 这样确保了版本更新文件的正确性
	 * 
	 * @param files
	 * @return
	 */
	private static Map<String, File> removeTheSameNameFileAndGetTheInnerClass(Set<File> files) {
		Map<String, File> map = new HashMap<String, File>();
		Set<File> classFiles = new HashSet<File>();
		try {
			// 将*.class文件放在一个Set里
			for (File file : files) {
				if (file.getName().endsWith(".class")) {
					classFiles.add(file);
				}
			}
			files.removeAll(classFiles);
			for (File file : files) {
				String fileName = file.getName();
				if (map.get(fileName) == null || map.get(fileName).lastModified() < file.lastModified()) {
					map.put(fileName, file);
					if (fileName.endsWith(".java")) {
						// 将同路径下的class文件加入到map中.
						for (File classFile : classFiles) {
							String classFileName = classFile.getCanonicalPath();
							String myClassFileName = classFileName.substring(classFileName.indexOf("classes" + File.separator) + 7,
									classFileName.length() - 6)
									+ ".java";
							if (file.getCanonicalPath().endsWith(myClassFileName)) {
								map.put(classFile.getName(), classFile);
								// 添加内部类入库
								for (File innerClassFile : innerClassFiles) {
									String innerClassFilePath = innerClassFile.getCanonicalPath();
									String classFileNameWithOutExtend = classFileName.substring(0, classFileName.lastIndexOf("."));
									if (innerClassFilePath.startsWith(classFileNameWithOutExtend + "$"))
										map.put(innerClassFile.getName(), innerClassFile);
								}
							}
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 创建版本更新文件汇总(带目录)
	 * 
	 * @param fileNames
	 *            所有需更新的文件集合
	 * @param yourDestination
	 *            生成的目录放在具体的位置 eg：F:/项目更新 这样目录就自动创建在F盘的项目更新文件夹下
	 *            eg:List<String>list=new ArrayList<String>();
	 *            list.add("SimplexSocket.java"); list.add("busiFunc.jsp");
	 *            createFinalDirectory(list, "F:/更新文件版本");
	 *            这样在F盘自动创建"更新文件版本"文件夹,并将list中所有的文件按目录复制进去
	 *            warning:需更新java类时，请在list中加入*.java而不是*.class,系统会自动将此类的
	 *            java文件和class文件一并保存到class类指定的目录下
	 */
	public static void createFinalDirectory(List<String> fileNames, String yourDestination) {

		// 创建update.txt
		String updateTxt = yourDestination + PROJECT_NAME + File.separator + "update.txt";
		File update = new File(updateTxt);
		if (!update.getParentFile().exists()) {
			update.getParentFile().mkdirs();
		}
		PrintWriter pw = null;

		Map<String, File> finalFiles = createFinalFiles(fileNames);
		try {
			pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(update, false), "GBK"), true);
			pw.println("需求:");
			for (int i = 0; i < 10; i++) {
				pw.println();
			}
			pw.println("文件位置");
			Set<String> myFileNames = finalFiles.keySet();
			// 复制非java文件
			for (String fileName : myFileNames) {
				File file = finalFiles.get(fileName);
				boolean isJavaFile = false;
				File pathFile = file;
				if (fileName.endsWith(".java")) {
					isJavaFile = true;
					String javaFileName = file.getName();
					String classFileNameForJava = javaFileName.substring(0, javaFileName.lastIndexOf(".")) + ".class";
					pathFile = finalFiles.get(classFileNameForJava);
				}
				String reallyPath = getReallyPath(yourDestination, pathFile, isJavaFile);
				if (!reallyPath.endsWith(".class"))
					pw.println("\t\t\t\t\t\t"
							+ file.getCanonicalPath().substring(
									1 + file.getCanonicalPath().lastIndexOf(PROJECT_PATH.substring(PROJECT_PATH.lastIndexOf(File.separator)))));
				copyFile(file, reallyPath);

			}
			pw.println("脚本");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(pw != null){
				pw.close();
			}
		}

	}

	/**
	 * 获取目的路径
	 * 
	 * @param 要更新的文件名
	 * @return 文件名对应的指定目录的地址
	 * @throws IOException
	 */
	private static String getReallyPath(String yourDestination, File file, boolean isJavaFile) throws IOException {

		String path = file.getCanonicalPath();
		String p = System.getProperty("user.dir");
		String pp = path.substring(path.indexOf(p) + p.length());
		String reallyPath = yourDestination + p.substring(p.lastIndexOf(File.separator)) + pp;
		if (isJavaFile) {
			reallyPath = reallyPath.substring(0, reallyPath.lastIndexOf(".")) + ".java";
		}
		return reallyPath;
	}

	/**
	 * 将所有更新的文件复制到指定目录下
	 * 
	 * @param sourceFile
	 * @param destition
	 */
	private static void copyFile(File sourceFile, String destition) {
		// 建立目的目录文件
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		File df = new File(destition);
		if (!df.getParentFile().exists()) {
			df.getParentFile().mkdirs();
		}
		try {
			bis = new BufferedInputStream(new FileInputStream(sourceFile));
			bos = new BufferedOutputStream(new FileOutputStream(df));

			int i;
			while ((i = bis.read()) != -1) {
				bos.write(i);
			}
			bos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(bis != null){
					bis.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if(bos != null){
					bos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public static void main(String[] args) throws IOException {

		List<String> list = new ArrayList<String>();

		list.add("userList.jsp");


		createFinalDirectory(list, "d:\\Project Update\\yw更新");
		System.out.println("bondTrade");

	}
}
