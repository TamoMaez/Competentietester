package view.panels.questions;

import javax.swing.table.AbstractTableModel;

import domain.Category;
import domain.Option;
import domain.Question;

public class QuestionCategoryTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;

	private Question question;
	private String[] columnNames = { "Title", "Points" , ""};

	public QuestionCategoryTableModel(Question question) {
		this.question = question;
	}

	@Override
	public int getRowCount() {
		return question.getCategories().size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int row, int column) {
		Category c = null;
		c = question.getCategories().get(row);

		switch (column) {
		case 0:
			return c.getTitle();
		case 1:
			return question.getMaxPointsForCategory(c);
		default:
			return "Delete";
		}
	}
	
	public Category getCategoryAt(int rowIndex){
		return question.getCategories().get(rowIndex);
	}
	
	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}
	
	@Override
	public boolean isCellEditable(int row, int column) {
	    return column != getColumnCount()-1;
	}
	
	@Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {	 
		if(columnIndex == 1){
			aValue = Integer.parseInt(aValue.toString());
			for(Option o : question.getOptions()){
				o.getScoreByCategory(question.getCategories().get(rowIndex)).setMaxPoints((int) aValue);
				if(o.isCorrect())
					o.getScoreByCategory(question.getCategories().get(rowIndex)).setPoints((int) aValue);
			}
		}
    }
}
