package cs601.project3;

/**
 * Slack Bot server that boots up the server that takes in an input and posts it to Slack
 * @author Taehyon
 *
 */
public class ChatApplication {
	public static void main(String[] args) {
		ConfigurationManager manager = new ConfigurationManager(); //config file
		HTTPServer server = new HTTPServer(manager.getConfig().getSlackPort(), "log/ChatLogFile.log"); //create server
		server.addMapping("/slackbot", new ChatHandler(manager.getConfig())); //add chat handler
		server.startup(); //start the server
	}
}