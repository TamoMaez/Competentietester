package controller;

import java.awt.event.ActionEvent;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import view.ViewException;
import view.panels.categories.CategoryDetailPanel;
import view.panels.WelcomePanel;
import domain.Category;
import domain.facade.CompetentieTesterFacade;

public class UserTypeDoneAction extends AbstractTestAction {
	private static final long serialVersionUID = 1L;
	private CategoryDetailPanel detailPanel;
	private WelcomePanel welcomePanel;

	public UserTypeDoneAction(CompetentieTesterFacade service){
		super(service);
	}

	public UserTypeDoneAction(CompetentieTesterFacade service, String caption){
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
			
			getService().write();
		}
		
		List<Category> categories = getService().getCategories();
		setPanelAsContentForView(getWelcomePanel());		
	}

	private WelcomePanel getWelcomePanel() {
		return welcomePanel;
	}

	public void setWelcomePanel(WelcomePanel welcomePanel) {
		this.welcomePanel = welcomePanel;
	}

	private CategoryDetailPanel getDetailPanel() {
		return detailPanel;
	}

	public void setDetailPanel(CategoryDetailPanel detailPanel) {
		this.detailPanel = detailPanel;
	}
}
