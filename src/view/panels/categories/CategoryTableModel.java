package view.panels.categories;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;

import domain.Category;

public class CategoryTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;

	private List<Category> categories;
	private String[] columnNames = { "Name", "Description", "" };

	private ImageIcon deleteIcon;

	public CategoryTableModel(List<Category> categories) {
		this.categories = categories;
		Image img = null;
		try {
			img = ImageIO.read(new File("res/delete.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Image resizedImage = img.getScaledInstance(10, 10, 0);
		deleteIcon = new ImageIcon(resizedImage);
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
			return deleteIcon;
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
