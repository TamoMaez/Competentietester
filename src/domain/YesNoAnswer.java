package domain;

public class YesNoAnswer extends Answer {
	
	public YesNoAnswer(Question question) {
		super(question);
	}

	private Option option;
	
	@Override
	public void addOption(Option option) {
		if (option == null) {
			throw new DomainException("Option can't be NULL");
		}
		this.option = option;
	}

	@Override
	public boolean isCorrect() {
		return this.option.isCorrect();
	}

	@Override
	public boolean isForCategory(Category category) {
		for(Score score : this.option.getScores()) {
			if (score.getCategory().equals(category)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int getScoreByCategory(Category category) {
		return this.option.getScoreByCategory(category).getPoints();
	}
	
	public int getScore() {
		return this.getScoreByCategory(this.question.getCategories().get(0));
	}

	@Override
	public int getMaximumScore(Category category) {
		return this.option.getScoreByCategory(category).getMaxPoints();
	}

	public int getMaximumScore() {
		return this.getMaximumScore(this.question.getCategories().get(0));
	}
}
