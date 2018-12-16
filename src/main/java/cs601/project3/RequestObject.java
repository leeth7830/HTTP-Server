package cs601.project3;

import java.util.HashMap;

/**
 * Request object that holds method, path, and paramters
 * @author Tae Hyon Lee
 *
 */
public class RequestObject {
	private String method;
	private String path;
	private HashMap<String, String> parameters;
	
	/**
	 * constructor for request object that assigns each variable with a value
	 * @param method
	 * @param path
	 * @param parameters
	 */
	public RequestObject(String method, String path, HashMap<String, String> parameters) {
		this.setMethod(method);
		this.path = path;
		this.setParameters(parameters);
	}

	/**
	 * returns parameters
	 * @return parameters
	 */
	public HashMap<String, String> getParameters() {
		return parameters;
	}

	/**
	 * sets parameters
	 * @param parameters
	 */
	public void setParameters(HashMap<String, String> parameters) {
		this.parameters = parameters;
	}

	/**
	 * gets method
	 * @return method
	 */
	public String getMethod() {
		return method;
	}
	
	/**
	 * sets method
	 * @param method
	 */
	public void setMethod(String method) {
		this.method = method;
	}
	
	/**
	 * gets path
	 * @return path
	 */
	public String getPath() {
		return path;
	}
	
	/**
	 * sets path
	 * @param path
	 */
	public void setPath(String path) {
		this.path = path;
	}
}
