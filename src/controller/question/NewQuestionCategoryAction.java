package controller.question;

import java.awt.event.ActionEvent;

import controller.AbstractTestAction;
import view.panels.questions.ChooseCategoryPanel;
import view.panels.questions.QuestionDetailPanel;
import domain.facade.CompetentieTesterFacade;

public class NewQuestionCategoryAction extends AbstractTestAction {
	private static final long serialVersionUID = 1L;
	private ChooseCategoryPanel detailPanel;
	private QuestionDetailPanel overviewPanel;

	public NewQuestionCategoryAction(CompetentieTesterFacade service){
		super(service);
	}

	public NewQuestionCategoryAction(CompetentieTesterFacade service, String caption){
		super(service, caption);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Add")){
			getService().addCategoryToQuestion(getOverviewPanel().getQuestion(), getDetailPanel().getCategory(), getDetailPanel().getMaxPoint());
			
			QuestionDetailPanel panel = getOverviewPanel();
			panel.update();
			panel.getCategoryTable().scrollToBottom();
		}
		
		//observer nog toevoegen
		setPanelAsContentForView(getOverviewPanel());
	}

	private QuestionDetailPanel getOverviewPanel() {
		return overviewPanel;
	}

	public void setOverviewPanel(QuestionDetailPanel questionDetailPanel) {
		this.overviewPanel = questionDetailPanel;
	}

	private ChooseCategoryPanel getDetailPanel() {
		return detailPanel;
	}

	public void setDetailPanel(ChooseCategoryPanel chooseCategoryPanel) {
		this.detailPanel = chooseCategoryPanel;
	}
}
