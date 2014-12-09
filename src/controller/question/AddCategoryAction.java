package controller.question;

import java.awt.event.ActionEvent;

import view.AdminMainView;
import view.panels.questions.ChooseCategoryPanel;
import controller.AbstractTestAction;
import domain.facade.CompetentieTesterFacade;

public class AddCategoryAction extends AbstractTestAction {
	private static final long serialVersionUID = 1L;
	
	private ChooseCategoryPanel panel;
	
	public AddCategoryAction(CompetentieTesterFacade service) {
		super(service, "Add Category");
	}
	
	private ChooseCategoryPanel getDetailPanel() {
		return panel;
	}

	public void setDetailPanel(ChooseCategoryPanel chooseCategoryPanel) {
		this.panel = chooseCategoryPanel;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.println(getDetailPanel());
		getDetailPanel().setCategories(getService().getCategories());
		setPanelAsContentForView(getDetailPanel());		
	}

}
