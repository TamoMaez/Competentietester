package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Question {
	private String question;
	private List<Category> categories;
	protected List<Option> options;
	private int timesAnswered;
	
	public Question() {}
	
	public Question(String question){
		setQuestion(question);
		
		categories = new ArrayList<>();
		options = new ArrayList<Option>();
		this.timesAnswered = 0;
	}

	public void setQuestion(String question) {
		if (question == null) {
			throw new DomainException("Question can't be NULL");
		}
		question = question.trim();
		if (question.length() < 2) {
			throw new DomainException("A question should contain more than 2 chars");
		}
		/*
		 * Later beslissen
		 *
		if (question.length() > 300) {
			throw new DomainException("A question should not contain more than 300 chars");
		}
		*/
		this.question = question;
	}
	
	public String getQuestion() {
		return question;
	}

	public List<String> getStatementsShuffled() {
		List<String> shuffledStatements = new ArrayList<String>(getStatements());
		Collections.shuffle(shuffledStatements);
		return shuffledStatements;
	}
	
	public List<Category> getCategories() {
		return this.categories;
	}
	
	public Category removeCategory(Category category){
		return this.categories.remove(categories.indexOf(category));
	}
	
	public void addCategory(Category category) {
		if (category == null) {
			throw new DomainException("A category can't be NULL");
		}
		this.categories.add(category);
	}
	

	public boolean removeOption(Option o) {
		if (options.size() <= 1) {
			throw new DomainException("At least one answer must be added");
		}
		return options.remove(o);
	}
	
	public  List<Option>getOptions(){
		return options;
	}
	
	public List<String> getStatements(){
		List<String> statementsStrings = new ArrayList<String>();
		for(Option s : getOptions()){
			statementsStrings.add(s.getStatement());
		}
		return statementsStrings;
	}

	public Option getOption(String statement) {
		Option option = null;
		for(int i = 0; i < getOptions().size() && option == null; i++){
			if(getOptions().get(i).getStatement().equals(statement)){
				option = getOptions().get(i);
			}
		}
		return option;
	}
	
	public List<Option> getCorrectOptions() {
		List<Option> correctOptions = new ArrayList<>();
		
		for (Option option : this.options) {
			if (option.isCorrect()) {
				correctOptions.add(option);
			}
		}
		return correctOptions;
	}
	
	public List<String> getCorrectStatements() {
		List<String> correctOptions = new ArrayList<>();
		
		for (Option option : this.options) {
			if (option.isCorrect()) {
				correctOptions.add(option.getStatement());
			}
		}
		return correctOptions;
	}
	
	public void incrementTimesAnswered() {
		this.timesAnswered++;
	}
	
	public int getTimesAnswered() {
		return this.timesAnswered;
	}
	
	public int getMaxPointsForCategory(Category category){
		return options.get(0).getScoreByCategory(category).getMaxPoints();
	}
	
	public abstract void setOptions(List<Option> options);
	
	public abstract void addOption(Option option);
	
	public abstract void setStatements(List<String> statements);
	
	public abstract void addStatement(String statement); 

	public abstract QuestionAnswerType getType();
	
	public String toString(){
		return getQuestion();
	}
	
	
	public Option getWrongOption() {
		for (Option option : this.options) {
			if (!option.isCorrect()) {
				return option;
			}
		}
		return null;
	}
	
}
