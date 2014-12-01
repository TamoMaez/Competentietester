package controller;

import java.awt.event.ActionEvent;

import application.CompetentieTesterApp;
import view.MainView;
import domain.facade.AdministratorFacade;

public class AdminModeAction extends AbstractTestAction{
	private MainView mainView;

	public AdminModeAction(AdministratorFacade service) {
		super(service, "ADMINMODE");
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = 1L;

	@Override
	public void actionPerformed(ActionEvent e) {
		this.getView().setVisible(false);
		this.getService().read();
		this.mainView.setVisible(true);
	}

	public void setOverviewPanel(MainView mainView) {
		this.mainView = mainView;
	}
}
