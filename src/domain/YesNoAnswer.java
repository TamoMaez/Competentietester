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
	public int getScore(Category category) {
		return this.option.getScoreByCategory(category).getPoints();
	}

	@Override
	public int getMaximumScore(Category category) {
		return this.option.getScoreByCategory(category).getMaxPoints();
	}

}
