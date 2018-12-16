package cs601.project3;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * A class that reads the parsed request lines and returns appropriate message to the client
 * @author Tae Hyon Lee
 *
 */
public class HTTPResponseHandler {
	private RequestObject request;
	private HashMap<String, Handler> map;
	private PrintWriter writer;
	private Handler handler;
	private String headers = "";
	private String page = "";
	private Logger logger;
	
	/**
	 * Constructor that initializes the below parameters
	 * @param requestLine
	 * @param parameters
	 * @param map
	 */
	
	public HTTPResponseHandler(PrintWriter writer,RequestObject request, HashMap<String, Handler> map, String log) throws IOException {
		this.request = request;
		this.map = map;
		this.writer = writer;
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
	 * respond method that parses the request and write the header and page
	 */
	public void respond() {
		String httpMethod = request.getMethod();
		HTMLStorage storage = new HTMLStorage();
		String path = request.getPath();
		HashMap<String, String> paramList = request.getParameters();
		String page = "";
		if (httpMethod == null || path == null || httpMethod.length() < 1 || path.length() < 1) {
			page = storage.failedPage(HTTPConstants.BAD_REQUEST);
			headers = HTTPConstants.BAD_REQUEST + HTTPConstants.CONNECTION_CLOSE + storage.contentLength(page.length());;
			
		}
		else {
			if (!(httpMethod.equals("GET") || httpMethod.equals("POST"))) {
				page = storage.failedPage(HTTPConstants.NOT_ALLOWED);
				headers = HTTPConstants.NOT_ALLOWED + HTTPConstants.CONNECTION_CLOSE + storage.contentLength(page.length());
			}
			else {
				for (String key : map.keySet()) {
					if (path.equals(key)) {
						handler = map.get(key);
						handler.handle(writer, request);
						page = handler.getPage();
						headers = HTTPConstants.OK_HEADER + HTTPConstants.CONNECTION_CLOSE + storage.contentLength(page.length());
						break;
					}
				}
				if (headers.equals("")) {
					page = storage.failedPage(HTTPConstants.NOT_FOUND);
					headers = HTTPConstants.NOT_FOUND + HTTPConstants.CONNECTION_CLOSE + storage.contentLength(page.length());
					
				}
				
			}
		}
		logger.info("Response: \n" + headers + page);
		writer.write(headers);
		writer.write(page);
		writer.flush();
	}
	
	/**
	 * returns the header
	 * @return header
	 */
	public String getHeader() { 
		return headers;
	}
}
