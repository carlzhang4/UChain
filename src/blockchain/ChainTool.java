package blockchain;

import communication.Matrix;
import communication.Task;

public class ChainTool {
    public static Chain merge(Chain originalChain,Chain newChain){
        if(originalChain.getLength() >= newChain.getLength())
            return originalChain;
        else{



            return null;
        }
    }

    public static Boolean verify(Task task, Matrix answer){
        
    }

}