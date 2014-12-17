/**
 * 
 * @author Ruben Thielemans, Tamo Maes, Georges Petrofski & Sam Hendrickx
 *
 */
package domain.scoreCalculatorStrategy;

import java.util.List;

import domain.Answer;


public interface ScoreCalculator {

	int calculateScore(List<Answer> answers);
	
}
