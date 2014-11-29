package domain.facade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import database.FileReader;
import database.FileWriter;
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
	protected int numberOfQuestions = 9;
	protected FileWriter writer;
	protected FileReader reader;
	
	
	public CompetentieTesterFacade() {
		categories = new HashMap<String, Category>();
		questions = new ArrayList<Question>();
	}
	
	public List<Question> getAllQuestions() {
		return this.questions;
	}
	
	public List<Question> selectQuestions() {
		return this.questionSelector.selectQuestions(this.questions, numberOfQuestions);
	}
	
	public List<Category> getCategories() {
		return new ArrayList<Category> (this.categories.values());
	}
	
	public boolean containsCategory(String title){
		return this.categories.containsKey(title);
	}
	
	public Map<String, Category> getCategoriesMap() {
		return this.categories;
	}
	
	public Category getCategory(String naam) {
		return this.categories.get(naam);
	}
}
