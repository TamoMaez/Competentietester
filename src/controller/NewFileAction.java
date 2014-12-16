package controller;

import java.awt.event.ActionEvent;
import view.panels.questions.QuestionOverviewPanel;
import domain.facade.CompetentieTesterFacade;

public class NewFileAction extends AbstractTestAction {

	private static final long serialVersionUID = 1L;
	private QuestionOverviewPanel panel;

	public NewFileAction(CompetentieTesterFacade service) {
		super(service, "New");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		getService().createNewQuiz();	
		setPanelAsContentForView(panel);
	}

	public void setGoTo(QuestionOverviewPanel questionOverviewPanel) {
		this.panel = questionOverviewPanel;
	}

}
