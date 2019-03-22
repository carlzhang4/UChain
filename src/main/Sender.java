package main;


import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import tool.Matrix;
import tool.Tool;
 
public class Sender {
	
	private final static Logger logger = Logger.getLogger(Sender.class.getName());
	
	public static void main(String[] args) throws Exception {
		for (int i = 0; i < 11; i++) {
			Socket socket = null;
			ObjectOutputStream os = null;
			ObjectInputStream is = null;
			
			try {
				socket = new Socket("localhost", 10000);
	
				os = new ObjectOutputStream(socket.getOutputStream());
				Matrix m = new Matrix(1000,1000);
				os.writeObject(m);
				os.flush();
				
				is = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
				Object obj = is.readObject();
				if (obj != null) {
					m = (Matrix)obj;
					//System.out.println(m.toString());
					Tool.print("Done!");
				}
			} catch(IOException ex) {
				logger.log(Level.SEVERE, null, ex);
			} finally {
				try {
					is.close();
				} catch(Exception ex) {}
				try {
					os.close();
				} catch(Exception ex) {}
				try {
					socket.close();
				} catch(Exception ex) {}
			}
		}
	}
}
