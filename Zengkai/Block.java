import java.util.List;

import sun.security.provider.SHA3.SHA256;

class Block {

    private final static String VERSION = "1.0"; // 当前版本

    private String blockHash; // 区块哈希值
    private String previousHash; // 上一个区块的哈希
    private String timeStamp; // 时间戳
    private String blockVersion; // 版本
    private Work workProof; // 工作证明
    private String merkleRoot; // Merkle树的根

    private List<Transaction> transactions;
    private List<String> merkleTree;

    public Block(String previousHash, Work workProof) {
        this.previousHash = previousHash;
        this.timeStamp = String.valueOf(System.currentTimeMillis()); // TODO: 安全隐患
        this.blockVersion = VERSION;
        this.workProof = workProof;
        this.merkleRoot = null;
        this.blockHash = getBlockHash();
    }

    public String getBlockHash() {
        return SHA256(SHA256(previousHash + timeStamp + blockVersion + workProof.toString() + merkleRoot)); // 两次SHA256
    }

    public static boolean isLegalBlock(Block block) {
        String blockHash = SHA256(SHA256(block.previousHash + block.timeStamp + block.blockVersion
                + block.workProof.toString() + block.merkleRoot));
        return blockHash.equals(block.blockHash);
    }

}
