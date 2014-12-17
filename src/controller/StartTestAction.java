/**
 * 
 * @author Ruben Thielemans, Tamo Maes, Georges Petrofski & Sam Hendrickx
 *
 */
package controller;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import controller.AbstractTestAction;
import view.ViewException;
import view.panels.TestPanel;
import domain.Question;
import domain.facade.CompetentieTesterFacade;

public class StartTestAction extends AbstractTestAction {
	private static final long serialVersionUID = 1L;
	private TestPanel testPanel;

	public StartTestAction(CompetentieTesterFacade service){
		super(service, "Start Test");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try{
			this.getService().start();
			// Listener to get all categories from service...
			Question question = getService().getNextQuestion();
			getTestPanel().setQuestion(question);
			try {
				getTestPanel().update();
			} catch (ViewException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			setPanelAsContentForView(getTestPanel());
		}catch(Exception e2){
			JOptionPane.showMessageDialog(null, "Login to administrator and select a file with questions or create a new quiz");
		}
	}

	private TestPanel getTestPanel() {
		return testPanel;
	}

	public void setTestPanel(TestPanel testPanel) {
		this.testPanel = testPanel;
	}
}
