package cs601.project3;

/**
 * Object that stores reused html lines
 * @author Tae Hyon Lee
 *
 */
public class HTMLStorage {
	
	/**
	 * returns title in html code
	 * @param title
	 * @return
	 */
	public String pageTitle(String title) {
		return "<html> " + "<head><title>" + title + "</title></head>" + "<body>";
	}
	
	/**
	 * return textbox html code
	 * @param path
	 * @param name
	 * @return
	 */
	public String inputTextBox(String path, String name) {
		return "<form enctype='application/x-www-form-urlencoded' action='" + path + "' method='post' >" +
				"Query: <input type='text' name='" + name + "'/>" +
				"<input type='submit' name='submit'/>" +
				"</form>" + 
  				"</body>" + 
				"</html>";
	}
	
	/**
	 * returns when error
	 * @param error
	 * @return
	 */
	public String failedPage(String error) {
		return "<html><head>" +		 
		  "<title>" + error +  "</title>" +
		  "</head><body>" +
		  "<p><b>" + error + "</b></p>" + 
		"</body></html>";
	}
	
	/**
	 * gets content length of body
	 * @param length
	 * @return
	 */
	public String contentLength(int length) {
		return "Content-Length: " + length + "\n" + "\r\n";
	}
	
	/**
	 * returns when parameter is not valid
	 * @return invalid html
	 */
	public String invalidParam() {
		return "<tr>" + 
				"<th>" + "parameter is invalid" + "</th>" +
				"</tr>";
	}
	
	/**
	 * returns when no results are found 
	 * @return no result html
	 */
	public String noResult() {
		return "<tr>" + 
				"<th>" + "no results found" + "</th>" +
				"</tr>";
	}
}
