package domain.facade;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import database.FileReader;
import database.FileWriter;
import domain.Category;
import domain.DomainException;
import domain.Feedback;
import domain.Option;
import domain.Question;
import domain.QuestionAnswerType;
import domain.Score;
import domain.factory.QuestionFactory;
import domain.questionSelectorStrategy.QuestionSelector;
import domain.scoreCalculatorStrategy.ScoreCalculator;

public class AdministratorFacade extends CompetentieTesterFacade {
	
	public AdministratorFacade() {
		super();	
	}
	
	public void read(){
		fileManager.read(this);
	}
	
	public void write(){
		fileManager.write(this);
	}
	
	public void addQuestion(Question question) {
		if(question == null) {
			throw new DomainException("Question can't be NULL");
		}
		
		questions.add(question);
	}

	public Question createQuestion(String statement, List<String> categories, QuestionAnswerType type) {
		List<Category> categorieenVanVraag = new ArrayList<>();
		
		for(String stmnt : categories) {
			categorieenVanVraag.add(this.categories.get(stmnt));
		}
		
		return QuestionFactory.createQuestion(statement, categorieenVanVraag, type);
	}
	
	public Question createQuestion(String statement, QuestionAnswerType type) {	
		return QuestionFactory.createQuestion(statement, type);
	}
	
	public void addCategory(String title, String description, List<String> feedbackTexts) {
		Category category = new Category(title, description);
		
		for (String feedbackText : feedbackTexts) {
			category.addFeedback(new Feedback(feedbackText));
		}
		
		addCategory(category);
	}
	
	public void addCategory(Category category){
		this.categories.put(category.getTitle(), category);
	}
	
	// ? moet dit niet question.get(question).addOption zijn ?
	public void addOptionForQuestion(Question question, Option option) {
		question.addOption(option);
	}
	
	public Option createOption(String statement) {
		if (statement == null) {
			throw new DomainException("Statement can't be NULL");
		}
		
		return new Option(statement);
	}
	
	public void addScoreForOption(Option option, Score score) {
		option.addScore(score);
	}
		
	public Score createScore(int maxPoints, int points,  String categoryNaam) {
		if (categoryNaam == null) {
			throw new DomainException("Category can't be NULL");
		}
		Category category = this.categories.get(categoryNaam);
		
		return new Score(maxPoints, points, category);
	}
	
	// Settings
	private void setNumberOfQuestions(int numberOfQuestions) {
		if (numberOfQuestions < 4 || numberOfQuestions > 10 || numberOfQuestions > this.questions.size()) {
			throw new DomainException("Number of questions should be between 4 and 10 and can't be greater than the number of questions available");
		}
		this.numberOfQuestions = numberOfQuestions;
	}
	
	private void setQuestionSelector(QuestionSelector questionSelector) {
		if (questionSelector == null) {
			throw new DomainException("QuestionSelector can't be NULL");
		}
		this.questionSelector = questionSelector;
	}	
	
	private void setScoreCalculator(ScoreCalculator scoreCalculator) {
		if (scoreCalculator == null) {
			throw new DomainException("ScoreCalculcator can't be NULL");
		}
		this.scoreCalculator = scoreCalculator;
	}
	
	public void changeSettings(int numberOfQuestions, QuestionSelector questionSelector, ScoreCalculator scorecalculator) {
		this.setNumberOfQuestions(numberOfQuestions);
		this.setQuestionSelector(questionSelector);
		this.setScoreCalculator(scorecalculator);
	}	
	
}
