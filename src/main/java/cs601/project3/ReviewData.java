package cs601.project3;

public class ReviewData extends Data {
	private String reviewText;
	private String reviewerID;
	private double overall;
	/**
	 * Constructor for ReviewData
	 * @param asin
	 * @param reviewText
	 * @param reviewerID
	 * @param overall
	 */
	public ReviewData(String asin, String reviewText, String reviewerID, double overall) {
		super(asin);
		this.reviewText = reviewText;
		this.reviewerID = reviewerID;
		this.overall = overall;
	}
	/**
	 * returns reviewText
	 */
	public String getData(){
		return reviewText;
	}
	/**
	 * Overrides toString for printing/writing purposes
	 */
	@Override
	public String toString(){
		return ("ASIN: " + asin + ", ReviewText: " + reviewText + ", Overall: " + overall + " reviewerID: " + reviewerID);
	}
	/**
	 * Setter for reviewText
	 * @param reviewText
	 */
	public void setReview(String reviewText) {
		this.reviewText = reviewText;
	}
	/**
	 * Getter for reviewer ID
	 * @return reviewerID
	 */
	public String getReviewerID() {
		return reviewerID;
	}
	/**
	 * Setter for reviewerID
	 * @param reviewerID
	 */
	public void setReviewerID(String reviewerID) {
		this.reviewerID = reviewerID;
	}
	/**
	 * Getter for overall score
	 * @return overall
	 */
	public double getOverall() {
		return overall;
	}
	/**
	 * Setter for overall
	 * @param overall
	 */
	public void setOverall(double overall) {
		this.overall = overall;
	}
}
