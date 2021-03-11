package com.eghm.enums;


import java.util.EnumSet;

public class EnumTest {
	public static void main(String[] args) {
		//全部元素组成set
		EnumSet<Channel> enumSet = EnumSet.allOf(Channel.class);
		System.out.println(enumSet);
		//用pc ~ android之间的元素组成set
		enumSet = EnumSet.range(Channel.PC, Channel.ANDROID);
		System.out.println(enumSet);
		//取反set
		enumSet = EnumSet.complementOf(enumSet);
		System.out.println(enumSet);
		//空set
		enumSet = EnumSet.noneOf(Channel.class);
		System.out.println(enumSet);

    }
}
