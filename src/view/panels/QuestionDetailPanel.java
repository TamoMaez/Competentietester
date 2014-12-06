package view.panels;

import java.util.List;

import javax.swing.JPanel;

import controller.question.QuestionDoneAction;
import domain.Question;

public class QuestionDetailPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private Question question;

	public QuestionDetailPanel(QuestionDoneAction questionDoneAction) {
		// TODO Auto-generated constructor stub
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public void setAnswers(List<Question> questions) {
		// TODO Auto-generated method stub
		
	}

}
