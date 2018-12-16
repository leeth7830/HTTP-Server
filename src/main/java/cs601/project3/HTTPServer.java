package cs601.project3;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * HTTP Server that can be connected through certain port and start taking in request to return a response to a client
 * @author Tae Hyon Lee
 *
 */
public class HTTPServer {
	private int port;
	private ExecutorService pool;
	private HashMap<String, Handler> map;
	private boolean running = true;
	private ServerSocket serverConnect;
	private String log;
	
	/**
	 * Constructor that takes in port
	 * @param port
	 */
	public HTTPServer(int port, String log) {
		this.port = port;
		this.log = log;
		map = new HashMap<String, Handler>();
	}
	
	/**
	 * method that adds a handler to the server
	 * @param bot
	 * @param handler
	 */
	public void addMapping(String bot, Handler handler){
		map.put(bot, handler);
	}
	
	/**
	 * method that starts up the server
	 */
	public void startup() {
		try {
			System.out.println("Server started.\nListening for connections on port : " + port + " ...\n");
			pool = Executors.newFixedThreadPool(100);
			serverConnect = new ServerSocket(port);
			while (running) {
				Socket socket = serverConnect.accept();
				ServerWorker myServer = new ServerWorker(socket, map, log);
				Thread thread = new Thread(myServer);
				pool.execute(thread);
			}
			
		} catch (IOException e) {
			System.err.println("Server Connection error : " + e.getMessage());
		} finally {
			try {
				serverConnect.close();
			} catch (IOException e) {
				System.err.println("Error while closing server socket");
			}
		}
	}
}
