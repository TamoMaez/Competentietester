package domain.scoreCalculatorStrategy;

import java.util.List;

import domain.Answer;


public class GisScoreCalculator implements ScoreCalculator {

	@Override
	public int calculateScore(List<Answer> answers, int totalMaxScore, int timePassed, int timePerQuestion) {
		int score = 0;
		
		for (Answer answer : answers) {
			if (answer.getScore() != 0) {
				score += answer.getScore();
			} else {
				score--;
			}
		}
		
		return score < 0 ? 0 : score;
	}
	
	public String toString() {
		return "Gis calculator";
	}

}
