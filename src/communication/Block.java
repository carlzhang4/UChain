package communication;

import java.io.Serializable;
import java.util.Vector;

public class Block implements Serializable{
	private static final long serialVersionUID = -8937603706541153658L;
	public int matrixHash;
	public int lastBlockHash;
	public Vector<Matrix> a = new Vector<>();
	
}
