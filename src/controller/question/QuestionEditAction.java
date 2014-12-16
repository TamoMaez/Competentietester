package controller.question;

import java.awt.event.MouseEvent;

import javax.swing.JTable;

import controller.AbstractTestMouseAdapter;
import view.ViewException;
import view.panels.questions.QuestionDetailPanel;
import view.panels.questions.QuestionOverviewPanel;
import view.panels.questions.QuestionTableModel;
import domain.Question;
import domain.facade.CompetentieTesterFacade;

public class QuestionEditAction extends AbstractTestMouseAdapter {

	private QuestionDetailPanel detailPanel;
	private QuestionOverviewPanel panel;

	public QuestionEditAction(CompetentieTesterFacade service) {
		super(service);
	}
	
	public void mouseClicked(MouseEvent evt) {
		JTable table = (JTable)(evt.getSource());
		QuestionTableModel tablem = (QuestionTableModel)(table.getModel());
		Question clickedQuestion = (Question) tablem.getQuestionAt(table.getSelectedRow());
		
		if(table.getSelectedColumn() == table.getColumnCount()-1){
			System.out.println("Question removed:" + getService().removeQuestion(clickedQuestion));
			try {
				panel.update();
			} catch (ViewException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{		
			getDetailPanel().setQuestion(clickedQuestion);		
			setPanelAsContentForView(getDetailPanel());	
		}
	}

	private QuestionDetailPanel getDetailPanel() {
		return detailPanel;
	}

	public void setDetailPanel(QuestionDetailPanel questionDetailPanel) {
		this.detailPanel = questionDetailPanel;
	}

	public void setPanel(QuestionOverviewPanel panel){
		this.panel = panel;
	}
}
