package cs601.project3;

/**
 * Data object that stores the parsed value
 * @author Taehyon
 *
 */
public abstract class Data {
	protected String asin;
	
	public Data(String asin) {
		this.asin = asin;
	}
	/**
	 * Getter for ASIN
	 * @return ASIN
	 */
	public String getAsin() {
		return asin;
	}
	/**
	 * returns the data object
	 * @return
	 */
	public abstract String getData();
	
	/**
	 * Setter for ASIN
	 * @param asin
	 */
	public void setAsin(String asin) {
		this.asin = asin;
	}
}
