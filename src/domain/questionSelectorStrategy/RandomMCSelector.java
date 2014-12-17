/**
 * 
 * @author Ruben Thielemans, Tamo Maes, Georges Petrofski & Sam Hendrickx
 *
 */
package domain.questionSelectorStrategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import domain.Question;
import domain.MCQuestion;

public class RandomMCSelector implements QuestionSelector {

	@Override
	public List<Question> selectQuestions(List<Question> questions, int numberOfQuestions) {
		List<Question> MCQuestions = new ArrayList<>();
		
		for (Question question : questions) {
			if (question instanceof MCQuestion) {
				MCQuestions.add(question);
			}
		}
		
		Collections.shuffle(MCQuestions);
		
		if (numberOfQuestions > MCQuestions.size()) {
			numberOfQuestions = MCQuestions.size();
			
			// We negeren het probleem dat er te weinig vragen aanwezig kunnen zijn setten de numberOfQuestions als de size van array
		}
		return MCQuestions.subList(0, numberOfQuestions);
	}
	
	public String toString() {
		return "Random MC";
	}
	
}
