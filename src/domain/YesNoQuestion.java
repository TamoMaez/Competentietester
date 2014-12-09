package domain;

import java.util.List;

public class YesNoQuestion extends Question {
	
	public YesNoQuestion(String question){
		super(question);
	}
	
	public YesNoQuestion(String question,  List<Option> options){
		super(question);
		setOptions(options);
	}

	public void setOptions(List<Option> options) {
		if (options.size() > 2) {
			throw new DomainException("The list of options can't contain more than 2 options.");
		}
		this.options = options;
	}
	
	public boolean removeOption(Option o) {
		if (options.size() <= 2) {
			throw new DomainException("Yes and No are the only answers possible");
		}
		return false;
	}
	
	public void addOption(Option option) {
		if (this.options.size() >= 2) {
			throw new DomainException("The list of options can't contain more than 2 options.");
		}
		this.options.add(option);
	}

	public void setStatements(List<String> statements) {
		if (statements.size() > 2) {
			throw new DomainException("The list of statements can't contain more than 2 options.");
		}
		
		getOptions().clear();
		
		for(String s : statements) {
			this.addStatement(s);
		}
	}
	
	public void addStatement(String statement) {
		if (this.options.size() >= 2) {
			throw new DomainException("The list of options can't contain more than 2 options.");
		}
		
		Option option = new Option(statement);
		
		for(Category category : this.getCategories()) {
			option.addScore(new Score(category));
		}

	}

	@Override
	public QuestionAnswerType getType() {
		// TODO Auto-generated method stub
		return QuestionAnswerType.YesNo;
	}
	

}
