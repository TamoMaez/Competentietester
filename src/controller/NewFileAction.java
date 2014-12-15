package controller;

import java.awt.event.ActionEvent;

import view.panels.questions.QuestionOverviewPanel;
import domain.facade.CompetentieTesterFacade;

public class NewFileAction extends AbstractTestAction {

	private static final long serialVersionUID = 1L;

	public NewFileAction(CompetentieTesterFacade service) {
		super(service, "New");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		getService().createNewQuiz();
		
	}

	public void setGoTo(QuestionOverviewPanel questionOverviewPanel) {
		
	}

}
