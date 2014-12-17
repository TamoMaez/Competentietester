/**
 * 
 * @author Ruben Thielemans, Tamo Maes, Georges Petrofski & Sam Hendrickx
 *
 */
package controller.question;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import view.panels.questions.QuestionDetailPanel;

public class QuestionStatementAction extends AbstractAction {
	private static final long serialVersionUID = 1L;
	
	private QuestionDetailPanel panel;

	public QuestionStatementAction(QuestionDetailPanel questionDetailPanel, String title) {
		super(title);
		this.panel = questionDetailPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Save title")){
			try{
				panel.getQuestion().setQuestion(panel.getTitleField().getText());
			}catch(Exception e1){
				JOptionPane.showMessageDialog(null, "Save a descent questiontitle");
			}
		}
		panel.update();
	}

}
