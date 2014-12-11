package domain.questionSelectorStrategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import domain.Question;
import domain.YesNoQuestion;

public class RandomYesNoSelector implements QuestionSelector {

	@Override
	public List<Question> selectQuestions(List<Question> questions, int numberOfQuestions) {
		List<Question> yesNoQuestions = new ArrayList<>();
		
		for (Question question : questions) {
			if (question instanceof YesNoQuestion) {
				yesNoQuestions.add(question);
			}
		}
		
		Collections.shuffle(yesNoQuestions);
		
		if (numberOfQuestions > yesNoQuestions.size()) {
			numberOfQuestions = yesNoQuestions.size();
			
			// We negeren het probleem dat er te weinig vragen aanwezig kunnen zijn setten de numberOfQuestions als de size van array
		}
		return yesNoQuestions.subList(0, numberOfQuestions);
	}
	
	public String toString() {
		return "Random yes/no";
	}
}
