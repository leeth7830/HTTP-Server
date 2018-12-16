package cs601.project3;

import java.io.PrintWriter;
import java.util.HashMap;

/**
 * Handler interface
 * @author Tae Hyon Lee
 *
 */
public interface Handler {
	/**
	 * takes in printwriter, httpmethod and parameters and returns result to client
	 * @param writer
	 * @param httpMethod
	 * @param parameters
	 */
	public void handle(PrintWriter writer, RequestObject request);
	
	/**
	 * gets current page
	 * @return
	 */
	public String getPage();
}
