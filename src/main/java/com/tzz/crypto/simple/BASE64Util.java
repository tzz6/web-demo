package com.tzz.crypto.simple;

import java.io.IOException;

import org.junit.Test;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class BASE64Util {
	/**加密*/ 
	public static String encrypt(byte[] key) {  
		return (new BASE64Encoder()).encodeBuffer(key);  
	}
	
	/**解密*/
	public static String decrypt(String key) {  
		try {
			return new String((new BASE64Decoder()).decodeBuffer(key),"utf-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Test
	public void test() {
		String encryptStr = encrypt("123456".getBytes());
		System.out.println("加密："+encryptStr);
		System.out.println("解密："+decrypt(encryptStr));
	}
}
