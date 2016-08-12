package common.consts;

import java.io.File;

public class Path {
	//public static final String ROOT="D:\\2016project\\dataroot\\userdata01\\";
	public static final String ROOT=System.getProperty("user.home") + File.separator+"sitedata"+File.separator;
	public static final String CUSTOMER_ROOT= ROOT+"customer"+File.separator;
	public static String getSavePath(String userName,EDir EDir,String... fileName){
		StringBuilder sb=new StringBuilder();
		sb.append(CUSTOMER_ROOT);
		sb.append(userName);
		sb.append(File.separator);
		sb.append(EDir.name());

		for(int i=0;i<fileName.length;i++){
			sb.append(File.separator);
			sb.append(fileName[i]);
		}
		return sb.toString();
	}
	public static String getSavePath(String userName,EDir EDir){
		StringBuilder sb=new StringBuilder();
		sb.append(CUSTOMER_ROOT);
		sb.append(userName);
		sb.append(File.separator);
		sb.append(EDir.name());
		return sb.toString();
	}
	public static String getCommonSavePath(String siteName,EDev EDev,String... fileName){
		StringBuilder sb=new StringBuilder();
		sb.append(CUSTOMER_ROOT);
		sb.append(siteName);
		sb.append(File.separator);
		sb.append(EDev.name());

		for(int i=0;i<fileName.length;i++){
			sb.append(File.separator);
			sb.append(fileName[i]);
		}
		return sb.toString();
	}
}