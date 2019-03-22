package security;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.SignatureException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;


import tool.*;

public class Security {
	private static final String dirPath = "./nodeInfo/keyFile";
	public static final String SIGN_ALGORITHMS = "SHA1WithRSA";

	public static void test() throws Exception {  //Test API functions
		String pub = loadPublicKeyByFile();
		String pri = loadPrivateKeyByFile();
		String cipher = encrypt("123456712345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890", pub);
		String plain = decrypt(cipher, pri);
		Tool.print(cipher);
		Tool.print(plain);
		Tool.print(pub);
		Tool.print(pri + "\n\n");

		String signed = sign("123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890", pri);
		Boolean equal = verify_sign("123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890", signed, pub);
		Tool.print(equal);
	}

	public static void generateKey() throws NoSuchAlgorithmException, IOException, UException {

		Tool.mkdir(dirPath);
		KeyPairGenerator keyPairGen = null;
		keyPairGen = KeyPairGenerator.getInstance("RSA");
		// initialize generator,96-1024bits
		keyPairGen.initialize(1024, new SecureRandom());
		KeyPair keyPair = keyPairGen.generateKeyPair();
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();

		String publicKeyString = Base64.encode(publicKey.getEncoded());
		String privateKeyString = Base64.encode(privateKey.getEncoded());
		// write keyfile into files
		FileWriter pubfw = new FileWriter(dirPath + "/publicKey.keystore");
		FileWriter prifw = new FileWriter(dirPath + "/privateKey.keystore");
		BufferedWriter pubbw = new BufferedWriter(pubfw);
		BufferedWriter pribw = new BufferedWriter(prifw);
		pubbw.write(publicKeyString);
		pribw.write(privateKeyString);
		pubbw.flush();
		pubbw.close();
		pubfw.close();
		pribw.flush();
		pribw.close();
		prifw.close();
		Tool.print("key generate success!");
	}

	public static String encrypt(String plainText, String publicKeyString) throws Exception {
		RSAPublicKey publicKey = loadPublicKeyByStr(publicKeyString);
		Cipher cipher = null;

		try {
			cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			byte[] output = cipher.doFinal(plainText.getBytes());
			return Base64.encode(output);
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("No such algorithm!");
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
			return null;
		} catch (InvalidKeyException e) {
			throw new Exception("Illegal publicKey!");
		} catch (IllegalBlockSizeException e) {
			throw new Exception("PlainText is too long!");
		} catch (BadPaddingException e) {
			throw new Exception("PlainText is broken!");
		}
	}

	public static String decrypt(String cipherText, String privateKeyString) throws Exception {
		RSAPrivateKey privateKey = loadPrivateKeyByStr(privateKeyString);
		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			byte[] output = cipher.doFinal(Base64.decode(cipherText));
			return new String(output);
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("No such algorithm!");
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
			return null;
		} catch (InvalidKeyException e) {
			throw new Exception("Illegal privateKey!");
		} catch (IllegalBlockSizeException e) {
			throw new Exception("CipherText is too long!");
		} catch (BadPaddingException e) {
			throw new Exception("CipherText is broken!");
		}
	}

	public static String design(String cipherText, String publicKeyString) throws Exception { // abandoned code
		RSAPublicKey publicKey = loadPublicKeyByStr(publicKeyString);
		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.DECRYPT_MODE, publicKey);
			byte[] output = cipher.doFinal(Base64.decode(cipherText));
			return new String(output);
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("No such algorithm!");
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
			return null;
		} catch (InvalidKeyException e) {
			throw new Exception("Illegal privateKey!");
		} catch (IllegalBlockSizeException e) {
			throw new Exception("CipherText is too long!");
		} catch (BadPaddingException e) {
			throw new Exception("CipherText is broken!");
		}
	}

	public static String sign(String contentText, String privateKeyString) throws Exception {

		PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64.decode(privateKeyString));
		KeyFactory keyf = KeyFactory.getInstance("RSA");
		PrivateKey priKey = keyf.generatePrivate(priPKCS8);
		java.security.Signature signature = java.security.Signature.getInstance(SIGN_ALGORITHMS);
		signature.initSign(priKey);
		signature.update(contentText.getBytes());
		byte[] signed = signature.sign();
		return Base64.encode(signed);

	}

	public static Boolean verify_sign(String plainText, String signedText, String publicKeyString)
			throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, SignatureException {

		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		byte[] encodedKey = Base64.decode(publicKeyString);
		PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));

		java.security.Signature signature = java.security.Signature.getInstance(SIGN_ALGORITHMS);

		signature.initVerify(pubKey);
		signature.update(plainText.getBytes());
		signature.toString();

		boolean equal = signature.verify(Base64.decode(signedText));
		return equal;
	}

	public static String loadPublicKeyByFile() throws Exception {
		try {
			BufferedReader br = new BufferedReader(new FileReader(dirPath + "/publicKey.keystore"));
			String readLine = null;
			StringBuilder sb = new StringBuilder();
			while ((readLine = br.readLine()) != null) {
				sb.append(readLine);
			}
			br.close();
			return sb.toString();
		} catch (IOException e) {
			throw new UException("publicKey load error!");
		} catch (NullPointerException e) {
			throw new UException("publicKey file is null!");
		}
	}

	public static RSAPublicKey loadPublicKeyByStr(String publicKeyString) throws Exception {
		try {
			byte[] buffer = Base64.decode(publicKeyString);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
			return (RSAPublicKey) keyFactory.generatePublic(keySpec);
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("No such algorithm!");
		} catch (InvalidKeySpecException e) {
			throw new Exception("Invalid publicKey");
		} catch (NullPointerException e) {
			throw new Exception("publicKey data is null!");
		}
	}

	public static String loadPrivateKeyByFile() throws Exception {
		try {
			BufferedReader br = new BufferedReader(new FileReader(dirPath + "/privateKey.keystore"));
			String readLine = null;
			StringBuilder sb = new StringBuilder();
			while ((readLine = br.readLine()) != null) {
				sb.append(readLine);
			}
			br.close();
			return sb.toString();
		} catch (IOException e) {
			throw new Exception("privateKey load error!");
		} catch (NullPointerException e) {
			throw new Exception("privateKey file is null!");
		}
	}

	public static RSAPrivateKey loadPrivateKeyByStr(String privateKeyStr) throws Exception {
		try {
			byte[] buffer = Base64.decode(privateKeyStr);
			PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("No such algorithm!");
		} catch (InvalidKeySpecException e) {
			throw new Exception("Invalid privateKey");
		} catch (NullPointerException e) {
			throw new Exception("privateKey data is null!");
		}
	}

}
