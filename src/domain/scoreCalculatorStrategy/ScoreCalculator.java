package domain.scoreCalculatorStrategy;

import java.util.List;

import domain.Answer;


public interface ScoreCalculator {

	int calculateScore(List<Answer> answers, int totalMaxScore, int timePassed, int timePerQuestion);
	
}
