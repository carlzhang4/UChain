
package main;

import java.io.BufferedWriter;
import tool.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class NodeInfo {
	private final static String filePath = "./nodeinfo";
	private static ArrayList<String> checkList = new ArrayList<String>(){{add("/node");}}; 
	
	public static void run() throws Exception {
		if(infoExists()) {
			Tool.print("Info load success!");
		}
		else {
			buildInfo();
		}
		
	}
	
	private static void buildInfo() throws IOException{
			if(!Tool.fileExist(filePath)) {
				Tool.mkdir(filePath);
				Tool.print("Dir create success!");
			}
    		FileWriter nodeFW = new FileWriter(filePath + "/node");
    		BufferedWriter nodeBW = new BufferedWriter(nodeFW); 
    		nodeBW.write("123");
    		nodeBW.flush();  
    		nodeBW.close();  
            nodeFW.close(); 
            Tool.print("Info create success!");
    }
	private static boolean infoExists() {

		for (int i = 0; i < checkList.size(); i++){
			if(!Tool.fileExist(filePath+checkList.get(i))) {
				return false;
			}
		}
		return true;
	}
}
