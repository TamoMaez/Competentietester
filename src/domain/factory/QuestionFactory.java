/**
 * 
 * @author Ruben Thielemans, Tamo Maes, Georges Petrofski & Sam Hendrickx
 *
 */
package domain.factory;

import java.util.List;

import domain.Category;
import domain.MCQuestion;
import domain.Question;
import domain.QuestionAnswerType;
import domain.YesNoQuestion;

public class QuestionFactory {

	public static Question createQuestion(String statement, List<Category> categories, QuestionAnswerType type) {
		Question question = null;
		
		if (type == QuestionAnswerType.YesNo) {
			question = new YesNoQuestion(statement);
		} else if (type == QuestionAnswerType.MC) {
			question = new MCQuestion(statement);
		}
		
		for (Category category : categories) {
			question.addCategory(category);
		}
		
		return question;
	}
	
	public static Question createQuestion(String statement, QuestionAnswerType type) {
		Question question = null;
		
		if (type == QuestionAnswerType.YesNo) {
			question = new YesNoQuestion(statement);
		} else if (type == QuestionAnswerType.MC) {
			question = new MCQuestion(statement);
		}
		
		return question;
	}
	
}
