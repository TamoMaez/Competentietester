package domain.facade;

import java.util.ArrayList;
import java.util.List;

import domain.Answer;
import domain.DomainException;
import domain.Option;
import domain.Question;
import domain.QuestionAnswerType;
import domain.factory.AnswerFactory;

public class UserFacade extends CompetentieTesterFacade {
	private List<Answer> answers;
	
	public UserFacade() {
		super();
		answers = new ArrayList<Answer>();
	}

	private void addAnswer(Answer answer) {
		if(answer == null) {
			throw new DomainException("Answer can't be NULL");
		}
		answers.add(answer);
	}

	public void answerQuestion(List<Option> options, Question question, QuestionAnswerType type) {
		if(question == null) {
			throw new DomainException("Question can't be NULL");
		}
		
		Answer answer = AnswerFactory.createAnswer(question, type);
		
		for (Option option : options) {
			answer.addOption(option);
		}
		
		this.addAnswer(answer);
	}
	
	
	
}
