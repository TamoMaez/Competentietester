package controller.settings;

import java.awt.event.ActionEvent;
import java.util.List;

import controller.AbstractTestAction;
import view.ViewException;
import view.panels.categories.CategoryOverviewPanel;
import view.panels.questions.QuestionOverviewPanel;
import domain.Category;
import domain.Question;
import domain.facade.CompetentieTesterFacade;

public class SettingsOverviewAction extends AbstractTestAction {

	private static final long serialVersionUID = 1L;
	private QuestionOverviewPanel overviewPanel;

	public SettingsOverviewAction(CompetentieTesterFacade service) {
		super(service, "Edit settings");
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
