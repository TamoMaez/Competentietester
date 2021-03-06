/**
 * 
 * @author Ruben Thielemans, Tamo Maes, Georges Petrofski & Sam Hendrickx
 *
 */
package domain.questionSelectorStrategy;

import java.util.List;

import domain.Question;

public interface QuestionSelector {
	
	List<Question> selectQuestions(List<Question> questions, int numberOfQuestions);
	
}
