package org.elsys.sample01;


public class Fundamentals {
	private static String CONSTANT_STR = "Hello World";
	private int my_number;
	
	public Fundamentals(int number) {
		my_number = number;
	}
	private void showNumber() {
		System.out.println(my_number);
	}
	public static void main(String[] args) {
		int a = 8;
		int b = 10;
		int c = b - a;
		for(int i = 0; i < c; ++i) {
			System.out.println(CONSTANT_STR + "!");
		}
		Fundamentals fund1 = new Fundamentals(1);
		Fundamentals fund2 = new Fundamentals(1);
		Fundamentals fund3 = new Fundamentals(2);
		Fundamentals fund4 = fund3;
		
		if(fund1 == fund2) {
			System.out.println("fund1 == fund2");
		}else{
			System.out.println("fund1 != fund2");
		}
		
		if(fund1.equals(fund2)) {
			System.out.println("fund1 equals fund2");
		}else{
			System.out.println("fund1 not equals fund2");
		}
		
		if(fund3 == fund4) {
			System.out.println("fund3 == fund4");
		}else{
			System.out.println("fund3 != fund4");
		}
		
		if(fund3.equals(fund4)) {
			System.out.println("fund3 equals fund4");
		}else{
			System.out.println("fund3 not equals fund4");
		}
		fund1.showNumber();
		fund2.showNumber();
		fund3.showNumber();
		fund4.showNumber();
		
	}

}
