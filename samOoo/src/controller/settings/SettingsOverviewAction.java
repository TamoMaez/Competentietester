package controller.settings;

import java.awt.event.ActionEvent;

import controller.AbstractTestAction;
import view.panels.SettingsOverviewPanel;
import domain.facade.CompetentieTesterFacade;

public class SettingsOverviewAction extends AbstractTestAction {

	private static final long serialVersionUID = 1L;
	private SettingsOverviewPanel overviewPanel;

	public SettingsOverviewAction(CompetentieTesterFacade service) {
		super(service, "Edit settings");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Listener to get all categories from service...
		getOverviewPanel().setSettingsReady();
		setPanelAsContentForView(getOverviewPanel());
	}

	private SettingsOverviewPanel getOverviewPanel() {
		return overviewPanel;
	}

	public void setOverviewPanel(SettingsOverviewPanel overviewPanel) {
		this.overviewPanel = overviewPanel;
	}
}
