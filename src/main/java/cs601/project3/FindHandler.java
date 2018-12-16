package cs601.project3;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
/**
 * Hanlder that handles the /find path
 * @author Taehyon
 *
 */
public class FindHandler implements Handler {
	private InvertedIndex index;
	private String page;
	private ArrayList<Data> list;
	private HTMLStorage html;
	private Config config;
	
	/**
	 * Constructor for Find handler that takes in the inverted index and config object
	 * @param config
	 * @param index
	 */
	public FindHandler(Config config, InvertedIndex index) {
		this.index = index;
		this.html = new HTMLStorage();
		this.config = config;
	}
	
	/**
	 * handle method that looks returns input text box on GET and search result on POST
	 */
	@Override
	public void handle(PrintWriter writer, RequestObject request) {
		String httpMethod = request.getMethod();
		HashMap<String, String> parameters = request.getParameters();
		if (httpMethod.equals("GET")) {
			page = html.pageTitle("Find") + html.inputTextBox(request.getPath(), "asin");
			System.out.println("page");	
		}
		else {
			String parameter = "";
			for (String key : parameters.keySet()) {
				if (key.equals("asin")) {
					parameter = parameters.get(key);
					break;
				}
			}
			list = index.find(parameter);
			page = html.pageTitle("Find") + 
					"<table style='width:100%'>";
			for(Data data : list) {
				page = page + "<tr>" + 
						"<th>" + data + "</th>" +
						"</tr>";
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
	 * gets current page
	 */
	@Override
	public String getPage() {
		return page;
	}
	/**
	 * gets current list
	 * @return
	 */
	public ArrayList<Data> getList() {
		return list;
	}
}
