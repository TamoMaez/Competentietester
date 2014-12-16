package view.panels;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.BackToUserHomeAction;
import domain.Category;
import domain.Evaluation;
import domain.Option;
import domain.Question;
import domain.facade.CompetentieTesterFacade;

public class EvaluationTestPanel extends JPanel {

	private CompetentieTesterFacade service;
	
	private static final long serialVersionUID = 1L;
	private GridBagConstraints constraints = new GridBagConstraints();
	private Evaluation evaluation;
	
	private BackToUserHomeAction backToUserHomeAction;
	
	public EvaluationTestPanel(BackToUserHomeAction backToUserHomeAction, CompetentieTesterFacade service) {
		this.setService(service);
		setLayout(new GridBagLayout());
		this.backToUserHomeAction = backToUserHomeAction;
	}
	
	
	private void initConstraints() {
		constraints.insets = new Insets(10, 10, 0, 10);
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 1.0;
		constraints.weighty = 1.0;
		
	}
	
	protected GridBagConstraints getConstraints() {
		return constraints;
	}
	
	protected void addToPanel(Component component) {
		add(component, getConstraints());
	}

	protected void changeConstraints(int height, int width, int gridx, int gridy) {
		constraints.gridheight = height;
		constraints.gridwidth = width;
		constraints.gridx = gridx;
		constraints.gridy = gridy;
	}

	private void initTitle(int row) {
		changeConstraints(1, 1, 1, row);

		JLabel title = new JLabel("<html><h1>Evaluation:</h1></html>");
		this.addToPanel(title);
	}
	
	private void showTestInfo(int row) {
		changeConstraints(1, 1, 1, row);
		
		String datetime = new SimpleDateFormat("dd/MM/YYYY HH:mm:ss").format(evaluation.getDate().getTime());

		String label = "<html><h3>Duration: " + evaluation.getDuration() + " seconds</h3>";
		label += "<h3>End datetime: " + datetime + "</h3>";
		label += evaluation.getScoreOn(20);
		label += "</html>";
		
		this.addToPanel(new JLabel(label));

	}	
	
	private void showNumberOfCorrectQuestionsPerCategory(int row) {
		changeConstraints(1, 1, 1, row);
		
		String label = "<html><h3>Number of correct question per category:</h3>";
		
		for (Category category : evaluation.getCategoriesAsked()) {
			changeConstraints(1, 1, 1, row);

			label += "<strong>" + category.getTitle() + ": </strong>";
			label += evaluation.getNumberOfCorrectQuestionsPerCategory(category) + " out of " + evaluation.getTotalNumberOfQuestionsPerCategory(category) + " questions correct<br>";
			row++;
		}
		this.addToPanel(new JLabel(label));
	
	}
	
	private int showWrongQuestions(int row) {
		changeConstraints(1, 1, 2, row);
		
		JLabel title = new JLabel("<html><h2>Wrong questions:</h2></html>");
		this.addToPanel(title);
		row++;
		
		String label = "";
		for (Question question : evaluation.getWrongQuestions()) {
			changeConstraints(1, 1, 2, row);

			label = "<html><strong>" + question.getQuestion() + ":</strong> <br>";
			label += "Correct answers: <br>";

			for (Option option : question.getCorrectOptions()) {

				label += "\t -" + option.getStatement() + "<br>";
				
			}
			label += "</html>";
			JLabel correctAnswer = new JLabel(label);
			this.addToPanel(correctAnswer);
			row++;
			
		}
		
		return row;
	
	}
	
	private void showBackToHomeButton(int row) {
		changeConstraints(1, 1, 2, row);
		
		this.addToPanel(new JButton(this.backToUserHomeAction));
	
	}
	
	public void generatePanel() {
		this.removeAll();

		initConstraints();
		
		// Left column
		int row = 0;
		this.initTitle(row);
		row++;
		
		this.showTestInfo(row);
		row++;
		
		this.showNumberOfCorrectQuestionsPerCategory(row);
		row++;
		
		// Right column
		row = 0;
		row = this.showWrongQuestions(row);
		
		row++;
		this.showBackToHomeButton(row);

	}


	public void setEvaluation(Evaluation lastEvaluation) {
		this.evaluation = lastEvaluation;
	}


	public CompetentieTesterFacade getService() {
		return service;
	}


	public void setService(CompetentieTesterFacade service) {
		this.service = service;
	}
	
}
