package org.elsys.sample01;
import java.io.*;

public class Capitalize {
	//--HW 2--//
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		final String str = in.readLine();
		
		System.out.println(str.toUpperCase());
	}

}
