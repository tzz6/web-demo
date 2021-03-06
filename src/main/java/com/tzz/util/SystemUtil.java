package com.tzz.util;

import java.net.InetAddress;
import java.net.NetworkInterface;

public class SystemUtil {

	/** 获取计算机名 */
	public static String getSystemUsername() {
		return System.getProperty("user.name");
	}

	/** 获取MAC地址 */
	public static String getSystemMAC() {
		InetAddress ia = null;
		StringBuffer sb = null;
		byte[] mac = null;
		try {
			// 获取本地IP对象
			ia = InetAddress.getLocalHost();
			// 获得网络接口对象（即网卡），并得到mac地址，mac地址存在于一个byte数组中。
			mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();

			sb = new StringBuffer();
			for (int i = 0; i < mac.length; i++) {
				if (i != 0) {
					sb.append("-");
				}
				// mac[i] & 0xFF 是为了把byte转化为正整数
				String s = Integer.toHexString(mac[i] & 0xFF);
				sb.append(s.length() == 1 ? 0 + s : s);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 把字符串所有小写字母改为大写成为正规的mac地址并返回
		return sb.toString().toUpperCase();
	}

}
