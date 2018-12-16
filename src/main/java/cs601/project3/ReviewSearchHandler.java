package cs601.project3;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.logging.Logger;

/**
 * ReviewSearch Handler that adds the path  /reviewsearch.
 * Returns the result of reviewsearch to the client
 * @author Tae Hyon Lee
 *
 */
public class ReviewSearchHandler implements Handler {
	private InvertedIndex index;
	private String page;
	private HashMap<Data, Integer> list;
	private HTMLStorage html;
	private Config config;
	/**
	 * Constructor for reviewsearch handler
	 * @param config
	 * @param index
	 */
	public ReviewSearchHandler(Config config, InvertedIndex index) {
		this.index = index;
		html = new HTMLStorage();
		this.config = config;
	}

	/**
	 * takes in httpmethod and parameters and performs GET or POST tasks
	 */
	@Override
	public void handle(PrintWriter writer, RequestObject request) {
		String httpMethod = request.getMethod();
		HashMap<String, String> parameters = request.getParameters();
		if (httpMethod.equals("GET")) {
			 page = html.pageTitle("Review Search") + html.inputTextBox(request.getPath(), "query");
					
		}
		else {
			String parameter = "";
			for (String key : parameters.keySet()) {
				if (key.equals("query")) {
					parameter = parameters.get(key);
					break;
				}
			}
			list = index.search(parameter);
			page = html.pageTitle("Review Search") + 
					"<table style='width:100%'>";
			for(Data data : list.keySet()) {
				if (list.keySet().size() > 100)
				{
					page = page + "<tr>" + 
							"<th>too many results (over 100)</th>" +
							"</tr>";
					break;
				}
				else {
					page = page + "<tr>" + 
							"<th>" + data + "</th>" +
							"</tr>";
				}
			}
			if (parameter.equals("")) {
				page = page + html.invalidParam();
			}
			else if (list.size() == 0) {
				page = page + html.noResult();
			}
	  		page =  page + "</body>" + 
					"</html>";
		}
	}
	
	/**
	 * gets current page generated
	 */
	@Override
	public String getPage() {
		return page;
	}
	public HashMap<Data, Integer> getList() {
		return list;
	}

}
