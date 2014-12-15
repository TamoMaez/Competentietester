package controller.category;

import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JTable;

import controller.AbstractTestMouseAdapter;
import view.ViewException;
import view.panels.categories.CategoryDetailPanel;
import view.panels.categories.CategoryOverviewPanel;
import view.panels.categories.CategoryTableModel;
import domain.Category;
import domain.facade.CompetentieTesterFacade;

public class CategoryEditAction extends AbstractTestMouseAdapter {
	private CategoryDetailPanel detailPanel;
	private CategoryOverviewPanel panel;
	
	public CategoryEditAction(CompetentieTesterFacade service){
		super(service);
	}
	
	public void mouseClicked(MouseEvent evt) {
		
		JTable table = (JTable)(evt.getSource());
		CategoryTableModel tablem = (CategoryTableModel)(table.getModel());
		Category clickedCategory = (Category) tablem.getCategoryAt(table.getSelectedRow());
		getDetailPanel().setCategory(clickedCategory);
		
		if(table.getSelectedColumn() == table.getColumnCount()-1){
			System.out.println("Category removed:" + getService().removeCategory(clickedCategory));
			try {
				panel.setCategories(getService().getCategories());
				panel.update();
			} catch (ViewException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{		
			List<Category> categories = getService().getCategories();
			getDetailPanel().setCategories(categories);
			setPanelAsContentForView(getDetailPanel());		
		}
	}

	private CategoryDetailPanel getDetailPanel() {
		return detailPanel;
	}

	public void setDetailPanel(CategoryDetailPanel detailPanel) {
		this.detailPanel = detailPanel;
	}
	
	public void setPanel(CategoryOverviewPanel panel){
		this.panel = panel;
	}

}