package controller;

import java.awt.event.ActionEvent;
import java.util.List;

import controller.AbstractTestAction;
import view.ViewException;
import view.panels.evaluations.HistoryOverviewPanel;
import domain.Evaluation;
import domain.facade.CompetentieTesterFacade;

public class HistoryOverviewAction extends AbstractTestAction {
	private static final long serialVersionUID = 1L;
	private HistoryOverviewPanel overviewPanel;

	public HistoryOverviewAction(CompetentieTesterFacade service){
		super(service, "Test history");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Listener to get all categories from service...
		List<Evaluation> evaluations = getService().getEvaluationsList();
		getOverviewPanel().setEvaluations(evaluations);
		try {
			getOverviewPanel().update();
		} catch (ViewException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		setPanelAsContentForView(this.getOverviewPanel());
	}

	private HistoryOverviewPanel getOverviewPanel() {
		return overviewPanel;
	}

	public void setOverviewPanel(HistoryOverviewPanel overviewPanel) {
		this.overviewPanel = overviewPanel;
	}
}
