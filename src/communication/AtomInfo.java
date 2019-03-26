package communication;

import security.Security;
import tool.Tool;

class AtomInfo {
    public double amount;
    public String srcAddress;
    public String destAddress;
    public TYPE type;
    public String signedText;
    public String time;
    public long timeInmillis;

    public enum TYPE {
        TRANSACTION, CHEAT
    };

    public static void main(String[] args) throws Exception {
        AtomInfo a = new AtomInfo(Security.getPublicKey(), Security.getPublicKey(), 10, Security.getPrivateKey());
        Tool.print(a.toString());
        Tool.print(a.verify_sign());
    }

    public AtomInfo(String srcAddress, String destAddress, double amount, String privateKey)throws Exception {
        this.type = TYPE.TRANSACTION;
        this.srcAddress = srcAddress;
        this.destAddress = destAddress;
        this.amount = amount;
        time = Tool.getTime();
        timeInmillis = Tool.getTimeInMillis();
        sign(privateKey);
    }

    public String toString() {
        StringBuilder m = new StringBuilder();
        m.append("TYPE:" + type + "\n");
        m.append("Time" + time + timeInmillis + "\n");
        m.append("SRC:" + srcAddress + "\n");
        m.append("DEST:" + destAddress + "\n");
        m.append("AMOUNT:" + amount);
        return m.toString();
    }

    public void sign(String privateKey) throws Exception {
        signedText = Security.sign(this.toString(), privateKey);
    }

    public Boolean verify_sign()throws Exception {
        return Security.verify_sign(this.toString(), signedText, srcAddress);
    }
    public String signedText(){
        return signedText;
    }

}