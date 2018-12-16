package cs601.project3;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * A class that reads the initial request from clients and parases and prints header and body of the request
 * @author Tae Hyon Lee
 *
 */
public class HTTPRequestHandler {
	private InputStream instream;
	private Socket socket;
	private HashMap<String, String> paramList;
	private String httpMethod = "";
	private String path = "";
	private RequestObject request;
	private Logger logger;
	/**
	 * constructor that initializes hashmap of parameter list
	 */
	
	/**
	 * constructor that initializes hashmap and socket
	 * @param socket
	 * @throws IOException
	 */
	public HTTPRequestHandler(Socket socket, String log) throws IOException {
		if(socket != null) {
			this.instream = socket.getInputStream();
		}
		this.socket = socket;
		this.paramList = new HashMap<String, String>();
		
		logger = Logger.getLogger("MyLog");  
	    FileHandler fh; 
	    try {  

	        // This block configure the logger with handler and formatter  
	        fh = new FileHandler(log);  
	        logger.addHandler(fh);
	        SimpleFormatter formatter = new SimpleFormatter();  
	        fh.setFormatter(formatter);  

	        // the following statement is used to log any messages  
	        logger.info(logger.getName());  

	    } catch (SecurityException e) {  
	        e.printStackTrace();  
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    }  
	}
	
	/**
	 * Reads the header and body request through socket and parses the parameter
	 */
	public void read() {
		try {
			String message = "";
			String line = oneLine(instream);
			String requestLine = line;
			int length = 0;
			while(line != null && !line.trim().isEmpty()) {
				message += line + "\n";
				line = oneLine(instream);
				if(line.startsWith("Content-Length:")) {
					length = Integer.parseInt(line.split(":")[1].trim());
				}
			}
			System.out.println("Request: \n" + message);
			logger.info("Request: \n" + message);
			String parameters = readBody(length); 
			
			parseRequestLine(requestLine);
			parseParameters(parameters);
			
		}
		catch (IOException e) {
			System.err.println("ERROR while reading request");
		}
	}
	
	/**
	 * reads one line from the stream
	 * @param instream
	 * @return
	 * @throws IOException
	 */
	private static String oneLine(InputStream instream) throws IOException {
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		byte b = (byte) instream.read();
		while(b != '\n') {
			bout.write(b);
			b = (byte) instream.read();
		}
		return new String(bout.toByteArray());
	}
	
	/**
	 * parses the parameter specified in the link or the body
	 * @param param
	 */
	public void parseParameters(String param) {
		if(param.length() > 0) {
			String[] array = param.split("&");
			for (int i = 0; i < array.length; i++) {
				if (array[i].contains("=")) {
					
					String[] keyValue = array[i].split("=");
					if(keyValue.length > 1) {
						paramList.put(keyValue[0], keyValue[1]);
					}
				}
			}
		}
		request = new RequestObject(httpMethod, path, paramList);
	}
	
	public String readBody(int length) throws IOException {
		String body = "";
		byte[] bytes = new byte[length];
		int read = socket.getInputStream().read(bytes);
		
		while(read < length) {
			read += socket.getInputStream().read(bytes, read, (bytes.length-read));
		}
		body = new String(bytes, StandardCharsets.UTF_8);
		System.out.println("Bytes expected: " + length + " Bytes read: " + read + " " + body );
		logger.info("Bytes expected: " + length + " Bytes read: " + read + " " + body );
		return body;
	}
	
	/**
	 * Parses requested line
	 * @param requestLine
	 */
	public void parseRequestLine(String requestLine) {
		String[] requests = requestLine.split(" ");
		httpMethod = requests[0];
		if (requests.length > 1) {
			path = requests[1];
			if (path.contains("?")) {
				String[] split = requests[1].split("\\?");
				path = split[0];
				parseParameters(split[1]);
			}
		}
		request = new RequestObject(httpMethod, path, paramList);
	}	
	
	/**
	 * returns request object
	 * @return
	 */
	public RequestObject getRequest() {
		return request;
	}

}
