package org.elsys.sample01;


public class Fundamentals {
	private static String CONSTANT_STR = "Hello World";
	
	public static void main(String[] args) {
		int a = 8;
		int b = 10;
		int c = b - a;
		for(int i = 0; i < c; ++i) {
			System.out.println(CONSTANT_STR + "!");
		}
	}

}
