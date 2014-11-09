package org.elsys.ip.http;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;

public class HttpClient {
	private Boolean following;
	

	public static void main(String[] args) throws UnknownHostException, IOException{
		HttpClient client = new HttpClient();
		client.setFollow(true);
		System.out.println(client.open("GET", "google.com", "/", null));
	}
	
	public void setFollow(Boolean follow) {
		following = follow;
	}
	
	public String open(String request, String host, String path, String data) throws UnknownHostException, IOException {
		Socket socket = new Socket(host, 80);
		OutputStream stream = socket.getOutputStream();
		
		HttpRequest httprequest = new HttpRequest(request, path, data);
		httprequest.write(stream);
		
		HttpResponse httpresponse = new HttpResponse();
		httpresponse.read(socket.getInputStream());
		
		if(httpresponse.statusCode == 301) {
			String newLocation = httpresponse.headers.get("Location");
			final URL newURL = new URL(newLocation);
			path = newURL.getFile();
			host = newURL.getHost();
			return open(request, host, path, data);
		}
		
		socket.close();
		return new String(httpresponse.body);
	}
}