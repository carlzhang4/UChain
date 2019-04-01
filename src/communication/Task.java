package communication;

import java.io.Serializable;

public class Task implements Serializable {
    private static final long serialVersionUID = 4867732956427242801L;
    public Problem problem;
    public String taskID;

    public String toString(){
        return taskID+problem.toString();
    }
}