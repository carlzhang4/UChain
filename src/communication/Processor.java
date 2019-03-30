package communication;

import blockchain.*;
import tool.*;
public class Processor {
	PreInfo preInfo;
	public AtomInfo info;
	public Block block;
	public Chain chain;
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
		else if(preInfo.infoType == Config.objectType.CHAIN){
			chain = (Chain)receivedObj;
			Tool.print(chain.toString());
		}

	}
}
