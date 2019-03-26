package communication;

import tool.Config;

public class PreInfo implements java.io.Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = 2839769766911909191L;
	private int port;
	private String publicKey;
	public Config.objectType infoType;
	public PreInfo(int port, String publicKey, Config.objectType infoType) {
		this.port = port;
		this.publicKey = publicKey;
		this.infoType = infoType;
	}

	public String toString() {
		StringBuilder m = new StringBuilder();
		m.append("PublicKey:"+publicKey+"\n");
		m.append("CurrentPort:"+port+"\n");
		m.append("InfoType:"+infoType+"\n");
		return m.toString();
	}
}
