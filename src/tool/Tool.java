package tool;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.Vector;

import communication.Matrix;

public class Tool {
	static Calendar calendar = Calendar.getInstance();
	static Date time;
	static long timeInMillis;
	static Vector<String> dirs = new Vector<String>();

	public static void main(String[] args) throws UException {
		initDir();
	}

	public static void print(String content) {
		System.out.println(content);
	}

	public static void print(int content) {
		System.out.println(content);
	}

	public static void print(double content) {
		System.out.println(content);
	}

	public static void print(float content) {
		System.out.println(content);
	}

	public static void print(Boolean content) {
		System.out.println(content);
	}

	public static void err(String err_info) {
		System.err.println(err_info);
	}

	public static String getTime() {
		time = calendar.getTime();
		return time.toString();
	}

	public static long getTimeInMillis() {
		timeInMillis = calendar.getTimeInMillis();
		return timeInMillis;
	}

	public static String gerCurrentPath() {
		return System.getProperty("user.dir");
	}

	public static Matrix getMatrixFromPath(String path) {


		try {
			FileInputStream fis = new FileInputStream(path);
			ObjectInputStream ois = new ObjectInputStream(fis);
			Matrix m = (Matrix)ois.readObject();
			ois.close();
			return m;
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
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
	public static void initDir() throws UException {
		dirs.add("./nodeInfo");
		dirs.add("./nodeInfo/chainFile");
		dirs.add("./nodeInfo/keyFile");
		dirs.add("./nodeInfo/task");
		dirs.add("./nodeInfo/myProblem");
		dirs.add("./nodeInfo/answer");
		for(int i=0;i<dirs.size();i++){
			if(!dirExist(dirs.get(i))){
				mkdir(dirs.get(i));
				print("Build dir:"+dirs.get(i));
			}
		}

	}
	public static Matrix inputAMatrix(){
		print("please enter the row and col of the matrix(and the third number to imply whether input the specific value):");
		Scanner sc = new Scanner(System.in);
		int row = sc.nextInt();
		int col = sc.nextInt();
		int in = sc.nextInt();
		Matrix returnMaxtrix = new Matrix(row,col);
		if(in == 1){
			for(int i=0;i<row;i++)
				for(int j=0;j<col;j++){
					returnMaxtrix.change_value_at(i, j, sc.nextInt());
				}
		}
		sc.close();
		return returnMaxtrix;
	}

}