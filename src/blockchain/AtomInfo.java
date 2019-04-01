package blockchain;

import java.io.Serializable;

import security.Security;
import tool.Tool;

public class AtomInfo implements Serializable {
    private static final long serialVersionUID = 8721091622416538273L;
    public String srcAddress;
    public String destAddress;
    public double amount;
    public TYPE type;
    public String signedText;
    public String time;
    public long timeInmillis;
    public int col,row;
    public long cheatBlockID;

    public enum TYPE {
        TRANSACTION, CHEAT
    };

    public static void main(String[] args) throws Exception {
        AtomInfo a = new AtomInfo(Security.getPublicKey(), Security.getPublicKey(), 10, Security.getPrivateKey());
        Tool.print(a.toString());
        Tool.print(a.valid());
        AtomInfo b = new  AtomInfo(Security.getPublicKey(), Security.getPublicKey(), 13, Security.getPrivateKey(), 2, 4, 0);
        Tool.print(b.toString());
        Tool.print(b.valid());
    }

    public AtomInfo(String srcAddress, String destAddress, double amount, String privateKey)throws Exception {//transaction
        this.type = TYPE.TRANSACTION;
        this.srcAddress = srcAddress;
        this.destAddress = destAddress;
        this.amount = amount;
        time = Tool.getTime();
        timeInmillis = Tool.getTimeInMillis();
        sign(privateKey);
    }


    //CheatInfo
    public AtomInfo(String srcAddress, String destAddress, double amount, String privateKey, int col, int row, long cheatBlockID ) throws Exception {
        this.type = TYPE.CHEAT;
        this.srcAddress = srcAddress;
        this.destAddress = destAddress;
        this.amount = amount;
        time = Tool.getTime();
        timeInmillis = Tool.getTimeInMillis();
        this.col = col;
        this.row = row;
        this.cheatBlockID = cheatBlockID;
        sign(privateKey);
    }

    public String toString() {
        StringBuilder m = new StringBuilder();
        m.append("\n[AtomInfo:\n");
        m.append("TYPE:" + type + "\n");
        m.append("Time:" + time + timeInmillis + "\n");
        m.append("SRC:" + srcAddress + "\n");
        m.append("DEST:" + destAddress + "\n");
        m.append("AMOUNT:" + amount);
        if(type == TYPE.CHEAT){
            m.append("\nCOL:" + col + "\n");
            m.append("ROW:" + col + "\n");
            m.append("CHEAT_BLOCK_ID:" + cheatBlockID);
        }
        m.append("]\n");
        return m.toString();
    }

    public void sign(String privateKey) throws Exception {
        signedText = Security.sign(this.toString(), privateKey);
    }

    public Boolean valid()throws Exception {
        return Security.verify_sign(this.toString(), signedText, srcAddress);
    }

}