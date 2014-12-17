/**
 * 
 * @author Ruben Thielemans, Tamo Maes, Georges Petrofski & Sam Hendrickx
 *
 */
package domain.facade;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;









import org.apache.commons.io.FileExistsException;

import database.FileManager;
import domain.Answer;
import domain.Category;
import domain.DomainException;
import domain.Evaluation;
import domain.Feedback;
import domain.Option;
import domain.Question;
import domain.QuestionAnswerType;
import domain.Score;
import domain.comparators.CompareEvaluationsByDate;
import domain.factory.AnswerFactory;
import domain.factory.QuestionFactory;
import domain.questionSelectorStrategy.LeastAskedSelector;
import domain.questionSelectorStrategy.QuestionSelector;
import domain.questionSelectorStrategy.RandomMCSelector;
import domain.questionSelectorStrategy.RandomSelector;
import domain.questionSelectorStrategy.RandomYesNoSelector;
import domain.scoreCalculatorStrategy.GisScoreCalculator;
import domain.scoreCalculatorStrategy.ScoreCalculator;
import domain.scoreCalculatorStrategy.SumScoreCalculator;
import domain.scoreCalculatorStrategy.TimeScoreCalculator;


public class CompetentieTesterFacade {
	
	protected Map<String, Category> categories;
	protected List<Question> questions;
	protected List<Question> questionsAsked;
	protected QuestionSelector questionSelector; // -> List<Question>
	protected ScoreCalculator scoreCalculator; // -> returns a score/100
	protected int numberOfQuestions;
	protected FileManager fileManager = new FileManager();
	public List<Answer> answers;
	
	private List<QuestionSelector> questionSelectors;
	private LeastAskedSelector leastAskedSelector;
	private RandomMCSelector randomMCSelector;
	private RandomSelector randomSelector;
	private RandomYesNoSelector randomYesNoSelector;
	private List<ScoreCalculator> scoreCalculators;
	private SumScoreCalculator sumScoreCalculator;
	private TimeScoreCalculator timeScoreCalculator;
	private GisScoreCalculator gisScoreCalculator;
	
	private int timePerQuestion;
	
	public Calendar startTime;
	public Calendar endTime;
	
	public Map<Calendar, Evaluation> evaluations;
	
	public int currentQuestionPosition = 0;
	
	
	
	public CompetentieTesterFacade() {
		numberOfQuestions = 4;
		timePerQuestion = 20;
		
		questionSelectors = new ArrayList<QuestionSelector>();
		randomSelector = new RandomSelector();
		questionSelectors.add(randomSelector);
		leastAskedSelector = new LeastAskedSelector();
		questionSelectors.add(leastAskedSelector);
		randomMCSelector = new RandomMCSelector();
		questionSelectors.add(randomMCSelector);
		randomYesNoSelector = new RandomYesNoSelector();
		questionSelectors.add(randomYesNoSelector);
		
		scoreCalculators = new ArrayList<ScoreCalculator>();
		sumScoreCalculator = new SumScoreCalculator();
		scoreCalculators.add(sumScoreCalculator);
		timeScoreCalculator = new TimeScoreCalculator();
		scoreCalculators.add(timeScoreCalculator);
		gisScoreCalculator = new GisScoreCalculator();
		scoreCalculators.add(gisScoreCalculator);
		
		this.questionSelector = randomSelector;
		this.scoreCalculator = sumScoreCalculator;
		
		
		categories = new HashMap<String, Category>();
		questions = new ArrayList<Question>();
		answers = new ArrayList<Answer>();
		evaluations = new HashMap<>();
		/**
		 * TESTING
		 */
		//this.read();
	}
	
	public void start() {
		if(getAllQuestions().size() < 1){
			throw new DomainException("There are no questions!");
		}
		answers = new ArrayList<Answer>();
		this.currentQuestionPosition = 0;
		this.selectQuestions();
		this.startTime = Calendar.getInstance();
	}
	
	public void stop() {
		this.endTime = Calendar.getInstance();
		
		Evaluation evaluation = new Evaluation();
		evaluation.setAnswers(answers);
		evaluation.setDate(endTime);
		evaluation.setDuration(this.getTimePassed());
		evaluation.setMaxScore(this.getTotalMaxScore());
		evaluation.setScore(this.getTotalScore());
		
		this.evaluations.put(this.endTime, evaluation);
	}
	
	
	public List<Question> getAllQuestions() {
		return this.questions;
	}
	
	public List<Question> selectQuestions() {
		this.questionsAsked = this.questionSelector.selectQuestions(this.questions, numberOfQuestions);
		return questionsAsked;
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
	
	public Question getQuestion(Question q) {
		return questions.get(questions.indexOf(q));
	}
	
	/*
	 * Admin
	 */
	public boolean removeQuestion(Question q){
		return this.questions.remove(q);
	}
	
	public Category removeCategory(Category c){
		return this.categories.remove(c.getTitle());
	}
	
	public void createNewQuiz() {
		write();
	}
	
	public void read(){
		fileManager.read(this);
	}
	
	public void write(){
		fileManager.write(this);
	}
	
	public void writeToCurrentFile() throws FileExistsException{
		fileManager.writeToCurrentFile(this);
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
		public void setNumberOfQuestions(int numberOfQuestions) {
			if (numberOfQuestions < 4 || numberOfQuestions > 10 || numberOfQuestions > this.questions.size()) {
				throw new DomainException("Number of questions should be between 4 and 10 and can't be greater than the number of questions available");
			}
			this.numberOfQuestions = numberOfQuestions;
		}
		
		public int getNumberOfQuestions() {
			return this.numberOfQuestions;
		}
		
		public int getTimePerQuestion() {
			return this.timePerQuestion;
		}
		
		public void setTimePerQuestion(int timePerQuestion) {
			if(timePerQuestion < 10 || timePerQuestion > 60) {
				throw new DomainException("Min. 10 seconds per question and max 60 seconds per question.");
			}
			this.timePerQuestion = timePerQuestion;
		}
		
		public void setQuestionSelector(QuestionSelector questionSelector) {
			if (questionSelector == null) {
				throw new DomainException("QuestionSelector can't be NULL");
			}
			this.questionSelector = questionSelector;
		}	
		
		public QuestionSelector getQuestionSelector() {
			return this.questionSelector;
		}
		
		public List<QuestionSelector> getQuestionSelectors() {
			return questionSelectors;
		}
		
		public void setScoreCalculator(ScoreCalculator scoreCalculator) {
			if (scoreCalculator == null) {
				throw new DomainException("ScoreCalculcator can't be NULL");
			}
			this.scoreCalculator = scoreCalculator;
		}
		
		public ScoreCalculator getScoreCalculator() {
			return this.scoreCalculator;
		}
		
		public List<ScoreCalculator> getScoreCalculators() {
			return this.scoreCalculators;
		}
		
		public void changeSettings(int numberOfQuestions, QuestionSelector questionSelector, ScoreCalculator scorecalculator) {
			this.setNumberOfQuestions(numberOfQuestions);
			this.setQuestionSelector(questionSelector);
			this.setScoreCalculator(scorecalculator);
		}	
		
	
	
	
	/*
	 * User 
	 */
	private void addAnswer(Answer answer) {
		if(answer == null) {
			throw new DomainException("Answer can't be NULL");
		}
		answers.add(answer);
	}

	public void answerQuestion(List<Option> options, Question question) {
		if(question == null) {
			throw new DomainException("Question can't be NULL");
		}
		
		question.incrementTimesAnswered();
		Answer answer = AnswerFactory.createAnswer(question, question.getType());
		
		for (Option option : options) {
			answer.addOption(option);
		}
		
		this.addAnswer(answer);
	}
	

	public Calendar getStartTime() {
		return startTime;
	}

	public Calendar getEndTime() {
		return endTime;
	}

	public int getTimePassed() {
		return (int) ((this.endTime.getTime().getTime() - this.startTime.getTime().getTime()) / 1000);
	}
	
	public Question getNextQuestion() {
		if(!isLastQuestion()){
			return this.questionsAsked.get(currentQuestionPosition++);
		}
		return null;
	}
	
	public boolean isLastQuestion(){
		return this.currentQuestionPosition==this.questionsAsked.size();
	}
	
	public int getTotalScore() {
		int score = 0;
		
		for (Answer answer : this.answers) {
			score += answer.getScore();
		}
		
		return score;
	}
	public int getTotalMaxScore() {
		int score = 0;
		
		for (Answer answer : this.answers) {
			score += answer.getMaximumScore();
		}
		
		return score;
	}
	

	public Map<Calendar, Evaluation> getEvaluations() {
		return this.evaluations;
	}
	
	public List<Evaluation> getEvaluationsList() {
		List<Evaluation> sorted = new ArrayList<Evaluation>(this.evaluations.values());
		Collections.sort(sorted, new CompareEvaluationsByDate());
		
		return sorted;
	}

	public Evaluation getEvaluation(Calendar date) {
		return this.evaluations.get(date);
	}

	public Evaluation getLastEvaluation() {
		return this.getEvaluation(endTime);
	}
	
	
	
	
	
	
	
	
	
	
	public void addCategoryToQuestion(Question q, Category c, int maxPoint) {
		q.addCategory(c);
		for(Option o : q.getOptions()){
			if(!q.getCorrectOptions().contains(o)){
				Score score = new Score(0, c);
				score.setMaxPoints(maxPoint);
				o.addScore(score);
			}
		}
		for(Option o : q.getCorrectOptions()){
			o.addScore(new Score(maxPoint, c));
		}
	}
	
	
}
