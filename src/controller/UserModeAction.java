package controller;

import java.awt.event.ActionEvent;
import java.util.List;

import view.ViewException;
import view.panels.CategoryOverviewPanel;
import domain.Category;
import domain.facade.AdministratorFacade;

public class UserModeAction extends AbstractTestAction {
	private static final long serialVersionUID = -1944003041085390843L;
	private CategoryOverviewPanel overviewPanel;
	
	public UserModeAction(AdministratorFacade service) {
		super(service, "USERMODE");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Listener to get all categories from service...
		List<Category> categories = getService().getCategories();
		getOverviewPanel().setCategories(categories);
		try {
			getOverviewPanel().update();
		} catch (ViewException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		setPanelAsContentForView(getOverviewPanel());
	}

	private CategoryOverviewPanel getOverviewPanel() {
		return overviewPanel;
	}

	public void setOverviewPanel(CategoryOverviewPanel overviewPanel) {
		this.overviewPanel = overviewPanel;
	}

}
