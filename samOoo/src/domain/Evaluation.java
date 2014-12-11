package domain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Evaluation {
	private List<Answer> answers;
	private int score;
	private int maxScore;
	private int duration;
	private Calendar date;

	public List<Answer> getAnswers() {
		return answers;
	}
	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}
	public int getTotalScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getTotalMaxScore() {
		return maxScore;
	}
	public void setMaxScore(int maxScore) {
		this.maxScore = maxScore;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public Calendar getDate() {
		return date;
	}
	public void setDate(Calendar endTime) {
		this.date = endTime;
	}
	
	public List<Question> getQuestionsAsked() {
		List<Question> questions = new ArrayList<Question>();
		
		for (Answer answer : this.answers) {
			questions.add(answer.getQuestion());
		}
		
		return questions;
	}
	public Set<Category> getCategoriesAsked() {
		Set<Category> categories = new TreeSet<>();
		
		for (Question question : this.getQuestionsAsked()) {
			categories.addAll(question.getCategories());
		}
		
		return categories;
	}
	
	public List<Question> getCorrectQuestions() {
		List<Question> correctQuestions = new ArrayList<>();
		
		for (Answer answer : this.answers) {
			if (answer.isCorrect()) {
				correctQuestions.add(answer.getQuestion());
			}
		}
		
		return correctQuestions;
	}
	
	public List<Question> getWrongQuestions() {
		List<Question> wrongQuestions = new ArrayList<>();
		
		for (Answer answer : this.answers) {
			if (!answer.isCorrect()) {
				wrongQuestions.add(answer.getQuestion());
			}
		}
		
		return wrongQuestions;
	}
	
	public int getNumberOfCorrectQuestionsPerCategory(Category category) {
		int count = 0;
		
		for (Question question : this.getCorrectQuestions()) {
			if (question.getCategories().contains(category)) {
				count++;
			}
		}
		
		return count;
	}
	
	public int getTotalNumberOfQuestionsPerCategory(Category category) {
		int count = 0;
		
		for (Question question : this.getQuestionsAsked()) {
			if (question.getCategories().contains(category)) {
				count++;
			}
		}
		
		return count;
	}
	
	public String getScoreOn(int maxScore) {
		double score = Math.round(((double) getTotalScore() / (double)getTotalMaxScore()) * maxScore);
		
		return String.format("%.2f / " + maxScore, score);
	}
}
