package controller.question;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JTable;

import controller.AbstractTestAction;
import view.ViewException;
import view.panels.categories.CategoryTableModel;
import view.panels.questions.QuestionDetailPanel;
import view.panels.questions.QuestionOverviewPanel;
import domain.Category;
import domain.facade.CompetentieTesterFacade;

public class QuestionDoneAction extends AbstractTestAction {

	private static final long serialVersionUID = 1L;
	private QuestionDetailPanel detailPanel;
	private QuestionOverviewPanel overviewPanel;

	public QuestionDoneAction(CompetentieTesterFacade service) {
		super(service);
	}

	public void setDetailPanel(QuestionDetailPanel questionDetailPanel) {
		this.detailPanel = questionDetailPanel;

	}

	public void setOverviewPanel(QuestionOverviewPanel questionOverviewPanel) {
		this.overviewPanel = questionOverviewPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Save")){

			getDetailPanel().setQuestionTitle();;
			try {
				getOverviewPanel().update();
			} catch (ViewException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			getService().writeToCurrentFile();;
		}
		setPanelAsContentForView(getOverviewPanel());	
	}

	private QuestionOverviewPanel getOverviewPanel() {
		return overviewPanel;
	}

	private QuestionDetailPanel getDetailPanel() {
		return detailPanel;
	}

}
