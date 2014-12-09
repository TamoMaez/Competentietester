package view.panels.evaluations;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import domain.Evaluation;

public class HistoryTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;

	private List<Evaluation> evaluations;
	
	private String[] columnNames = { "Date", "Score" };

	public HistoryTableModel(List<Evaluation> evaluations) {
		this.evaluations = evaluations;
	}

	@Override
	public int getRowCount() {
		return evaluations.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int row, int column) {
		Evaluation evaluation = evaluations.get(row);

		switch (column) {
		case 0:
			return new SimpleDateFormat("dd/MM/YYYY HH:mm:ss").format(evaluation.getDate().getTime());
		case 1:
			return evaluation.getScoreOn(20);
		default:
			return "";
		}
	}
	
	public Evaluation getEvaluationAt(int rowIndex){
		return evaluations.get(rowIndex);

	}

	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}
}
