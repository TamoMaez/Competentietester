
package controller;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import view.UserMainView;
import view.WelcomeView;
import view.panels.UserWelcomePanel;
import domain.facade.CompetentieTesterFacade;

public class LogoutAction extends AbstractTestAction{
	private WelcomeView welcomeView;
	private List<JFrame> views = new ArrayList<JFrame>();

	public LogoutAction(CompetentieTesterFacade service) {
		super(service, "Logout");
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = 1L;


	@Override
	public void actionPerformed(ActionEvent e) {
		for (JFrame view : this.views) {
			view.setVisible(false);
		}
		
		this.welcomeView.setVisible(true);
		
	}

	public void setOverviewPanel(WelcomeView welcomeView) {
		this.welcomeView = welcomeView;
	}
	
	public void addView(JFrame view) {
		this.views.add(view);
	}
}
