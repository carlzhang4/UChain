package main;
import java.util.Map;

import sun.security.ec.ECKeyFactory;

public class Node{

	static Tool t;//tool class that contains print and so on
	static Node_Info myInfo;

//	public static void main(String[] args) throws Exception { //after node has been started, run main all the time
//        t = new Tool();
//        publishProblem("Path");
//        t.print("213");
//        
//        String inputStr = "abc";  
//        byte[] data = inputStr.getBytes();  
//  
//        Map<String, Object> keyMap = ECCCoder.initKey();  
//  
//        String publicKey = ECCCoder.getPublicKey(keyMap);  
//        String privateKey = ECCCoder.getPrivateKey(keyMap);  
//        System.err.println("公钥: \n" + publicKey);  
//        System.err.println("私钥： \n" + privateKey);  
//  
//        byte[] encodedData = ECCCoder.encrypt(data, publicKey);  
//  
//        byte[] decodedData = ECCCoder.decrypt(encodedData, privateKey);  
//  
//        String outputStr = new String(decodedData);  
//        System.err.println("加密前: " + inputStr + "\n\r" + "解密后: " + outputStr);  
//        
//  
//    }

    private Boolean bulidInfo(){
    	return null;
    }

    private static Node_Info scan(){

    	return null;
    }

    private static Boolean publishProblem(String path){ //publish a problem contains two matrix, return success or fail
		ProblemPublisher mPublisher = new ProblemPublisher();
		return false;
    }


}



class Node_Info{

}