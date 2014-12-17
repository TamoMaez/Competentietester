/**
 * 
 * @author Ruben Thielemans, Tamo Maes, Georges Petrofski & Sam Hendrickx
 *
 */

package controller;

import java.awt.event.ActionEvent;

import view.UserMainView;
import view.panels.UserWelcomePanel;
import domain.facade.CompetentieTesterFacade;

public class BackToUserHomeAction extends AbstractTestAction{
	private UserMainView mainView;
	private UserWelcomePanel userWelcomePanel;

	public BackToUserHomeAction(CompetentieTesterFacade service) {
		super(service, "Back to home");
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = 1L;

	@Override
	public void actionPerformed(ActionEvent e) {
		setPanelAsContentForView(this.userWelcomePanel);
	}

	public void setOverviewPanel(UserMainView mainView) {
		this.setMainView(mainView);
	}
	
	public void setUserWelcomePanel(UserWelcomePanel userWelcomePanel) {
		this.userWelcomePanel = userWelcomePanel;
	}

	public UserMainView getMainView() {
		return mainView;
	}

	private void setMainView(UserMainView mainView) {
		this.mainView = mainView;
	}
	
}
