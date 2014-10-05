package org.elsys.ip.sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateServer {

	public static void main(String[] args) throws IOException {
		new DateServer().run();
	}

	private void run() throws IOException {
		final ServerSocket serverSocket = new ServerSocket(44012);
		boolean started = true;
		

		while(started) {
			final Socket clientSocket = serverSocket.accept();
			new ClientHandler(clientSocket).start();
		}
		
		serverSocket.close();
	}
	
	
	class ClientHandler extends Thread {
		Socket clientSocket;
		public ClientHandler(Socket socket_) {
			clientSocket = socket_;
		}
		@Override
		public void run() {
			try {
				handleClient();
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
		private void handleClient() throws IOException{
			final InputStream inputStream = clientSocket.getInputStream();
			final OutputStream outputStream = clientSocket.getOutputStream();
			
			
			final InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			final BufferedReader in = new BufferedReader(inputStreamReader);
			
			final PrintWriter out = new PrintWriter(outputStream);
			
			final String readLine = in.readLine();
			try {
				Date date = new SimpleDateFormat("d/M/y", Locale.ENGLISH).parse(readLine);
				Date now = new Date();
				long diff = Math.abs(date.getTime() - now.getTime());
				out.print(diff / 1000 / 60 / 60 / 24);
				out.print("\n");
			} catch (ParseException e) {
				out.print("Bad format. Use d/M/y\n");
			} finally {
				out.flush();
				clientSocket.close();
			}
		}
	}
	
}
