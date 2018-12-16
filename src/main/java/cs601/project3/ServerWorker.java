package cs601.project3;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;

/**
 * Server worker that create a new thread to work on client's request
 * @author Tae Hyon Lee
 *
 */
public class ServerWorker implements Runnable {
	private Socket socket;
	private HashMap<String, Handler> map;
	private String log;
	
	/**
	 * constructor for ServerWork. Sets socket and Hashmap of Handlers
	 * @param socket
	 * @param map
	 */
	public ServerWorker(Socket socket, HashMap<String, Handler> map, String log) {
		this.socket = socket;
		this.map = map;
		this.log = log;
	}
	
	/**
	 * runs the thread to carry out the tasks
	 */
	@Override
	public void run() {
		try {
			
			HTTPRequestHandler request = new HTTPRequestHandler(socket, log);
			request.read();
			PrintWriter writer = new PrintWriter(socket.getOutputStream());
			HTTPResponseHandler response = new HTTPResponseHandler(writer, request.getRequest(), map, log);
			response.respond();
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
		finally {
			try {
				socket.close();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
