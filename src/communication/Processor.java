package communication;
import tool.*;
public class Processor {
	PreInfo preInfo;
	public AtomInfo info;
	public Block block;
	public Processor(Object preObj, Object receivedObj) {
		preInfo = (PreInfo)preObj;
		Tool.print(preInfo.toString());
		if(preInfo.infoType == Config.objectType.BLOCK) {
			block = (Block)receivedObj;
			Tool.print(block.toString());
		}
		else if(preInfo.infoType == Config.objectType.ATOMINFO) {
			info = (AtomInfo)receivedObj;
			Tool.print(info.toString());
		}

	}
}
