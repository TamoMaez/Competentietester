package controller;

import java.awt.event.ActionEvent;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import view.ViewException;
import view.panels.CategoryDetailPanel;
import view.panels.CategoryOverviewPanel;
import domain.Category;
import domain.facade.AdministratorFacade;

public class CategoryDoneAction extends AbstractTestAction {
	private static final long serialVersionUID = 1L;
	private CategoryDetailPanel detailPanel;
	private CategoryOverviewPanel overviewPanel;

	public CategoryDoneAction(AdministratorFacade service){
		super(service);
	}

	public CategoryDoneAction(AdministratorFacade service, String caption){
		super(service, caption);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Save")){
			
			Iterator<Entry<String, Category>> it = getService().getCategoriesMap().entrySet().iterator();
		    while (it.hasNext()) {
		        Map.Entry pairs = (Map.Entry)it.next();
		        System.out.println(pairs.getKey() + " = " + pairs.getValue());
		        if (pairs.getValue().equals(getDetailPanel().getCreatedCategory())) {
		        	it.remove(); // avoids a ConcurrentModificationException
		        }
		    }
			
			getService().addCategory(getDetailPanel().getCreatedCategory());
			try {
				getOverviewPanel().update();
			} catch (ViewException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			getService().write("test.xlsx");
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
