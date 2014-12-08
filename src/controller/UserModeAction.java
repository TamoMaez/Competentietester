package controller;

import java.awt.event.ActionEvent;


import view.UserMainView;
import domain.facade.CompetentieTesterFacade;

public class UserModeAction extends AbstractTestAction{
	private UserMainView mainView;

	public UserModeAction(CompetentieTesterFacade service) {
		super(service, "USERMODE");
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = 1L;

	@Override
	public void actionPerformed(ActionEvent e) {
		this.getView().setVisible(false);
		this.mainView.setVisible(true);
	}

	public void setOverviewPanel(UserMainView mainView) {
		this.mainView = mainView;
	}
}
