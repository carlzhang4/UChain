public class Node{

	static T t;//tool class that contains print and so on
	static Node_Info myInfo;

	public static void main(String[] args) { //after node has been started, run main all the time
        t = new T();
        int[] a = new int[3];
        int[] b = new int[3];
        a[0] = 0;
        a[1] = 1;
        a[2] = 2;
        b[0] = 0;
        b[1] = 1;
        b[2] = 2;
        publishProblem(a,b);
    }

    private Boolean bulidInfo(){
    	return null;
    }

    private static Node_Info scan(){

    	return null;
    }

    private static Boolean publishProblem(int[] a, int[] b){ //publish a problem contains two matrix, return success or fail
		if(a.length == b.length)
			return true;
		else 
			return false;
    }


}

class T{
	public static void print(String content){
		System.out.println(content);
	}
	public static void print(int content){
		System.out.println(content);
	}
	public static void print(double content){
		System.out.println(content);
	}
	public static void print(float content){
		System.out.println(content);
	}
}

class Node_Info{

}