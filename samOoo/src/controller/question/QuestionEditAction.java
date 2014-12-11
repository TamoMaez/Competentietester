package controller.question;

import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JTable;

import controller.AbstractTestMouseAdapter;
import view.panels.questions.QuestionDetailPanel;
import view.panels.questions.QuestionTableModel;
import domain.Category;
import domain.Option;
import domain.Question;
import domain.facade.CompetentieTesterFacade;

public class QuestionEditAction extends AbstractTestMouseAdapter {

	private QuestionDetailPanel detailPanel;

	public QuestionEditAction(CompetentieTesterFacade service) {
		super(service);
	}
	
	public void mouseClicked(MouseEvent evt) {
		
		JTable table = (JTable)(evt.getSource());
		QuestionTableModel tablem = (QuestionTableModel)(table.getModel());
		Question clickedQuestion = (Question) tablem.getQuestionAt(table.getSelectedRow());
		getDetailPanel().setQuestion(clickedQuestion);
		
		setPanelAsContentForView(getDetailPanel());		
	}

	private QuestionDetailPanel getDetailPanel() {
		return detailPanel;
	}

	public void setDetailPanel(QuestionDetailPanel questionDetailPanel) {
		this.detailPanel = questionDetailPanel;
	}

}
