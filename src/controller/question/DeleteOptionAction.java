/**
 * 
 * @author Ruben Thielemans, Tamo Maes, Georges Petrofski & Sam Hendrickx
 *
 */
package controller.question;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import controller.DeleteAction;
import view.panels.options.OptionTableModel;
import view.panels.questions.QuestionDetailPanel;
import domain.Option;

public class DeleteOptionAction extends DeleteAction {

	public DeleteOptionAction(QuestionDetailPanel questionDetailPanel) {
		super();
		this.setPanel(questionDetailPanel);
	}

	@Override
	protected void deleteRow(JTable table, int row) {
		OptionTableModel tablem = (OptionTableModel) (table.getModel());
		Option o = (Option) tablem.getOptionAt(row);
		if(areYouSure(o))
			getPanel().getQuestion().removeOption(o);
		getPanel().update();
	}
	
	protected boolean areYouSure(Option o) {
		int reply = JOptionPane.showConfirmDialog(null, "Delete " + o.getStatement() + "?" , "Are you sure", JOptionPane.YES_NO_OPTION);
        return reply == JOptionPane.YES_OPTION;
	}

	@Override
	protected QuestionDetailPanel getPanel(){
		return (QuestionDetailPanel) super.getPanel();
	}
}
