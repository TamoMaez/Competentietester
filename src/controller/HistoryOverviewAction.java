package controller;

import java.awt.event.ActionEvent;
import java.util.List;

import controller.AbstractTestAction;
import view.ViewException;
import view.panels.categories.CategoryOverviewPanel;
import domain.Category;
import domain.facade.CompetentieTesterFacade;

public class HistoryOverviewAction extends AbstractTestAction {
	private static final long serialVersionUID = 1L;
	private CategoryOverviewPanel overviewPanel;

	public HistoryOverviewAction(CompetentieTesterFacade service){
		super(service, "Test history");
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
