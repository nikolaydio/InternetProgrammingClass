package org.elsys.sample01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

public class StreamExample {
	public final static String END_READER = "END";
	
	public static void main(String []args) throws IOException {
		//inputStreamExample();
		//bufferedReaderExample();
		//readLinecountLetterInLine();
		//readLineGetLength();
		//readFromUrl();
		readFromFile();
	}
	
	public static void inputStreamExample() throws IOException {
		InputStream input = System.in;
		
		final int b = input.read();
		
		System.out.println(Character.toString((char)b));
		
		final byte a[] = new byte[3];
		input.read(a);
		System.out.println(new String(a));
	}

	private static void readFromFile() throws IOException {
		final File file = new File("/home/nickie/test.txt");
		final InputStream input = new FileInputStream(file);
		final InputStreamReader inputStreamReader = new InputStreamReader(input, Charset.forName("UTF-8"));
		final BufferedReader reader = new BufferedReader(inputStreamReader);
		
		try {
			String nextLine;
			while((nextLine = reader.readLine()) != null) {
				System.out.println(nextLine);
			}
		} finally {
			reader.close();
		}
		
		
	}
	
	private static void readFromUrl() throws IOException {
		final URL url = new URL("http://google.com");
		final InputStream input = url.openStream();
		final InputStreamReader inputStreamReader = new InputStreamReader(input, Charset.forName("UTF-8"));
		final BufferedReader reader = new BufferedReader(inputStreamReader);
		
		try {
			String nextLine;
			while((nextLine = reader.readLine()) != null) {
				System.out.println(nextLine);
			}
		} finally {
			reader.close();
		}
		
		
	}
	
	public static void bufferedReaderExample() throws IOException {
		final InputStream input = System.in;
		final InputStreamReader inputStreamReader = new InputStreamReader(input);
		final BufferedReader reader = new BufferedReader(inputStreamReader);
		
		final String wholeLine = reader.readLine();
		System.out.println(wholeLine);
		
		int count = 0;
		while(!reader.readLine().equals(END_READER)) {
			++count;
		}
		System.out.println("You have entered: " + count + " lines.");
	}
	
	public static String getLine() {
		final InputStream input = System.in;
		final InputStreamReader inputStreamReader = new InputStreamReader(input);
		final BufferedReader reader = new BufferedReader(inputStreamReader);

		try {
			return reader.readLine();
		} catch (IOException e) {
			return null;
		}
	}
	
	public static int countSingleLetter(String line, char letter) {
		int count = 0;
		for (int i = 0; i < line.length(); ++i) {
			if (line.charAt(i) == letter) {
				++count;
			}			
		}
		
		return count;
	}
	
	public static int countLineLength(String line) {
		return line.length();
	}
	
	public static void countLetterInLine() {
		final String line = getLine();
		final int count = countSingleLetter(line, 'a');
		System.out.println("Count is: " + count);
	}
	
	public static void readLineGetLength() {
		final String line = getLine();
		System.out.println("Length is: " + countLineLength(line));
	}
}
