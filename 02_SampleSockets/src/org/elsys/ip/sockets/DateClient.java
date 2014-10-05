package org.elsys.ip.sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;



public class DateClient {

	public static void main(String[] args) throws IOException{
		System.out.println("Enter date in format d/M/y: ");
		final InputStream input = System.in;
		final InputStreamReader inputStreamReader = new InputStreamReader(input);
		final BufferedReader reader = new BufferedReader(inputStreamReader);
		final String date = reader.readLine();
	
			
		final Socket clientSocket = new Socket("localhost", 44012);
		try {
			//get I/O streams
			final InputStream inputStream = clientSocket .getInputStream();
			final OutputStream outputStream = clientSocket.getOutputStream();
			
			final InputStreamReader inputStreamReader1 = new InputStreamReader(inputStream);
			final BufferedReader in = new BufferedReader(inputStreamReader1);
			final PrintWriter out = new PrintWriter(outputStream);
	
			
			out.println(date);
			out.flush();
			
	
			final String result = in.readLine();
			System.out.println(result);
		}catch (IOException e) {
			
		}finally {
			clientSocket.close();
		}
		
	}
}
