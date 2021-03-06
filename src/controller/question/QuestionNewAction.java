/**
 * 
 * @author Ruben Thielemans, Tamo Maes, Georges Petrofski & Sam Hendrickx
 *
 */
package controller.question;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import controller.AbstractTestAction;
import view.panels.questions.QuestionDetailPanel;
import domain.MCQuestion;
import domain.Option;
import domain.Question;
import domain.YesNoQuestion;
import domain.facade.CompetentieTesterFacade;

public class QuestionNewAction extends AbstractTestAction {

	private static final long serialVersionUID = 1L;
	private QuestionDetailPanel detailPanel;

	public QuestionNewAction(CompetentieTesterFacade service) {
		super(service, "New");
	}

	public void setDetailPanel(QuestionDetailPanel detailPanel) {
		this.detailPanel = detailPanel;
	}
	
	public QuestionDetailPanel getDetailPanel(){
		return detailPanel;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {	
		Question q = getQuestionType();
		getDetailPanel().setQuestion(q);
		if(q instanceof YesNoQuestion){
			q.addOption(new Option("Yes"));
			q.addOption(new Option("No"));
		}else q.addOption(new Option("Option 1"));
		getService().addQuestion(q);
		setPanelAsContentForView(getDetailPanel());	
	}

	private Question getQuestionType() {
		String[] options = new String[] {"Yes/No", "Multiple choice", "Cancel"};
	    int response = JOptionPane.showOptionDialog(null, "Choose your questiontype", "Questiontype",
	        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
	        null, options, options[0]);
	    if(response == 0){
	    	return new YesNoQuestion("New Yes/No question");
	    } 
	    if(response == 1){
	    	return new MCQuestion("New Multiple choice question");
	    }
	    return null;
	}

}
