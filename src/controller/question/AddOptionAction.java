package controller.question;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import view.panels.questions.QuestionDetailPanel;
import domain.Category;
import domain.DomainException;
import domain.Option;
import domain.Question;
import domain.Score;

public class AddOptionAction extends AbstractAction {
	private static final long serialVersionUID = 1L;
	private QuestionDetailPanel panel;
	
	public AddOptionAction(QuestionDetailPanel questionDetailPanel) {
		super("Add Option");
		this.panel = questionDetailPanel;
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		Question question = panel.getQuestion();
		
		Option newOption = new Option("Typ the new answer here");
		if(question.getCategories() != null && question.getCategories().size() > 0){
			for(Category c : question.getCategories()){
				int max = question.getMaxPointsForCategory(c);
				newOption.addScore(new Score(max,max,c));
			}
		}
		try{
			question.addOption(newOption);
		}catch(DomainException a){
			JOptionPane.showMessageDialog(null, a.getMessage());
		}
		panel.update();
	}


}
