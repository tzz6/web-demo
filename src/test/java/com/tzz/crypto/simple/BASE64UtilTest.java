package com.tzz.crypto.simple;

import org.junit.Test;

public class BASE64UtilTest {
	@Test
	public void test() {
		String encryptStr = BASE64Util.encrypt("123456".getBytes());
		System.out.println("加密："+encryptStr);
		System.out.println("解密："+BASE64Util.decrypt(encryptStr));
	}
}