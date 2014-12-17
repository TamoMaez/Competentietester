/**
 * 
 * @author Ruben Thielemans, Tamo Maes, Georges Petrofski & Sam Hendrickx
 *
 */
package view.panels.options;

import java.io.IOException;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import controller.ImageManager;
import domain.Option;
import domain.Score;

public class OptionTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;

	private List<Option> options;
	private String[] columnNames = {"Option", "Correct", ""};

	public OptionTableModel(List<Option> options) throws IOException {
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
	public Class<?> getColumnClass(int column) {
	    return (getValueAt(0, column).getClass());
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
		case 2:
			return ImageManager.deleteIMG;
		default:
			return "Delete";
		}
	}
	
	public Option getOptionAt(int rowIndex){
		return options.get(rowIndex);
	}
	
	@Override
	public boolean isCellEditable(int row, int column) {
		return column != getColumnCount()-1;
	}

	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}
	
	@Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if(columnIndex == 0) options.get(rowIndex).setStatement((String) aValue); 
		else if(columnIndex == 1){
			if(options.get(rowIndex).isCorrect()){
				if(hasMoreThanOneCorrectOption())
				for(Score score : options.get(rowIndex).getScores()){
					score.setPoints(0);
				}
			}else {
				for(Score score : options.get(rowIndex).getScores()){
					score.setPoints(score.getMaxPoints());
				}
			}
		}
    }
	
	private boolean hasMoreThanOneCorrectOption(){
		boolean hasOne = false;
		for(Option o : options)
			if(o.isCorrect()){
				if(hasOne) return true;
				else hasOne = true;
			}
		return false;
	}
}
