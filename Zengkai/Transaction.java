import java.security.interfaces.RSAPrivateKey;
import javax.crypto.Cipher;

import sun.security.provider.SHA3.SHA256;

class Transaction {

    private String in; // 付款方公钥
    private String out; // 收款方公钥
    private float count; // 数额
    private String inSignature; // 付款方签名

    public Transaction(String in, String out, float count, RSAPrivateKey privateKey) {
        this.in = in;
        this.out = out;
        this.count = count;

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        this.inSignature = new String(cipher.doFinal((SHA256(in + out + String.valueOf(count))).getBytes()));
    }

    public String getSHA256() {
        return SHA256(SHA256(in + out + String.valueOf(count) + inSignature));
    }

}