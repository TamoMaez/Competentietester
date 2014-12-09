package domain;

import java.util.List;

public class MCQuestion extends Question {
	
	public MCQuestion(String question){
		super(question);
	}
	
	public MCQuestion(String question,  List<Option> options){
		super(question);
		setOptions(options);
	}

	public void setOptions(List<Option> options) {
		if (options.size() > 5) {
			throw new DomainException("The list of options can't contain more than 5 options.");
		}
		this.options = options;
	}
	
	public void addOption(Option option) {
		if (this.options.size() >= 5) {
			throw new DomainException("The list of options can't contain more than 5 options.");
		}
		this.options.add(option);
	}

	public void setStatements(List<String> statements) {
		if (statements.size() > 5) {
			throw new DomainException("The list of statements can't contain more than 5 options.");
		}
		
		getOptions().clear();
		
		for(String s : statements) {
			this.addStatement(s);
		}
	}
	
	public void addStatement(String statement) {
		if (this.options.size() >= 5) {
			throw new DomainException("The list of options can't contain more than 5 options.");
		}
		
		Option option = new Option(statement);
		
		for(Category category : this.getCategories()) {
			option.addScore(new Score(category));
		}

	}

	@Override
	public QuestionAnswerType getType() {
		// TODO Auto-generated method stub
		return QuestionAnswerType.MC;
	}
	
	
}
