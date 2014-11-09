package org.elsys.ip.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.HashMap;

public class HttpResponse {
	public int statusCode;
	public char[] body;
	public HashMap<String, String> headers;
	public HttpResponse() {
		headers = new HashMap<String, String>();
	}
	public void read(InputStream inputStream) throws IOException {
		final InputStreamReader reader = new InputStreamReader(inputStream);
		final BufferedReader in = new BufferedReader(reader);
		final String statusLine = in.readLine();
		final String[] statusLineSplit = statusLine.split("\\s", 3);
		// final String httpVersion = statusLineSplit[0];
		this.statusCode = Integer.parseInt(statusLineSplit[1]);
		// final String statusReason = statusLineSplit[2];
		String headerLine = in.readLine();
		while (!headerLine.isEmpty()) {
			final String[] headerSplit = headerLine.split(":\\s?", 2);
			if (headerSplit.length != 2) {
				throw new IllegalArgumentException("Header line not valid");
			}
			headers.put(headerSplit[0], headerSplit[1]);
			
			
			headerLine = in.readLine();
		}
		
		int body_size = Integer.parseInt(headers.get("Content-Length"));
		body = new char[body_size];
		in.read(body, 0, body_size);
		
		in.close();
	}

}
