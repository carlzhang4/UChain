
import java.io.*;   
import java.net.*;
import java.util.Scanner;   
public class Server { 
    static int port;
    static boolean flag = false;
    public static void main(String[] args) throws IOException{  
    	Socket so;
    	ServerSocket server = null;
    	//int port = 0;
    	for (int i = 10001; i <10010; i++){
    	    try {
    	      server = new ServerSocket(i);    
    	      port=i;
    	      break;
    	    }
    	    catch (IOException ex) {
    	      System.out.println("端口﹕" + i + " 已被占用");
    	    }
    	}
    	for(int i=10001; i<port; i++)
    	{
    		try { 
                //创建连接指定服务器和端口的Socket
               so= new Socket("localhost",i);
               S_Thread thread = new S_Thread(so) ; //开启一个线程处理连接
               thread.start() ;
    		}
            catch(Exception e) {            
            }
    	}
        //ServerSocket server=new ServerSocket(port); //创建绑定到特定端口的服务器套接字。  
        /*try { 
            //创建连接指定服务器和端口的Socket
           Socket so= new Socket("localhost",10086);
           System.out.println("...Connect"); 
           //创建键盘输入的数据流
           usein  = new  BufferedReader(new InputStreamReader(System.in));
           //创建发送到服务器端的数据流
           out= new PrintWriter(so.getOutputStream());

            boolean flag=true;
            while(flag) {           

                String theLine=usein.readLine();
                //通过流发送信息给服务器端
                out.println( theLine);
                out.flush();

                BufferedReader in=new BufferedReader(new InputStreamReader(so.getInputStream())); 
                String str=in.readLine();   
                System.out.println("Server:"+str); 

                if(theLine.equals("end")){
                    so.close() ;
                    break ;
                }       
             }  
            }
        catch(Exception e) {            
        }
    }*/
        System.out.println("....Service Start!");
        Con_Thread cthread = new Con_Thread();
        cthread.start();
        while(true){   
            Socket client=server.accept(); //等待客户连接请求
            S_Thread thread = new S_Thread(client) ; //开启一个线程处理连接
            thread.start() ;
        } 
    }  
}

class S_Thread extends Thread{
    Socket client ;
    public S_Thread(Socket client){
        this.client = client ;
        Dis_Thread dthread = new Dis_Thread(client);
        dthread.start();
    }
    public void run() {     
        //BufferedReader in = null;
        PrintWriter out = null;// 获取写往客户端的输出流,true表示自动刷新 
        BufferedReader usein = null;
        try {
            //in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new PrintWriter(client.getOutputStream(),true);
            usein  = new  BufferedReader(new InputStreamReader(System.in));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }   
        while(true){   
            //String str = null;
            try {
            	 //String theLine=usein.readLine();
                 //通过流发送信息给服务器端
            	 if(Server.flag)
            	 {
            		 out.println("a from "+Server.port);
            		 out.flush();
            		 this.sleep(1000);
            	 }                
                //str = in.readLine();
            } /*catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } */catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}   
            //System.out.println("Client: "+str);   
            //out.println("has receive....");   
            //out.flush();  
            /*if(str.equals("end")){
                System.out.println("Client Close!") ;
                break ;
            }*/
        }
        /*try {
            client.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/
    }
}

class Dis_Thread extends Thread{
    Socket client ;
    public Dis_Thread(Socket client){
        this.client = client ;
    }
    public void run() {     
        BufferedReader in = null;
        //PrintWriter out = null;// 获取写往客户端的输出流,true表示自动刷新 
        //BufferedReader usein = null;
        try {
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            //out = new PrintWriter(client.getOutputStream(),true);
            //usein  = new  BufferedReader(new InputStreamReader(System.in));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }   
        while(true){   
            String str = null;
            try {
            	 /*String theLine=usein.readLine();
                 //通过流发送信息给服务器端
                 out.println(theLine+" from "+Server.port);
                 out.flush();
                 this.sleep(1000);*/
                str = in.readLine();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } /*catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}   */
            System.out.println("Client: "+str);   
            //out.println("has receive....");   
            //out.flush();  
            if(str.equals("end")){
                System.out.println("Client Close!") ;
                break ;
            }
        }
        try {
            client.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

class Con_Thread extends Thread{
	public void run() { 
		while(true)
		{
			Scanner in = new Scanner(System.in);
			int x = in.nextInt();
			if(x==1)
			{
				Server.flag=true;
			}
			else
			{
				Server.flag=false;
			}
		}
	}
}

