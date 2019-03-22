package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import security.Base64;

public class DESTest {

	static final String KeyPath = "DESTest/DESkey.dat";
	static final String algorithm="DES";
	static SecretKey DESkey;
	
	public static void main(String[] args) {
		DESTest des = new DESTest();
		long startTime, endTime;
		startTime =  System.currentTimeMillis();
		String encrypt_out = des.encrypt("DESTest/plain.txt");
		//System.out.println(encrypt_out);
		//des.encrypt("DESTest/plain.txt");
		endTime =  System.currentTimeMillis();
		System.out.println("Encrypt Time used: "+(endTime-startTime)+"ms");
		
		try{
			FileOutputStream out = new FileOutputStream(new File("DESTest/cipher.txt"));
			out.write(Base64.decode(encrypt_out));
			out.close();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		startTime =  System.currentTimeMillis();
		des.decrypt("DESTest/cipher.txt");
		endTime =  System.currentTimeMillis();
		System.out.println("Decrypt Time used: "+(endTime-startTime)+"ms");
	}
	
	public static void run() {
		long startTime, endTime;
		startTime =  System.currentTimeMillis();
	}
	
	public static void generateKey() {
		
	}
	
	public String decrypt(String input){
		try{
			String ciphertext = Base64.encode(readInbyte(input));
			
			//use the ciper text in a byte type from the father function
			if(!(new File(KeyPath)).exists()){
				System.out.println("can not find the DES key!");
				return null;
			}
			else{
				ObjectInputStream in = new ObjectInputStream(new FileInputStream(KeyPath));
				DESkey = (SecretKey)in.readObject();
				in.close();
			}
			Cipher cipher = Cipher.getInstance(algorithm);
			cipher.init(Cipher.DECRYPT_MODE, DESkey);

			byte[] output = cipher.doFinal(Base64.decode(ciphertext));
			return new String(output);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
	public String encrypt(String input)
	{
		try{
			BufferedReader in_clear = new BufferedReader(new InputStreamReader(new FileInputStream(input)));
			StringBuffer content = new StringBuffer();
			String s="";
			while((s=in_clear.readLine())!=null){
				content.append(s);
			}
			in_clear.close();
			
			String cleartext=content.toString();
			if(!(new File(KeyPath)).exists()){
				System.out.println("creating DES key");
				KeyGenerator keygen=KeyGenerator.getInstance(algorithm);
				DESkey = keygen.generateKey();
				
				ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(KeyPath));
				outputStream.writeObject(DESkey);
				outputStream.close();
			}
			else{
				System.out.println("read DES key");
				ObjectInputStream in = new ObjectInputStream(new FileInputStream(KeyPath));
				DESkey = (SecretKey)in.readObject();
				in.close();
			}
			Cipher c1=Cipher.getInstance(algorithm);
			c1.init(Cipher.ENCRYPT_MODE, DESkey);
			byte[] output = c1.doFinal(cleartext.getBytes());
			return Base64.encode(output);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	public byte[] readInbyte(String inputname)
	{
		try{
			File file = new File(inputname);
			FileInputStream in = new FileInputStream(file);
			long filesize = file.length();
			byte[] readin = new byte[(int)filesize];
			
			int offset=0;
			int numRead=0;
			
			while(offset<readin.length&&(numRead = in.read(readin, offset, readin.length-offset))>=0){
				offset+=numRead;
			}
			if(offset!=readin.length){
				throw new IOException("can not read completely of file :"+file.getName());
			}
			in.close();
			return readin;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
}
