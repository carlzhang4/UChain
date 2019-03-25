package communication;


import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import tool.Config;
import tool.Tool;
 
public class Sender {
	
	public static void broadcast() {
		Matrix matrix = new Matrix(4, 4);
		new Thread_Sender((Object)matrix,Config.objectType.Matrix).start();;
	}
	
	
	public static void main(String[] args) throws Exception {
		broadcast();
	}
}

class Thread_Sender extends Thread{
	Object obj;
	Config.objectType dataType;
	
	public Thread_Sender(Object obj,Config.objectType dataType) {
		this.dataType = dataType;
		this.obj = obj;
		switch (this.dataType) {
		case Matrix:{
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
		for(int i=Config.PORT_START; i<=10000; i++) {
			if(i == Config.PORT) { //avoid send message to itself
				continue;
			}
			else {
				Socket socket = null;
				ObjectOutputStream outputStream = null;
				try {
					socket = new Socket("localhost", i);
					Tool.print("Connected to port:"+i);
					outputStream = new ObjectOutputStream(socket.getOutputStream());
					outputStream.writeObject(new PreInfo(Config.PORT, "asdasd", Config.objectType.Problem));
					Problem problem = new Problem(new Matrix(4, 4), new Matrix(4, 4));
					problem.matrixA.change_value_at(0, 0, 4);
					outputStream.writeObject(problem);
					outputStream.flush();
				} catch(IOException ex) {
					ex.printStackTrace();
				} finally {
					try {
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
