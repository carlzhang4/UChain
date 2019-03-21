package main;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import tool.*;

import tool.Matrix;

public class Test {
	public static void main(String[] args) throws Exception {
		Matrix m1 = new Matrix(3,5);
		m1.change_value_at(0, 0, 1);
		FileOutputStream fos=new FileOutputStream("./problem/matrix_b");		
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(m1);
		oos.close();
		
		Tool.print(m1.toString());
		
		
		
		
	}

}