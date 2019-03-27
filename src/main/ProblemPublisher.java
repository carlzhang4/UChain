package main;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import communication.Matrix;
import tool.Tool;
import tool.UException;





public class ProblemPublisher{

	private final static String filePathA = "./nodeInfo/problem/matrix_a";
	private final static String filePathB = "./nodeInfo/problem/matrix_b";
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