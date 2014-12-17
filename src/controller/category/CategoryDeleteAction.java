/**
 * 
 * @author Ruben Thielemans, Tamo Maes, Georges Petrofski & Sam Hendrickx
 *
 */
package controller.category;

import javax.swing.JTable;

import view.ViewException;
import view.panels.categories.CategoryOverviewPanel;
import view.panels.categories.CategoryTableModel;
import controller.DeleteAction;
import domain.Category;
import domain.facade.CompetentieTesterFacade;

public class CategoryDeleteAction extends DeleteAction {
	private CompetentieTesterFacade service;
	private CategoryOverviewPanel panel;

	public CategoryDeleteAction(CompetentieTesterFacade service) {
		super();
		this.service = service;
	}

	private CompetentieTesterFacade getService() {
		return service;
	}

	@Override
	protected void deleteRow(JTable table, int row) {
		CategoryTableModel tablem = (CategoryTableModel) table.getModel();
		Category c = (Category) tablem.getCategoryAt(row);
		getService().removeCategory(c);
		try {
			getPanel().update();
		} catch (ViewException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setPanel(CategoryOverviewPanel panel){
		this.panel = panel;
	}
	
	protected CategoryOverviewPanel getPanel(){
		return panel;
	}
	
}
