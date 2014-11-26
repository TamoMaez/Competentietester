package domain;

import java.util.ArrayList;
import java.util.List;

public class Option {
	private String statement;
	private List<Score> scores;
	
	
	public Option(String statement){
		this.scores = new ArrayList<>();
		setStatement(statement);
	}
	
	public String getStatement() {
		return statement;
	}
	private void setStatement(String statement) {
		this.statement = statement;
	}
	
	public List<Score> getScores() {
		return scores;
	}
	
	public void addScore(Score score) {
		if (score == null) {
			throw new DomainException("Score can't be NULL");
		}
		this.scores.add(score);
	}

	public boolean isCorrect() {
		return this.scores.get(0).getPoints() == this.scores.get(0).getMaxPoints();
	}
	
	public Score getScoreByCategory(Category category) {
		if (category == null) {
			throw new DomainException("Category can't be NULL");
		}
		
		for (Score score : this.scores) {
			if (score.getCategory().equals(category)) {
				return score;
			}
		}
		
		return null;
	}
	
}
