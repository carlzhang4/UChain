package communication;
import tool.*;
public class Processor {
	PreInfo preInfo;
	Problem problem;
	public Matrix matrix;
	public Processor(Object preObj, Object receivedObj) {
		preInfo = (PreInfo)preObj;
		if(preInfo.infoType == Config.objectType.Matrix) {
			matrix = (Matrix)receivedObj;
			Tool.print(matrix.toString());
		}
		else if(preInfo.infoType == Config.objectType.Problem) {
			problem = (Problem)receivedObj;
			Tool.print(problem.toString());
		}
		else if (preInfo.infoType == Config.objectType.Block) {
		}

	}
}
