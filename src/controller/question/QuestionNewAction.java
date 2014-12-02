package controller.question;

import java.awt.event.ActionEvent;
import java.util.List;

import controller.AbstractTestAction;
import view.panels.QuestionDetailPanel;
import domain.Question;
import domain.facade.AdministratorFacade;

public class QuestionNewAction extends AbstractTestAction {

	private static final long serialVersionUID = 1L;
	private QuestionDetailPanel detailPanel;

	public QuestionNewAction(AdministratorFacade service) {
		super(service, "New");
	}

	public void setDetailPanel(QuestionDetailPanel detailPanel) {
		this.detailPanel = detailPanel;
	}
	
	public QuestionDetailPanel getDetailPanel(){
		return detailPanel;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {		
		setPanelAsContentForView(getDetailPanel());	
	}

}
