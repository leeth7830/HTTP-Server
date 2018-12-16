package cs601.project3;

/**
 * Configuration object that stores the value from the config.json file.
 * @author Taehyon
 *
 */
public class Config {
	private String fileName;
	private int searchPort;
	private int slackPort;
	private String token;
	private String channel;
	private String slackURL;
	
	/**
	 * Constructor that takes in the below values from the JSON file.
	 * @param fileName
	 * @param searchPort
	 * @param slackPort
	 * @param token
	 * @param channel
	 * @param slackURL
	 */
	public Config (String fileName, int searchPort, int slackPort, String token, String channel, String slackURL) {
		this.fileName = fileName;
		this.searchPort = searchPort;
		this.slackPort = slackPort;
		this.token = token;
		this.channel = channel;
		this.slackURL = slackURL;
	}
	
	/**
	 * gets the file name
	 * @return file name 
	 */
	public String getFileName() {
		return fileName;
	}
	/**
	 * sets the file name
	 * @param fileName
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	/**
	 * gets the search port
	 * @return search app port
	 */
	public int getSearchPort() {
		return searchPort;
	}
	/**
	 * sets the search applicaton's port
	 * @param searchPort
	 */
	public void setSearchPort(int searchPort) {
		this.searchPort = searchPort;
	}
	/**
	 * gets the slack port
	 * @return
	 */
	public int getSlackPort() {
		return slackPort;
	}
	/**
	 * sets the slack application's port
	 * @param slackPort
	 */
	public void setSlackPort(int slackPort) {
		this.slackPort = slackPort;
	}
	/**
	 * gets the channel for slack
	 * @return channel name
	 */
	public String getChannel() {
		return channel;
	}
	/**
	 * sets the channel name
	 * @param channel
	 */
	public void setChannel(String channel) {
		this.channel = channel;
	}
	/**
	 * gets the token for slack api
	 * @return token
	 */
	public String getToken() {
		return token;
	}
	/**
	 * sets the token for slack api
	 * @param token
	 */
	public void setToken(String token) {
		this.token = token;
	}
	/**
	 * gets the slack URL
	 * @return slack URL
	 */
	public String getSlackURL() {
		return slackURL;
	}
	/**
	 * sets the slack URL
	 * @param slackURL
	 */
	public void setSlackURL(String slackURL) {
		this.slackURL = slackURL;
	}
	
}
