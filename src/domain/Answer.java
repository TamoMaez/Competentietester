/**
 * 
 * @author Ruben Thielemans, Tamo Maes, Georges Petrofski & Sam Hendrickx
 *
 */
package domain;

public abstract class Answer {
	protected Question question;

	public Answer(Question question) {
		this.setQuestion(question);
		this.question.incrementTimesAnswered();
	}
	
	public abstract void addOption(Option option);

	public abstract boolean isCorrect();
	
	public abstract boolean isForCategory(Category category);
	
	public abstract int getScoreByCategory(Category category);

	public abstract int getScore();

	public abstract int getMaximumScore(Category category);

	public abstract int getMaximumScore();
	
	public Question getQuestion() {
		return question;
	}

	protected void setQuestion(Question question) {
		this.question = question;
	}

}
