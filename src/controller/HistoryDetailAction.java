/**
 * 
 * @author Ruben Thielemans, Tamo Maes, Georges Petrofski & Sam Hendrickx
 *
 */
package controller;

import java.awt.event.MouseEvent;

import javax.swing.JTable;

import view.panels.EvaluationTestPanel;
import view.panels.evaluations.HistoryTableModel;
import domain.Evaluation;
import domain.facade.CompetentieTesterFacade;

public class HistoryDetailAction extends AbstractTestMouseAdapter {
	private EvaluationTestPanel evaluationTestPanel;
	
	public HistoryDetailAction(CompetentieTesterFacade service){
		super(service);
	}

	@Override
	
	public void mouseClicked(MouseEvent evt) {
		
		JTable table = (JTable)(evt.getSource());
		HistoryTableModel tablem = (HistoryTableModel)(table.getModel());
		Evaluation clickedEvaluation = (Evaluation) tablem.getEvaluationAt(table.getSelectedRow());
		this.getEvaluationTestPanel().setEvaluation(clickedEvaluation);
		
		this.getEvaluationTestPanel().generatePanel();
		setPanelAsContentForView(this.getEvaluationTestPanel());
			
	}
	
	
	private EvaluationTestPanel getEvaluationTestPanel() {
		return evaluationTestPanel;
	}

	public void setEvaluationTestPanel(EvaluationTestPanel evaluationTestPanel) {
		this.evaluationTestPanel = evaluationTestPanel;
	}
	
}
