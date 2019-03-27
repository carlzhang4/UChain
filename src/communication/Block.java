package communication;

import java.io.Serializable;
import java.util.Vector;

import security.Security;
import tool.Tool;

public class Block implements Serializable{
	private static final long serialVersionUID = -8937603706541153658L;
	public long matrixHash;
	public long lastBlockID;
	public long blockID;
	public long withAnswerHash;
	public String time;
	public int infoNumber;
    public long timeInmillis;
	public Vector<AtomInfo> infos = new Vector<AtomInfo>();
	public String answerAddress;
	public String minerAddress;


	public static void main(String[] args) throws Exception {
		Vector<AtomInfo> infos = new Vector<AtomInfo>();
		infos.add(new AtomInfo(Security.getPublicKey(), Security.getPublicKey(), 10, Security.getPrivateKey()));
		infos.add(new  AtomInfo(Security.getPublicKey(), Security.getPublicKey(), 13, Security.getPrivateKey(), 2, 4, serialVersionUID));
		Block a = new Block(123,123,infos,Tool.gerCUrrentPath());
		Tool.print(a.toString());
		Tool.print(a.verify(123));

	}

	public Block(long lastBlockID, long matrixHash, Vector<AtomInfo> infos, String answerAddress) throws Exception {
		this.lastBlockID = lastBlockID;
		this.matrixHash = matrixHash;
		this.infos = infos;
		time = Tool.getTime();
		timeInmillis = Tool.getTimeInMillis();
		infoNumber = infos.size();
		this.answerAddress = answerAddress;
		minerAddress = Security.loadPublicKeyByFile();
		this.withAnswerHash = (toString()+matrixHash).hashCode();
	}


    public Boolean verify(long matrixHash)throws Exception {
        return (withAnswerHash)==(toString()+matrixHash).hashCode();
	}

	public Boolean verify_info() throws Exception {
		for(int i=0;i<infos.size();i++){
			if(infos.get(i).verify_sign() == false)
				return false;
		}
		return true;
	}

	public String toString(){
		StringBuilder m = new StringBuilder();
		m.append("{Block:\n");
		m.append("BlockID:"+blockID+"\n");
		m.append("LastBlockID:"+lastBlockID+"\n");
		m.append("MinerAddress" + minerAddress + "\n");
		m.append("Time:" + time + timeInmillis + "\n");
		m.append("Total_info_number:" + infoNumber + "\n");
		m.append("Answer_address:" + answerAddress + "\n");
		for(int i=0;i<infos.size();i++){
			m.append("\nInfoNum:"+i+"\n");
			m.append(infos.get(i).toString());
		}
		m.append("}\n");
		return(m.toString());
	}
}
