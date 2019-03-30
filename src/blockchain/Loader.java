package blockchain;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;

import security.Security;
import tool.Tool;

public class Loader {
    private final static String filePathChain = "./nodeInfo/chainFile/chain";

    public static void main(String[] args) throws Exception {
        Vector<AtomInfo> infos = new Vector<AtomInfo>();
		infos.add(new AtomInfo(Security.getPublicKey(), Security.getPublicKey(), 10, Security.getPrivateKey()));
		infos.add(new  AtomInfo(Security.getPublicKey(), Security.getPublicKey(), 13, Security.getPrivateKey(), 2, 4, 123));
		Block a = new Block(0,1,3,infos,Tool.gerCurrentPath());
        Block b = new Block(a.blockID, 1, 3, infos, Tool.gerCurrentPath());
        Chain chain = new Chain();
        chain.addBlock(a);
        chain.addBlock(b);
        storeChain(chain);
        Tool.print(loadChain().toString());
    }
    public static Chain loadChain() throws IOException, ClassNotFoundException {
        if (!Tool.fileExist(filePathChain)) {
            return null;
        } else {

            FileInputStream fis = new FileInputStream(filePathChain);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Chain chain = (Chain) ois.readObject();
            Tool.print("chain loaded:" + chain.toString());
            ois.close();
            return chain;
        }
    }

    public static void storeChain(Chain chain) throws IOException {
        FileOutputStream fos=new FileOutputStream(filePathChain);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(chain);
		oos.close();
    }
}