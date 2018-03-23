package com.nwuer.utils;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("crpty")
public class Crpty {
	@Value("${key}")
	private String key;
	public String encrypt(String str) {
		try {
			
			SecureRandom random = new SecureRandom();
			DESKeySpec keySpec = new DESKeySpec(key.getBytes());
			SecretKeyFactory keyFacorty = SecretKeyFactory.getInstance("des");
			SecretKey secretKey = keyFacorty.generateSecret(keySpec);
			
			Cipher cipher = Cipher.getInstance("des");  
		    cipher.init(Cipher.ENCRYPT_MODE, secretKey, random);  
		    byte[] cipherData = cipher.doFinal(str.getBytes());  
		    String ciphertext = new Base64().encodeAsString(cipherData);
		    System.out.println("cipherText : " + ciphertext);  
		    System.out.println("cipherText Length : " + ciphertext.length());  
			
			return ciphertext;
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String decrypt(String str) {
		SecureRandom random = new SecureRandom();
		DESKeySpec keySpec;
		try {
			keySpec = new DESKeySpec(key.getBytes());
			SecretKeyFactory keyFacorty = SecretKeyFactory.getInstance("des");
			SecretKey secretKey = keyFacorty.generateSecret(keySpec);
			
			Cipher cipher = Cipher.getInstance("des"); 
			
		    cipher.init(Cipher.DECRYPT_MODE, secretKey, random);  
		    byte[] cipherData = new Base64().decode(str); 
		    byte[] plainData = cipher.doFinal(cipherData);  
		    System.out.println("plainText : " + new String(plainData));
		    
		    return new String(plainData);
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    
	    
		return null;
	}
}
