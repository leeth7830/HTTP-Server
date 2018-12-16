package cs601.project3;

/**
 * Class that stores HTTP COnstants 
 * @author Tae Hyon Lee
 *
 */
public class HTTPConstants {

	public static final String OK_HEADER = "HTTP/1.0 200 OK" + "\r\n";
	public static final String BAD_REQUEST = "HTTP/1.0 400 Bad Request"  + "\r\n";
	public static final String NOT_ALLOWED = "HTTP/1.0 405 Method Not Allowed"  + "\r\n";
	public static final String NOT_FOUND = "HTTP/1.0 404 Not Found" + "\r\n";	
	public static final String CONNECTION_CLOSE = "Connection: close" + "\r\n";
}