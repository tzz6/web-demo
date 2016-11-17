package com.tzz.crypto.certificate;

import org.junit.Test;

public class SignCertTest {


	/** 证书签名*/
	@Test
	public void sign() {
		try {
			SignCert signCert = new SignCert();
			signCert.sign();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}