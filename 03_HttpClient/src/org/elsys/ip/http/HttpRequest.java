package org.elsys.ip.http;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

public class HttpRequest {
	String method;
	String target_path;
	String data;
	
	
	public HttpRequest(String request, String path, String data) {
		method = request;
		target_path = path;
		this.data = data;
	}

	public void write(OutputStream stream) throws IOException {
		final PrintWriter out = new PrintWriter(stream);
		out.printf("%s %s %s\n", this.method, this.target_path, "HTTP/1.1");
		
		if (this.data != null) {
			out.write(data);
		}
		out.printf("\n");
		out.flush();
		stream.flush();
	}

}
