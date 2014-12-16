package controller.question;

import java.awt.event.ActionEvent;

import view.ViewException;
import view.panels.questions.QuestionOverviewPanel;
import controller.AbstractTestAction;
import domain.facade.CompetentieTesterFacade;

public class QuestionOverviewAction extends AbstractTestAction {

	private static final long serialVersionUID = 1L;
	private QuestionOverviewPanel overviewPanel;

	public QuestionOverviewAction(CompetentieTesterFacade service) {
		super(service, "Questions");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Listener to get all categories from service...
		getOverviewPanel().setQuestions(getService().getAllQuestions());
		try {
			getOverviewPanel().update();
		} catch (ViewException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		setPanelAsContentForView(getOverviewPanel());
	}

	private QuestionOverviewPanel getOverviewPanel() {
		return overviewPanel;
	}

	public void setOverviewPanel(QuestionOverviewPanel overviewPanel) {
		this.overviewPanel = overviewPanel;
	}
}
