package controller;

import java.awt.event.ActionEvent;
import view.AdminMainView;
import domain.facade.CompetentieTesterFacade;

public class AdminModeAction extends AbstractTestAction{
	private AdminMainView mainView;

	public AdminModeAction(CompetentieTesterFacade service) {
		super(service, "ADMINMODE");
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = 1L;

	@Override
	public void actionPerformed(ActionEvent e) {
		this.getView().setVisible(false);
		this.mainView.setVisible(true);
	}

	public void setOverviewPanel(AdminMainView mainView) {
		this.mainView = mainView;
	}
}
