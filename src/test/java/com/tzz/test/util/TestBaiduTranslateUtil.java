package com.tzz.test.util;

import org.junit.Test;

import com.tzz.util.BaiduTranslateUtil;

public class TestBaiduTranslateUtil {


	@Test
	public void testTranslate() {
		try {
			// String q = "百度翻译引擎示例";
			String q = "测试百度翻译";
			String from = "zh";
			String to = "en";
			String result = BaiduTranslateUtil.translate(q, from, to);
			if (result == null) {
				System.out.println("翻译出错，参考百度错误代码和说明。");
				return;
			}
			System.out.println(q + "：" + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
