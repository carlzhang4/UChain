package blockchain;

import java.io.Serializable;
import java.util.Vector;

import security.Security;
import tool.Tool;

public class Block implements Serializable{
	private static final long serialVersionUID = -8937603706541153658L;
	public long lastBlockID; //in
	public long blockID;
	public String time;  //in
	public int infoNumber;  //in
    public long timeInmillis;  //in
	public Vector<AtomInfo> infos = new Vector<AtomInfo>();  //in
	public String answerAddress;  //in
	public String minerAddress;  //in
	public String taskID;			//in



	public static void main(String[] args) throws Exception {
		Vector<AtomInfo> infos = new Vector<AtomInfo>();
		infos.add(new AtomInfo(Security.getPublicKey(), Security.getPublicKey(), 10, Security.getPrivateKey()));
		infos.add(new  AtomInfo(Security.getPublicKey(), Security.getPublicKey(), 13, Security.getPrivateKey(), 2, 4, serialVersionUID));
		Block a = new Block(123,"123",123,infos,Tool.gerCurrentPath());
		Tool.print(a.toString());
		Tool.print(a.verify(123));

	}

	//answerMatrixHash exists only when created
	public Block(long lastBlockID, String taskID,long answerMatrixHash, Vector<AtomInfo> infos, String answerAddress) throws Exception {
		this.lastBlockID = lastBlockID;
		this.infos = infos;
		time = Tool.getTime();
		timeInmillis = Tool.getTimeInMillis();
		infoNumber = infos.size();
		this.answerAddress = answerAddress;
		minerAddress = Security.loadPublicKeyByFile();
		this.taskID = taskID;
		this.blockID = (getHashContent()+answerMatrixHash).hashCode();
	}


    public Boolean verify(long answerMatrixHash)throws Exception {
        return (blockID)==(getHashContent()+answerMatrixHash).hashCode();
	}


	public String getHashContent(){  //compare with toString function, blockID is ignored
		StringBuilder m = new StringBuilder();
		m.append("{Block:\n");
		//m.append("BlockID:"+blockID+"\n");
		m.append("LastBlockID:"+lastBlockID+"\n");
		m.append("MinerAddress" + minerAddress + "\n");
		m.append("Time:" + time + timeInmillis + "\n");
		m.append("Answer_address:" + answerAddress + "\n");
		m.append("Task_ID:" + taskID + "\n");
		m.append("Total_info_number:" + infoNumber + "\n");
		for(int i=0;i<infos.size();i++){
			m.append("\nInfoNum_"+i+":\n");
			m.append(infos.get(i).toString());
		}
		m.append("}\n");
		return(m.toString());
	}

	public String toString(){
		StringBuilder m = new StringBuilder();
		m.append("{Block:\n");
		m.append("BlockID:"+blockID+"\n");
		m.append("LastBlockID:"+lastBlockID+"\n");
		m.append("MinerAddress" + minerAddress + "\n");
		m.append("Time:" + time + timeInmillis + "\n");
		m.append("Answer_address:" + answerAddress + "\n");
		m.append("Task_ID:" + taskID + "\n");
		m.append("Total_info_number:" + infoNumber + "\n");
		for(int i=0;i<infos.size();i++){
			m.append("\nInfoNum_"+i+":\n");
			m.append(infos.get(i).toString());
		}
		m.append("}\n");
		return(m.toString());
	}
	public boolean weekValid() throws Exception {
		for(int i=0;i<infos.size();i++){
			if(infos.get(i).valid() != true)
				return false;
		}
		return true;
	}
	public boolean strongValid(long answerMatrixHash) throws Exception {
		if(weekValid() != true || verify(answerMatrixHash) != true)
			return false;

		return true;
	}
}
