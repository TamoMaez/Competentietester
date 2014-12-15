package domain.scoreCalculatorStrategy;

import java.util.List;

import domain.Answer;

public class TimeScoreCalculator implements ScoreCalculator {

	@Override
	public int calculateScore(List<Answer> answers, int totalMaxScore, int timePassed, int timePerQuestion) {
		int score = 0;
		
		for (Answer answer : answers) {
			score += answer.getScore();
		}
		
		if (timePassed < ( timePerQuestion * answers.size() ) / 2) {
			score += (2 / totalMaxScore) * 20;
			System.out.println("YUPPP");
		}
		
		if (score > totalMaxScore) {
			score = score - (score % totalMaxScore);
		} 
		
		return score;
	}
	
	public String toString() {
		return "Time calculator";
	}

}
