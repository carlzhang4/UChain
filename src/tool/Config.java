package tool;

public class Config {
	public static final int PORT_START = 10000;
	public static final int PORT_END = 10010;
	public static int PORT = 0;
	public static int CONVINCING_LENGTH = 3;
	public static int INTERVAL = 2;

	public enum objectType{
		BLOCK,
		ATOMINFO,
		CHAIN
	}

}
