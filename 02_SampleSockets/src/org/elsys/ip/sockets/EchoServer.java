package org.elsys.ip.sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

	public static void main(String[] args) throws Exception {
		EchoServer echo = new EchoServer();
		echo.run();
	}
	private class ClientHandler extends Thread {
		private final Socket clientSocket;
		public ClientHandler(Socket s) {
			clientSocket = s;
		}
		@Override
		public void run() {
			try {
				handleClient();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		private void handleClient() throws IOException {
			while(true) {
				try {
					final InputStream inputStream = clientSocket.getInputStream();
					final OutputStream outputStream = clientSocket.getOutputStream();
					
					
					final InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
					final BufferedReader in = new BufferedReader(inputStreamReader);
					
					final PrintWriter out = new PrintWriter(outputStream);
					final String readLine = in.readLine();
					out.println(readLine);
					
					out.flush();
				}catch(java.net.SocketException e) {
					break;
				}
			}
			
		}
	}
	public void run() throws Exception {
		final ServerSocket serverSocket = new ServerSocket(44012);
		boolean started = true;
		

		while(started) {
			final Socket clientSocket = serverSocket.accept();
			new ClientHandler(clientSocket).start();
		}
		
		serverSocket.close();
	}

}
