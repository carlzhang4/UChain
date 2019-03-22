package main;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import tool.Matrix;
import tool.Tool;
 
public class Server {
	static ServerSocket server;
	static Thread_Server thread;
	
	public static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket(10000);
		new Thread_Server(server).start();;
		while (true) {
			
		}
	}
	public static void run() throws IOException {
		server = new ServerSocket(10000);
		thread = new Thread_Server(server);;
		thread.start();
		while (true) {
			
		}
	}
	public static void stop() throws InterruptedException {
		if(thread != null) {
			thread.interrupt();
	        thread.join();
	        thread = null;
		}
		else {
			Tool.print("The thread has already been stopped!");
		}
	}
 
}
class Thread_Server extends Thread{
	
	ServerSocket server;
	Socket socket;
	
	public Thread_Server(ServerSocket server) {
		this.server = server;
	}
	
	public void run(){
		while (true) {
			
			try {
				socket = server.accept();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			new Thread(new Runnable() {
				public void run() {
					ObjectInputStream is = null;
					ObjectOutputStream os = null;
					try {
						is = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
						os = new ObjectOutputStream(socket.getOutputStream());
	 
						Object obj = is.readObject();
						Matrix m = (Matrix)obj;
						Tool.print(m.toString());
						m.change_value_at(0, 0, 3);
						os.writeObject(m);
						os.flush();
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						try {
							is.close();
							os.close();
							socket.close();
						} 
						catch(Exception e) {
							e.printStackTrace();
						}

					}
				}
			}).start();
		}
	}
}
