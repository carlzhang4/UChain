package main;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;

import tool.*;

public class ProblemPublisher{
	private final static String filePathA = "./problem/matrix_a";
	private final static String filePathB = "./problem/matrix_b";
	public static void run() throws UException, IOException, ClassNotFoundException {
		if(!Tool.fileExist(filePathA) || !Tool.fileExist(filePathB)) {
			throw new UException("No such problem file!");
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
		}
	}
}