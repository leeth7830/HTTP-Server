package cs601.project3;

/**
 * Chat Handler that posts to Slack channel when the user specifies path "/slackbot" and parameter "message"
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class ChatHandler implements Handler {
	private HTMLStorage html;
	private String page;
	private Config config;
	private String message = "";
	
	/**
	 * Constructor that takes in configuration file
	 * @param config
	 */
	public ChatHandler(Config config) {
		html = new HTMLStorage();
		this.config = config;
	}

	/**
	 * Handle method that takes in http method and parameter and posts to slack and returns the result to back to the client
	 */
	@Override
	public void handle(PrintWriter writer, RequestObject request) {
		String httpMethod = request.getMethod();
		HashMap<String, String> parameters = request.getParameters();
		if (httpMethod.equals("GET")) {
			 page = html.pageTitle("Slack Chat") + html.inputTextBox(request.getPath(), "message");
					
		}
		else {
			for (String key : parameters.keySet()) {
				if (key.equals("message")) {
					message = parameters.get(key);
					try {
						if (writer != null) {
							this.postToSlack(message);
						}
					} catch (IOException e) {
						System.err.println("ERROR while posting to slack");
					}
				}
			}
			if (message.equals("")) {
				page = html.pageTitle("Slack Chat") +
				"<Text> No parameter message found </Text" +
				"</body>" + 
				"</html>";
			}
			else {
				page = html.pageTitle("Slack Chat") + html.inputTextBox("/slackbot", "message");
			}
		}
	}
	
	/**
	 * Method that posts to slack server using the specified token channel and message
	 * @param message
	 * @throws IOException
	 */
	public void postToSlack(String message) throws IOException {
		String link = config.getSlackURL() + "?token=" + config.getToken() + "&channel=" + config.getChannel() + "&text=" + message;
		URL url = new URL(link);
		HttpsURLConnection connection = (HttpsURLConnection)url.openConnection();
		connection.setRequestMethod("POST");		
		connection.connect();
		printHeaders(connection);
		printBody(connection);
	}
	
	/**
	 * A method that prints the header of returned message from slack
	 * @param connection
	 */
	public static void printHeaders(URLConnection connection) {
		Map<String,List<String>> headers = connection.getHeaderFields();
		for(String key: headers.keySet()) {
			System.out.println(key);
			List<String> values = headers.get(key);
			for(String value: values) {
				System.out.println("\t" + value);
			}
		}		
	}
	
	/**
	 * prints body
	 * @param connection
	 * @throws IOException
	 */
	public static void printBody(URLConnection connection) throws IOException {
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(connection.getInputStream()));
		String line;
		while((line = reader.readLine()) != null) {
			System.out.println(line);
		} 
	}
	
	/**
	 * returns that current message parsed from parameters. Used for testing purposes
	 * @return message
	 */
	public String getMessage() {
		return message;
	}
	
	/**
	 * returns current page generated
	 */
	@Override
	public String getPage() {
		return page;
	}

}
