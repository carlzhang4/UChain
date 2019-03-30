package blockchain;

import java.io.Serializable;
import java.util.Vector;

import security.Security;
import tool.Tool;

public class Chain implements Serializable {
    private static final long serialVersionUID = -7387603351327270119L;
    public Vector<Block> blocks = new Vector<Block>();


    public static void main(String[] args) throws Exception {
		Vector<AtomInfo> infos = new Vector<AtomInfo>();
		infos.add(new AtomInfo(Security.getPublicKey(), Security.getPublicKey(), 10, Security.getPrivateKey()));
		infos.add(new  AtomInfo(Security.getPublicKey(), Security.getPublicKey(), 13, Security.getPrivateKey(), 2, 4, 123));
		Block a = new Block(0,1,3,infos,Tool.gerCurrentPath());
        Block b = new Block(a.blockID, 1, 3, infos, Tool.gerCurrentPath());
        Chain chain = new Chain();
        chain.addBlock(a);
        chain.addBlock(b);
        Tool.print(chain.toString());

	}
    public Boolean ableAdd(Block newBlock){
        if(blocks.size() == 0 && newBlock.lastBlockID == 0)
            return true;
        else if(blocks.size() == 0){
            Tool.print("Chain is empty which can only add meta block!");
            return false;
        }
        if(blocks.get(blocks.size()-1).blockID == newBlock.lastBlockID)
            return true;
        else{
            Tool.print("lastBlockID does not match!");
            return false;
        }
    }
    public void addBlock(Block newBlock){
        if(ableAdd(newBlock))
            blocks.add(newBlock);
        else
            Tool.print("Unable to add blocks in this chain!");
    }
    public int getLength(){
        return blocks.size();
    }
    public String toString(){
        StringBuilder m = new StringBuilder();
        m.append(blocks.get(0).lastBlockID);
        for(int i=0;i<blocks.size();i++){
            m.append("<="+blocks.get(i).blockID);
        }
        m.append("\n");
        return m.toString();
    }
    public String toStringDetail(){
        StringBuilder m = new StringBuilder();
        m.append("Chain length:"+blocks.size()+"\n");
        for(int i=0;i<blocks.size();i++)
            m.append(blocks.get(i).toString());
        return m.toString();
    }
}