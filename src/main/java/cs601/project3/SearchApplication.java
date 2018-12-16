package cs601.project3;

/**
 * Search Application that can perform reviews search or find and return the result to the client
 * @author Tae Hyon Lee
 *
 */
public class SearchApplication {
	public static void main(String[] args) {
		ConfigurationManager manager = new ConfigurationManager();
		InvertedIndex index = new InvertedIndex();
		FileManager fileManager = new FileManager(manager.getConfig().getFileName(), index);
		HTTPServer server = new HTTPServer(manager.getConfig().getSearchPort(), "log/SearchLogFile.log");
		//The request GET /reviewsearch will be dispatched to the 
		//handle method of the ReviewSearchHandler.
		server.addMapping("/reviewsearch", new ReviewSearchHandler(manager.getConfig(), index));
		//The request GET /find will be dispatched to the 
		//handle method of the FindHandler.
		server.addMapping("/find", new FindHandler(manager.getConfig(), index));
		server.startup();
	}
}