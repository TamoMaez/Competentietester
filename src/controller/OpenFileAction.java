package controller;

import java.awt.event.ActionEvent;

import view.panels.questions.QuestionOverviewPanel;
import domain.facade.CompetentieTesterFacade;

public class OpenFileAction extends AbstractTestAction {

	private QuestionOverviewPanel goToPanel;

	public OpenFileAction(CompetentieTesterFacade service) {
		super(service, "Open");
	}

	private static final long serialVersionUID = 1L;

	@Override
	public void actionPerformed(ActionEvent arg0) {
		this.getService().read();
		try {
			goToPanel.setQuestions(getService().getAllQuestions());
			goToPanel.update();
		} catch (Exception e) {
			e.printStackTrace();
		}
		setPanelAsContentForView(goToPanel);
	}

	public void setGoTo(QuestionOverviewPanel overviewPanel) {
		this.goToPanel = overviewPanel;
	}



}
