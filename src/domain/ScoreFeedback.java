package domain;

public class ScoreFeedback extends Feedback {
	private int score;
	private int maximumScore;
	
	public ScoreFeedback(String feedbackText, int score, int maximumScore){
		super(feedbackText);
		setScore(score);
		setMaximumScore(maximumScore);
	}
	
	private int getMaximumScore() {
		return maximumScore;
	}
	private void setMaximumScore(int maximumScore) {
		this.maximumScore = maximumScore;
	}

	private int getScore() {
		return score;
	}

	private void setScore(int score) {
		this.score = score;
	}

	public String toString(){
		return super.toString() + ": " + getScore() + "/" + getMaximumScore();
	}
}
