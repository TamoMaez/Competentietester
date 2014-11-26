package domain.facade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.Category;
import domain.Question;
import domain.questionSelectorStrategy.QuestionSelector;
import domain.questionSelectorStrategy.RandomSelector;
import domain.scoreCalculatorStrategy.ScoreCalculator;


public abstract class CompetentieTesterFacade {
	
	protected Map<String, Category> categories;
	protected List<Question> questions;
	protected QuestionSelector questionSelector = new RandomSelector(); // -> List<Question>
	protected ScoreCalculator scoreCalculator; // -> returns a score/100
	protected int numberOfQuestions = 4;
	
	
	public CompetentieTesterFacade() {
		categories = new HashMap<String, Category>();
		questions = new ArrayList<Question>();
	}
	
	public List<Question> getQuestions() {
		return this.questionSelector.selectQuestions(this.questions, numberOfQuestions);
	}
	
	public List<Category> getCategories() {
		return new ArrayList<Category> (this.categories.values());
	}
	
	public Category getCategory(String naam) {
		return this.categories.get(naam);
	}
}
