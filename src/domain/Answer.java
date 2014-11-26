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
	
	public abstract int getScore(Category category);
	
	public abstract int getMaximumScore(Category category);
	
	protected Question getQuestion() {
		return question;
	}

	protected void setQuestion(Question question) {
		this.question = question;
	}

}
