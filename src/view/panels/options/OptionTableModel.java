package view.panels.options;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import domain.Option;

public class OptionTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;

	private List<Option> options;
	private String[] columnNames = {"Option", "Correct"};

	public OptionTableModel(List<Option> options) {
		this.options = options;
	}

	@Override
	public int getRowCount() {
		return options.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int row, int column) {
		Option option = null;
		option = options.get(row);

		switch (column) {
		case 0:
			return option.getStatement();
		case 1:
			return option.isCorrect();
		default:
			return "";
		}
	}
	
	public Option getOptionAt(int rowIndex){
		return options.get(rowIndex);
	}

	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}
}
