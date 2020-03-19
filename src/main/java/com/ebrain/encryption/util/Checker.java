package com.ebrain.encryption.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Checker {
	public static void main(String[] args) throws Exception {

		
		String key = generateEncryptedKey("US Open Tennis Championship");
		
    }
	
	
	public static void generate(String key) throws Exception {

        String password = key;
        String passwordEnc = AESencrp.encrypt(password);
        String passwordDec = AESencrp.decrypt(passwordEnc);

        System.out.println("Plain Text : " + password);
        System.out.println("Encrypted Text : " + passwordEnc.toUpperCase());
        System.out.println("Decrypted Text : " + passwordDec);
    }
	
	
	public static String generateEncryptedKey(String keyToHash) {
        String encryptedKey = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(keyToHash.getBytes());
            //Get the hash's bytes 
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 30).substring(1));
            }
            //Get complete hashed password in hex format
            encryptedKey = sb.toString();
        } 
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        System.out.println(encryptedKey.toUpperCase());
        return encryptedKey.toUpperCase();
    }
}