package com.tzz.crypto.ssl;


import java.io.DataInputStream;
import java.io.InputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.junit.Test;

public class CertificateCoderTest {
	
	private String clientKeyStorePath = "d:/tzz-sf.keystore";
	private String clientPassword = "123456789";
	
	@Test
	public void testHttps() throws Exception {
		URL url = new URL("https://www.zlex.org/examples/");
		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();

		conn.setDoInput(true);
		conn.setDoOutput(true);

		CertificateCoder.configSSLSocketFactory(conn, clientPassword,
				clientKeyStorePath, clientKeyStorePath);

		InputStream is = conn.getInputStream();

		int length = conn.getContentLength();

		DataInputStream dis = new DataInputStream(is);
		byte[] data = new byte[length];
		dis.readFully(data);

		dis.close();
		System.err.println(new String(data));
		conn.disconnect();
	}
}