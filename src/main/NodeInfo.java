
package main;

import java.io.BufferedWriter;
import tool.*;
import java.io.FileWriter;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import security.*;


public class NodeInfo {
	private final static String dirPath = "./nodeInfo";
	private static ArrayList<String> checkList = new ArrayList<String>()
		{
		private static final long serialVersionUID = 1L;

		{
			add("/node");
			add("/keyFile/publicKey.keystore");
			add("/keyFile/privateKey.keystore");
		}};

	public static void run() throws Exception {
		if(infoExists()) {
			Tool.print("Info load success!");
		}
		else {
			buildInfo();
		}

	}

	private static void buildInfo() throws IOException, NoSuchAlgorithmException, UException{
			if(!Tool.fileExist(dirPath)) {
				Tool.mkdir(dirPath);
				Tool.print("Dir create success!");
			}

			for (int i = 0; i < checkList.size(); i++){

				if(!Tool.fileExist(dirPath+checkList.get(i))) {
					Tool.print("lack of "+dirPath+checkList.get(i));

					switch (checkList.get(i)) {
						case "/node":{
							FileWriter nodeFW = new FileWriter(dirPath + "/node");
				    		BufferedWriter nodeBW = new BufferedWriter(nodeFW);
				    		nodeBW.write("123");
				    		nodeBW.flush();
				    		nodeBW.close();
				            nodeFW.close();
				            Tool.print("File node create success!");
							break;
						}
						case "/keyFile/publicKey.keystore":
						case "/keyFile/privateKey.keystore":{
							Security.generateKey();
							break;
						}
					}
				}
			}

    }

	private static boolean infoExists() {
		for (int i = 0; i < checkList.size(); i++){
			if(!Tool.fileExist(dirPath+checkList.get(i))) {
				return false;
			}
		}
		return true;
	}
}
