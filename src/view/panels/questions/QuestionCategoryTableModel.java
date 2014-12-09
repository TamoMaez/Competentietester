package view.panels.questions;

import javax.swing.table.AbstractTableModel;

import domain.Category;
import domain.Question;

public class QuestionCategoryTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;

	private Question question;
	private String[] columnNames = { "Title", "Points" };

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
			return "";
		}
	}
	
	public Category getQuestionAt(int rowIndex){
		return question.getCategories().get(rowIndex);
	}
	
	@Override
	public String getColumnName(int column) {
		System.out.println("test " + column + " " + columnNames[column]);
		return columnNames[column];
	}
}
