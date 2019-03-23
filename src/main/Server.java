package main;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import tool.*;

 
public class Server {
	static ServerSocket server;
	static Thread_Server thread;
	public static void main(String[] args) throws IOException {
		start();
	}
	public static void start() throws IOException {
		for(int i=Config.PORT_START; i<=Config.PORT_END; i++) {
			try {
				server = new ServerSocket(i);
				Config.PORT = i;
				Tool.print("Server started at port:"+Config.PORT);
				break;
			}
			catch (Exception e) {
				Tool.print("port "+i+" has been used!");
			}
		}
		thread = new Thread_Server(server);
		thread.start();
		while (true) {
		}
	}
	public static void stop() throws InterruptedException {
		if(thread != null) {
			thread.interrupt();
	        thread.join();
	        thread = null;
	        server = null;
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
					ObjectInputStream inputStream = null;
					ObjectOutputStream outputStream = null;
					try {
						inputStream = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
						outputStream = new ObjectOutputStream(socket.getOutputStream());
						Object obj = inputStream.readObject();
						//TO DO
						Matrix m = (Matrix)obj;
						Tool.print(m.toString());
						m.change_value_at(0, 0, 3);
						outputStream.writeObject(m);
						outputStream.flush();
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						try {
							inputStream.close();
							outputStream.close();
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
