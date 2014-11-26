package domain.questionSelectorStrategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import domain.Question;

public class RandomSelector implements QuestionSelector {

	@Override
	public List<Question> selectQuestions(List<Question> questions, int numberOfQuestions) {
		List<Question> shuffleQuestions = new ArrayList<>(questions);
		Collections.shuffle(shuffleQuestions);
		return shuffleQuestions.subList(0, numberOfQuestions);
	}
}
