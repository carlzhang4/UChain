package main;


import security.Security;
import tool.*;

public class Node{


	public static void main(String[] args)  { //after node has been started, run main all the time
       try {
    	   NodeInfo.run();
    	   //ProblemPublisher.run();
    	   Security.test();
       } 
       catch (Exception e) {
    	 Tool.err(e.getMessage());
    	 e.printStackTrace();
       }
        
        
    }

}
