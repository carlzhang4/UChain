package main;


import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import tool.Config;
import tool.Matrix;
import tool.Tool;
 
public class Sender {
	
	public static void broadcast() {
		Matrix matrix = new Matrix(4, 4);
		new Thread_Sender((Object)matrix,Config.objectType.Matrix).start();
	}
	
	
	public static void main(String[] args) throws Exception {
		for (int i = 0; i < 11; i++) {
			Socket socket = null;
			ObjectOutputStream outputStream = null;
			ObjectInputStream inputStream = null;
			
			try {
				socket = new Socket("localhost", 10000);
	
				outputStream = new ObjectOutputStream(socket.getOutputStream());
				Matrix m = new Matrix(1000,1000);
				outputStream.writeObject(m);
				outputStream.flush();
				
				inputStream = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
				Object obj = inputStream.readObject();
				if (obj != null) {
					m = (Matrix)obj;
					//System.out.println(m.toString());
					Tool.print("Done!");
				}
			} catch(IOException ex) {
				ex.printStackTrace();
			} finally {
				try {
					inputStream.close();
					outputStream.close();
					socket.close();
				} 
				catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}
}

class Thread_Sender extends Thread{
	Object obj;
	Config.objectType dataType;
	
	public Thread_Sender(Object obj,Config.objectType dataType) {
		this.dataType = dataType;
		switch (this.dataType) {
		case Matrix:{
			this.obj = (Matrix)obj;
			break;
		}
		case Block:{
			break;
		}
		default:
			break;
		}
	}
	public void run() {
		for(int i=Config.PORT_START; i<=Config.PORT_END; i++) {
			if(i == Config.PORT) { //avoid send message to itself
				continue;
			}
			else {
				Socket socket = null;
				ObjectOutputStream outputStream = null;
				ObjectInputStream inputStream = null;
				try {
					socket = new Socket("localhost", i);
					outputStream = new ObjectOutputStream(socket.getOutputStream());
					outputStream.writeObject(obj);
					outputStream.flush();
					
					inputStream = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
					Object receivedObj = inputStream.readObject();
					if (obj != null) {
						//m = (Matrix)obj;
						Tool.print("Done!");
					}
				} catch(IOException | ClassNotFoundException ex) {
					ex.printStackTrace();
				} finally {
					try {
						inputStream.close();
						outputStream.close();
						socket.close();
					} 
					catch(Exception ex) {
						ex.printStackTrace();
					}
				}
			}

		}
	}
}
