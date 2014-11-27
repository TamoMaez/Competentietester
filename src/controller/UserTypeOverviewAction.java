package controller;

import java.awt.event.ActionEvent;
import java.util.List;

import view.ViewException;
import view.panels.CategoryOverviewPanel;
import view.panels.WelcomePanel;
import domain.Category;
import domain.facade.AdministratorFacade;

public class UserTypeOverviewAction extends AbstractTestAction {
	private static final long serialVersionUID = 1L;
	private WelcomePanel welcomePanel;

	public UserTypeOverviewAction(AdministratorFacade service){
		super(service);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Listener to get all categories from service...
		List<Category> categories = getService().getCategories();
		getOverviewPanel().setCategories(categories);
		try {
			getWelcomePanel().update();
		} catch (ViewException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		setPanelAsContentForView(getOverviewPanel());
	}

	private WelcomePanel getWelcomePanel() {
		return welcomePanel;
	}

	public void setWelcomePanel(WelcomePanel welcomePanel) {
		this.welcomePanel = welcomePanel;
	}
}
