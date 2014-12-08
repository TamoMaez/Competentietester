package controller;

import java.awt.event.ActionEvent;

<<<<<<< HEAD
import view.AdminMainView;
import domain.facade.CompetentieTesterFacade;
=======
import view.MainView;
import domain.facade.AdministratorFacade;
>>>>>>> fd6e2e10599957ccde5a3900cd9488791b3eceb7

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
