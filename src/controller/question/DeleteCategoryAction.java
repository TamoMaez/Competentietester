package controller.question;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import controller.DeleteAction;
import view.panels.questions.QuestionCategoryTableModel;
import view.panels.questions.QuestionDetailPanel;
import domain.Category;

public class DeleteCategoryAction extends DeleteAction {

	public DeleteCategoryAction(QuestionDetailPanel questionDetailPanel) {
		super();
		this.setPanel(questionDetailPanel);
	}

	@Override
	protected void deleteRow(JTable table, int row) {
		QuestionCategoryTableModel tablem = (QuestionCategoryTableModel) (table.getModel());
  	  	Category c = (Category) tablem.getCategoryAt(row);
  	  	if(areYouSure(c))
  	  		getPanel().getQuestion().removeCategory(c);
  	  	getPanel().update();
	}
	
	protected boolean areYouSure(Category c) {
		int reply = JOptionPane.showConfirmDialog(null, "Delete " + c.getTitle() + "?" , "Are you sure", JOptionPane.YES_NO_OPTION);
        return reply == JOptionPane.YES_OPTION;
	}
	
	@Override
	protected QuestionDetailPanel getPanel(){
		return (QuestionDetailPanel) super.getPanel();
	}

}
