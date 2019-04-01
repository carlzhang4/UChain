package communication;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Vector;

import blockchain.*;
import security.Security;
import tool.*;


public class Sender {
	public static void main(String[] args) throws Exception {
		Vector<AtomInfo> infos = new Vector<AtomInfo>();
		infos.add(new AtomInfo(Security.getPublicKey(), Security.getPublicKey(), 10, Security.getPrivateKey()));
		infos.add(new  AtomInfo(Security.getPublicKey(), Security.getPublicKey(), 13, Security.getPrivateKey(), 2, 4, 123));
		Block a = new Block(0,"23",3,infos,Tool.gerCurrentPath());
        Block b = new Block(a.blockID, "23", 3, infos, Tool.gerCurrentPath());
        Chain chain = new Chain();
        chain.addBlock(a);
		chain.addBlock(b);
		broadcast(chain);
	}
	public static void broadcast(Block block) {
		new Thread_Sender(block).start();
	}
	public static void broadcast(AtomInfo info){
		new Thread_Sender(info).start();
	}
	public static void broadcast(Chain chain){
		new Thread_Sender(chain).start();
	}
}

class Thread_Sender extends Thread{
	Block block;
	AtomInfo info;
	Chain chain;
	Config.objectType dataType;

	public Thread_Sender(Block block) {
		this.dataType = Config.objectType.BLOCK;
		this.block = block;
	}
	public Thread_Sender(AtomInfo info){
		this.dataType = Config.objectType.ATOMINFO;
		this.info = info;
	}
	public Thread_Sender(Chain chain){
		this.dataType = Config.objectType.CHAIN;
		this.chain = chain;
	}

	public void run() {
		for(int i=Config.PORT_START; i<=Config.PORT_END; i++) {
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

					if(dataType == Config.objectType.BLOCK){
						outputStream.writeObject(new PreInfo(Config.PORT, "asdasd", Config.objectType.BLOCK));
						outputStream.writeObject(block);
						Tool.print("Send Block message to port:"+i);
					}
					else if(dataType == Config.objectType.ATOMINFO){
						outputStream.writeObject(new PreInfo(Config.PORT, "asdasd", Config.objectType.ATOMINFO));
						outputStream.writeObject(info);
						Tool.print("Send AtomInfo message to port:"+i);
					}
					else if(dataType == Config.objectType.CHAIN){
						outputStream.writeObject(new PreInfo(Config.PORT, "asdasd", Config.objectType.CHAIN));
						outputStream.writeObject(chain);
						Tool.print("Send Chain message to port:"+i);
					}
					outputStream.flush();
				} catch(IOException ex) {
					//ex.printStackTrace();
				} finally {
					try {
						outputStream.close();
						socket.close();
					}
					catch(Exception ex) {
						//ex.printStackTrace();
					}
				}
			}
		}
	}
}
