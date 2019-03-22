package main;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import tool.Matrix;
import tool.Tool;





public class ProblemPublisher{
	
	private final static String filePathA = "./problem/matrix_a";
	private final static String filePathB = "./problem/matrix_b";
	public static void run() throws UException, IOException, ClassNotFoundException {
		if(!Tool.fileExist(filePathA) || !Tool.fileExist(filePathB)) {
			throw new UException("No matrix file!");
		}
		else {
			
			FileInputStream fis = new FileInputStream(filePathA);
			ObjectInputStream ois = new ObjectInputStream(fis);
			Matrix ma= (Matrix) ois.readObject();
			Tool.print(ma.toString());
			
			fis = new FileInputStream(filePathB);
			ois = new ObjectInputStream(fis);

			Matrix mb= (Matrix) ois.readObject();
			Tool.print(mb.toString());
			ois.close();

			


		}
	}
	




}