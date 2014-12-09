package domain.questionSelectorStrategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import domain.CompareByTimesAsked;
import domain.Question;

public class LeastAskedSelector implements QuestionSelector {
	
	@Override
	public List<Question> selectQuestions(List<Question> questions, int numberOfQuestions) {
		List<Question> questionsOrderedByTimesAsked = new ArrayList<>(questions);
		
		Collections.sort(questionsOrderedByTimesAsked, new CompareByTimesAsked());

		return questionsOrderedByTimesAsked.subList(0, numberOfQuestions);

	}

	public String toString() {
		return "Least asked";
	}
	
}
