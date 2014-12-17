/**
 * 
 * @author Ruben Thielemans, Tamo Maes, Georges Petrofski & Sam Hendrickx
 *
 */
package domain;

public class Feedback {
	private String feedbackText;
	
	public Feedback(String feedbackText){
		setFeedbackText(feedbackText);
	}

	private String getFeedbackText() {
		return feedbackText;
	}

	private void setFeedbackText(String feedbackText) {
		this.feedbackText = feedbackText;
	}
	
	public String toString(){
		return getFeedbackText();
	}
}
