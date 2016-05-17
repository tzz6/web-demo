package com.tzz.crypto.dh;

import java.io.IOException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.SecretKey;
import javax.crypto.interfaces.DHPrivateKey;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import org.junit.Test;


public class DHUtil {
	
	public static final String ALGORITHM = "DH";
	/**默认密钥字节数*/
	private static final int KEY_SIZE = 1024;

	/** DH加密下需要一种对称加密算法对数据加密，这里我们使用DES，也可以使用其他对称加密算法*/
	public static final String SECRET_ALGORITHM = "DES";
	private static final String PUBLIC_KEY = "DHPublicKey";//公钥
	private static final String PRIVATE_KEY = "DHPrivateKey";//私钥

	public String encryptBASE64(byte[] key) {  
		return (new BASE64Encoder()).encodeBuffer(key);  
	}
	
	public byte[] decryptBASE64(String key) throws IOException {  
		return new BASE64Decoder().decodeBuffer(key);
	}
	
	/** 初始化甲方密钥 */
	public Map<String, Object> initKey() throws Exception {
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);
		keyPairGenerator.initialize(KEY_SIZE);
		KeyPair keyPair = keyPairGenerator.generateKeyPair();
		// 甲方公钥
		DHPublicKey publicKey = (DHPublicKey) keyPair.getPublic();
		// 甲方私钥
		DHPrivateKey privateKey = (DHPrivateKey) keyPair.getPrivate();
		Map<String, Object> keyMap = new HashMap<String, Object>(2);
		keyMap.put(PUBLIC_KEY, publicKey);
		keyMap.put(PRIVATE_KEY, privateKey);
		return keyMap;
	}

	/**初始化乙方密钥*/
	public Map<String, Object> initKey(String key) throws Exception {
		// 解析甲方公钥
		byte[] keyBytes = decryptBASE64(key);
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
		PublicKey pubKey = keyFactory.generatePublic(x509KeySpec);
		// 由甲方公钥构建乙方密钥
		DHParameterSpec dhParamSpec = ((DHPublicKey) pubKey).getParams();
		KeyPairGenerator keyPairGenerator = KeyPairGenerator
				.getInstance(keyFactory.getAlgorithm());
		keyPairGenerator.initialize(dhParamSpec);
		KeyPair keyPair = keyPairGenerator.generateKeyPair();
		// 乙方公钥
		DHPublicKey publicKey = (DHPublicKey) keyPair.getPublic();
		// 乙方私钥
		DHPrivateKey privateKey = (DHPrivateKey) keyPair.getPrivate();
		Map<String, Object> keyMap = new HashMap<String, Object>(2);
		keyMap.put(PUBLIC_KEY, publicKey);
		keyMap.put(PRIVATE_KEY, privateKey);

		return keyMap;
	}

	/**
	 * 加密<br>
	 * 
	 * @param data
	 *            待加密数据
	 * @param publicKey
	 *            甲方公钥
	 * @param privateKey
	 *            乙方私钥
	 * @return
	 * @throws Exception
	 */
	public byte[] encrypt(byte[] data, String publicKey, String privateKey) throws Exception {
		// 生成本地密钥
		SecretKey secretKey = getSecretKey(publicKey, privateKey);
		// 数据加密
		Cipher cipher = Cipher.getInstance(secretKey.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		return cipher.doFinal(data);
	}

	/**
	 * 解密<br>
	 * 
	 * @param data
	 *            待解密数据
	 * @param publicKey
	 *            乙方公钥
	 * @param privateKey
	 *            乙方私钥
	 * @return
	 * @throws Exception
	 */
	public byte[] decrypt(byte[] data, String publicKey, String privateKey) throws Exception {
		// 生成本地密钥
		SecretKey secretKey = getSecretKey(publicKey, privateKey);
		// 数据解密
		Cipher cipher = Cipher.getInstance(secretKey.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		return cipher.doFinal(data);
	}

	/**构建密钥*/
	private SecretKey getSecretKey(String publicKey, String privateKey) throws Exception {
		// 初始化公钥
		byte[] pubKeyBytes = decryptBASE64(publicKey);
		KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(pubKeyBytes);
		PublicKey pubKey = keyFactory.generatePublic(x509KeySpec);

		// 初始化私钥
		byte[] priKeyBytes = decryptBASE64(privateKey);

		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(priKeyBytes);
		Key priKey = keyFactory.generatePrivate(pkcs8KeySpec);

		KeyAgreement keyAgree = KeyAgreement.getInstance(keyFactory
				.getAlgorithm());
		keyAgree.init(priKey);
		keyAgree.doPhase(pubKey, true);

		// 生成本地密钥
		SecretKey secretKey = keyAgree.generateSecret(SECRET_ALGORITHM);
		return secretKey;
	}

	/** 取得私钥*/
	public String getPrivateKey(Map<String, Object> keyMap) throws Exception {
		Key key = (Key) keyMap.get(PRIVATE_KEY);
		return encryptBASE64(key.getEncoded());
	}

	/**取得公钥*/
	public String getPublicKey(Map<String, Object> keyMap) throws Exception {
		Key key = (Key) keyMap.get(PUBLIC_KEY);
		return encryptBASE64(key.getEncoded());
	}
	
	@Test
	public void testDH() throws Exception {
		// 生成甲方密钥对儿
		Map<String, Object> aKeyMap = initKey();
		String aPublicKey = getPublicKey(aKeyMap);
		String aPrivateKey = getPrivateKey(aKeyMap);
		System.out.println("甲方公钥:\r" + aPublicKey);
		System.out.println("甲方私钥:\r" + aPrivateKey);
		
		// 由甲方公钥产生本地密钥对儿
		Map<String, Object> bKeyMap = initKey(aPublicKey);
		String bPublicKey = getPublicKey(bKeyMap);
		String bPrivateKey = getPrivateKey(bKeyMap);
		System.out.println("乙方公钥:\r" + bPublicKey);
		System.out.println("乙方私钥:\r" + bPrivateKey);
		
		String aInput = "abcdef ";
		System.out.println("原文: " + aInput);

		// 由甲方公钥，乙方私钥构建密文
		byte[] aCode = encrypt(aInput.getBytes(), aPublicKey, bPrivateKey);

		// 由乙方公钥，甲方私钥解密
		byte[] aDecode = decrypt(aCode, bPublicKey, aPrivateKey);
		String aOutput = (new String(aDecode));
		System.out.println("解密: " + aOutput);

		/**反过来加密解密*/
		String bInput = "123456 ";
		System.out.println("原文: " + bInput);

		// 由乙方公钥，甲方私钥构建密文
		byte[] bCode = encrypt(bInput.getBytes(), bPublicKey, aPrivateKey);

		// 由甲方公钥，乙方私钥解密
		byte[] bDecode = decrypt(bCode, aPublicKey, bPrivateKey);
		String bOutput = (new String(bDecode));
		System.out.println("解密: " + bOutput);
	}

}
