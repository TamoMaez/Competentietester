package controller.category;

import java.awt.event.ActionEvent;
import java.util.List;

import controller.AbstractTestAction;
import view.ViewException;
import view.panels.categories.CategoryDetailPanel;
import view.panels.categories.CategoryOverviewPanel;
import domain.Category;
import domain.facade.CompetentieTesterFacade;

public class CategoryDoneAction extends AbstractTestAction {
	private static final long serialVersionUID = 1L;
	private CategoryDetailPanel detailPanel;
	private CategoryOverviewPanel overviewPanel;

	public CategoryDoneAction(CompetentieTesterFacade service){
		super(service);
	}

	public CategoryDoneAction(CompetentieTesterFacade service, String caption){
		super(service, caption);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Save")){
			
			String initialCategoryTitle = getDetailPanel().getInitialCategoryTitle();
			if(initialCategoryTitle != null && getService().containsCategory(initialCategoryTitle)){
				getService().getCategoriesMap().remove(initialCategoryTitle);
			}
			
			getService().addCategory(getDetailPanel().getCreatedCategory());
			try {
				getOverviewPanel().update();
			} catch (ViewException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			getService().writeToCurrentFile();;
		}
		
		List<Category> categories = getService().getCategories();
		getOverviewPanel().setCategories(categories);
		setPanelAsContentForView(getOverviewPanel());		
	}

	private CategoryOverviewPanel getOverviewPanel() {
		return overviewPanel;
	}

	public void setOverviewPanel(CategoryOverviewPanel overviewPanel) {
		this.overviewPanel = overviewPanel;
	}

	private CategoryDetailPanel getDetailPanel() {
		return detailPanel;
	}

	public void setDetailPanel(CategoryDetailPanel detailPanel) {
		this.detailPanel = detailPanel;
	}
}
