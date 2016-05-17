package com.tzz.test.util.java;

import org.junit.Test;

import com.tzz.util.java.ColorEnum;

public class ColorEnumTest {
	
	@Test
	public void test(){
		System.out.println(ColorEnum.RED.getRedValue());
		System.out.println(ColorEnum.BLUE.getRedValue());
		System.out.println(ColorEnum.BLACK.getRedValue());
		System.out.println(ColorEnum.YELLOW.getRedValue());
	}
}

