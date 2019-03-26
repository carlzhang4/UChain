package tool;

import java.io.File;
import java.util.Calendar;
import java.util.Date;

public class Tool{
	static Calendar calendar = Calendar.getInstance();
	static Date time;
	static long timeInMillis;
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
	public static void err(String err_info) {
		System.err.println(err_info);
	}

	public static String getTime(){
		time = calendar.getTime();
		return time.toString();
	}
	public static long getTimeInMillis(){
		timeInMillis = calendar.getTimeInMillis();
		return timeInMillis;
	}

	public static boolean fileExist(String filePath) {
		File file = new File(filePath);
		if(file.exists()) {
			return true;
		}
		else
			return false;
	}

	public static boolean dirExist(String dirPath) {
		File dir = new File(dirPath);
		if  (!dir .exists()  && !dir .isDirectory()){
		    return false;
		}
		else{
		    return true;
		}
	}


	public static void mkdir(String dirPath) throws UException {
		if(dirExist(dirPath) == false) {
			File dir = new File(dirPath);
			dir.mkdir();
		}
		else {
			throw new UException("dir has already been there!");
		}
	}
}