package controller;

import java.awt.event.ActionEvent;

import javax.swing.JPanel;

import view.panels.questions.QuestionOverviewPanel;
import domain.facade.CompetentieTesterFacade;

public class OpenFileAction extends AbstractTestAction {

	private JPanel goToPanel;

	public OpenFileAction(CompetentieTesterFacade service) {
		super(service, "Open");
	}

	private static final long serialVersionUID = 1L;

	@Override
	public void actionPerformed(ActionEvent arg0) {
		this.getService().read();
		setPanelAsContentForView(goToPanel);
	}

	public void setGoTo(JPanel overviewPanel) {
		this.goToPanel = overviewPanel;
	}



}
