package main;

import java.io.File;

public class Tool{
	public static void print(String content){
		System.out.println(content);
	}
	public static void print(int content){
		System.out.println(content);
	}
	public static void print(double content){
		System.out.println(content);
	}
	public static void print(float content){
		System.out.println(content);	
	}
	public static void print(Boolean content){
		System.out.println(content);	
	}
	public static boolean fileExist(String filePath) {
		File file = new File(filePath);
		if(file.exists()) {
			return true;
		}
		else
			return false;
	}
	public static void mkdir(String dirPath) {
		File dir = new File(dirPath);
		dir.mkdir();
	}
}