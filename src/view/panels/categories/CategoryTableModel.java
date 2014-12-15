package view.panels.categories;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import controller.ImageManager;
import domain.Category;

public class CategoryTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;

	private List<Category> categories;
	private String[] columnNames = { "Name", "Description", "" };

	public CategoryTableModel(List<Category> categories) {
		this.categories = categories;
	}

	@Override
	public int getRowCount() {
		return categories.size();
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
		Category category = null;
		category = categories.get(row);

		switch (column) {
		case 0:
			return category.getTitle();
		case 1:
			return category.getDescription();
		case 2:
			return ImageManager.deleteIMG;
		default:
			return "";
		}
	}
	
	public Category getCategoryAt(int rowIndex){
		Category category = null;
		category = categories.get(rowIndex);
		return category;
	}

	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}
}
