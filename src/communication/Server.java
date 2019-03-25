package communication;


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
	        Config.PORT = 0;
		}
		else {
			Tool.print("The thread has already stopped!");
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
				new Thread_Socket(socket).start();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
}

class Thread_Socket extends Thread{
	Socket socket;
	ObjectInputStream inputStream = null;
	Object preObj = null;
	Object receivedObj = null;

	public Thread_Socket(Socket socket) {
		this.socket = socket;
	}
	
	public void run() {
		try {
			inputStream = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
			preObj = inputStream.readObject();
			receivedObj = inputStream.readObject();
			new Processor(preObj, receivedObj);
			Tool.print("Receive done!");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				inputStream.close();
				socket.close();
				Tool.print("Close Socket!");
			} 
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
