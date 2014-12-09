package view.panels.questions;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import domain.Category;
import domain.Question;

public class QuestionTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;

	private List<Question> questions;
	private String[] columnNames = { "Question", "Answers", "Correct answer", "Categories" };

	public QuestionTableModel(List<Question> questions) {
		this.questions = questions;
	}

	@Override
	public int getRowCount() {
		return questions.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int row, int column) {
		Question q = null;
		q = questions.get(row);

		switch (column) {
		case 0:
			return q.getQuestion();
		case 1:
			return q.getStatements();
		case 2:
			return q.getCorrectStatements();
		case 3:
			return q.getCategories();
		default:
			return "";
		}
	}
	
	public Question getQuestionAt(int rowIndex){
		Question q = null;
		q = questions.get(rowIndex);
		return q;
	}

	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}

}
