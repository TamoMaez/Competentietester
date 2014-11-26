package domain;

import java.util.ArrayList;
import java.util.List;

public class MCAnswer extends Answer {
	
	public MCAnswer(Question question) {
		super(question);
	}

	private List<Option> options = new ArrayList<Option>();

	public List<Option> getOptions() {
		return options;
	}

	public void addOption(Option option) {
		this.options.add(option);
	}
	
	public boolean isCorrect(){
		boolean correct = true;
		for(int i = 0; i < options.size() && correct; i++){
			if(!options.get(i).isCorrect()){
				correct = false;
			}
		}
		return correct;
	}

	public boolean isForCategory(Category category) {
		for(Score score : this.options.get(0).getScores()) {
			if (score.getCategory().equals(category)) {
				return true;
			}
		}
		return false;
	}
	

	public int getScore(Category category){
		int score = 0;
		for(Option o : options){
			if(o.getScoreByCategory(category) != null) {
				score += o.getScoreByCategory(category).getPoints();
			}
		}
		return score;
	}
	

	public int getMaximumScore(Category category){
		int score = 0;
		for(Option o : options){
			if(o.getScoreByCategory(category) != null) {
				score += o.getScoreByCategory(category).getMaxPoints();
			}
		}
		return score;
	}

}
