package controller;

import java.awt.event.ActionEvent;
import java.util.List;

import view.ViewException;
import view.panels.CategoryOverviewPanel;
import view.panels.QuestionOverviewPanel;
import domain.Category;
import domain.Question;
import domain.facade.AdministratorFacade;

public class QuestionOverviewAction extends AbstractTestAction {

	private static final long serialVersionUID = 1L;
	private QuestionOverviewPanel overviewPanel;

	public QuestionOverviewAction(AdministratorFacade service) {
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
