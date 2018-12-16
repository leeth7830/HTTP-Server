package cs601.project3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

/**
 * Client Application used to test out the server
 * @author Tae Hyon Lee
 *
 */
public class ClientApplication {
	private int port;
	private String path;
	private String parameter;
	private String method;
	private HttpsURLConnection connection;
	
	/**
	 * Constructor for client application
	 * @param port
	 * @param path
	 * @param parameter
	 * @param method
	 */
	public ClientApplication(int port, String path, String parameter, String method) {
		this.port = port;
		this.path = path;
		this.parameter = parameter;
		this.method = method;
	}
	/**
	 * Send the request to the server
	 * @throws IOException
	 */
	public void send() throws IOException {
		String link = "http://localhost:" + port + path + parameter;
		URL url = new URL(link);
		connection = (HttpsURLConnection)url.openConnection();
		connection.setRequestMethod(method);		
		connection.connect();
	}
	
	/**
	 * get the header response from the server
	 * @return header
	 */
	public String getHeaders() {
		Map<String,List<String>> headers = connection.getHeaderFields();
		String header = "";
		for(String key: headers.keySet()) {
			System.out.println(key);
			header = key + "\n";
			List<String> values = headers.get(key);
			for(String value: values) {
				System.out.println("\t" + value);
				header = header + "\t" + value + "\n";
			}
		}
		return header;
	}
	/**
	 * get the body response from the server
	 * @return body
	 * @throws IOException
	 */
	public String getBody() throws IOException {
		String body = "";
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(connection.getInputStream()));
		String line;
		while((line = reader.readLine()) != null) {
			System.out.println(line);
			body = body + line + "\n";
		}
		return body;
	}
}

