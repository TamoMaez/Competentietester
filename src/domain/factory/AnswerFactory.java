/**
 * 
 * @author Ruben Thielemans, Tamo Maes, Georges Petrofski & Sam Hendrickx
 *
 */
package domain.factory;

import domain.Answer;
import domain.QuestionAnswerType;
import domain.MCAnswer;
import domain.Question;
import domain.YesNoAnswer;

public class AnswerFactory {
	
	public static Answer createAnswer(Question question, QuestionAnswerType type) {
		Answer answer = null;
		
		if (type == QuestionAnswerType.YesNo) {
			answer = new YesNoAnswer(question);
		} else if (type == QuestionAnswerType.MC) {
			answer = new MCAnswer(question);
		}
		
		return answer;
	}
}
