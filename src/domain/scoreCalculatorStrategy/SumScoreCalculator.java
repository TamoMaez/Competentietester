package domain.scoreCalculatorStrategy;

import java.util.List;

import domain.Answer;


public class SumScoreCalculator implements ScoreCalculator {

	@Override
	public int calculateScore(List<Answer> answers, int totalMaxScore, int timePassed, int timePerQuestion) {
		int score = 0;
		
		for (Answer answer : answers) {
			score += answer.getScore();
		}
		
		return score;
	}

	public String toString() {
		return "Sum calculator";
	}
	
}
