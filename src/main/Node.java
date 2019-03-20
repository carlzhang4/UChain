package main;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Map;

import sun.security.ec.ECKeyFactory;

public class Node{


	public static void main(String[] args) throws Exception { //after node has been started, run main all the time
        publishProblem("Path");
        NodeInfo.run();
        

  
    }



    private static Boolean publishProblem(String path){ //publish a problem contains two matrix, return success or fail
		ProblemPublisher mPublisher = new ProblemPublisher();
		return false;
    }


}
