package controller.question;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import view.panels.questions.ChooseCategoryPanel;
import view.panels.questions.QuestionDetailPanel;
import controller.AbstractTestAction;
import domain.Category;
import domain.facade.CompetentieTesterFacade;

public class AddCategoryAction extends AbstractTestAction {
	private static final long serialVersionUID = 1L;
	
	private ChooseCategoryPanel panel;

	private QuestionDetailPanel overviewPanel;
	
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
	public void actionPerformed(ActionEvent e) {
		List<Category> catNotInQ = new ArrayList<Category>();
		if(getService().getCategories() != null){
			catNotInQ.addAll(getService().getCategories());
		}
		if(getOverviewPanel().getQuestion().getCategories() != null){
			catNotInQ.removeAll(getOverviewPanel().getQuestion().getCategories());
		}
		getDetailPanel().setCategories(catNotInQ);
		setPanelAsContentForView(getDetailPanel());		
	}

	private QuestionDetailPanel getOverviewPanel(){
		return overviewPanel;
	}
	
	public void setOverviewPanel(QuestionDetailPanel questionDetailPanel) {
		this.overviewPanel = questionDetailPanel;
	}

}
