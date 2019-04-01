package blockchain;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;

import communication.Matrix;
import communication.Problem;
import communication.Task;
import security.Security;
import tool.Tool;

public class Loader {
    private final static String filePathChain = "./nodeInfo/chainFile/chain";
    private final static String filePathMyProblem = "./nodeInfo/myProblem/problem";
    private final static String dirPathAnswer = "./nodeInfo/answer/";
    private final static String dirPathTask = "./nodeInfo/task/";


    public static void main(String[] args) throws Exception {
        // Tool.initDir();
        // Vector<AtomInfo> infos = new Vector<AtomInfo>();
        // infos.add(new AtomInfo(Security.getPublicKey(), Security.getPublicKey(), 10,
        // Security.getPrivateKey()));
        // infos.add(new AtomInfo(Security.getPublicKey(), Security.getPublicKey(), 13,
        // Security.getPrivateKey(), 2, 4,
        // 123));
        // Block a = new Block(0, 1, 3, infos, Tool.gerCurrentPath());
        // Block b = new Block(a.blockID, 1, 3, infos, Tool.gerCurrentPath());
        // Chain chain = new Chain();
        // chain.addBlock(a);
        // chain.addBlock(b);
        // storeChain(chain);
        // Tool.print(loadChain().toString());
        generateProblem();
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
        FileOutputStream fos = new FileOutputStream(filePathChain);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(chain);
        oos.close();
    }

    public static Problem loadMyProblem() throws IOException, ClassNotFoundException {
        if (!Tool.fileExist(filePathMyProblem)) {
            return null;
        } else {
            FileInputStream fis = new FileInputStream(filePathMyProblem);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Problem myProblem = (Problem) ois.readObject();
            Tool.print("chain loaded:" + myProblem.toString());
            ois.close();
            return myProblem;
        }
    }

    public static void generateProblem() throws IOException {
        Matrix mA = Tool.inputAMatrix();
        Matrix mB = Tool.inputAMatrix();
        Problem problem = new Problem(mA, mB);
        FileOutputStream fos = new FileOutputStream(filePathMyProblem);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(problem);
        oos.close();
    }

    public static Task loadTask(String taskID) throws IOException, ClassNotFoundException {
        if (!Tool.fileExist(dirPathTask + taskID)) {
            return null;
        } else {
            FileInputStream fis = new FileInputStream(dirPathTask + taskID);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Task task = (Task) ois.readObject();
            Tool.print("Task loaded:" + task.toString());
            ois.close();
            return task;
        }
    }
    public static Matrix loadAnswer(String taskID) throws IOException, ClassNotFoundException {
        if (!Tool.fileExist(dirPathAnswer + taskID)) {
            return null;
        } else {
            FileInputStream fis = new FileInputStream(dirPathAnswer + taskID);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Matrix matrix = (Matrix) ois.readObject();
            Tool.print("Answer loaded:" + matrix.toString());
            ois.close();
            return matrix;
        }
    }
}