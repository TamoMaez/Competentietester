package controller.category;

import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JTable;

import controller.AbstractTestMouseAdapter;
import view.panels.CategoryDetailPanel;
import view.panels.CategoryTableModel;
import domain.Category;
import domain.facade.AdministratorFacade;

public class CategoryEditAction extends AbstractTestMouseAdapter {
	private CategoryDetailPanel detailPanel;
	
	public CategoryEditAction(AdministratorFacade service){
		super(service);
	}
	
	public void mouseClicked(MouseEvent evt) {
		
		JTable table = (JTable)(evt.getSource());
		CategoryTableModel tablem = (CategoryTableModel)(table.getModel());
		Category clickedCategory = (Category) tablem.getCategoryAt(table.getSelectedRow());
		getDetailPanel().setCategory(clickedCategory);
		
		List<Category> categories = getService().getCategories();
		getDetailPanel().setCategories(categories);
		setPanelAsContentForView(getDetailPanel());		
	}

	private CategoryDetailPanel getDetailPanel() {
		return detailPanel;
	}

	public void setDetailPanel(CategoryDetailPanel detailPanel) {
		this.detailPanel = detailPanel;
	}

}