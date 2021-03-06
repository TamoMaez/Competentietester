/**
 * 
 * @author Ruben Thielemans, Tamo Maes, Georges Petrofski & Sam Hendrickx
 *
 */
package controller.category;

import java.awt.event.ActionEvent;
import java.util.List;

import controller.AbstractTestAction;
import view.panels.categories.CategoryDetailPanel;
import domain.Category;
import domain.facade.CompetentieTesterFacade;

public class CategoryNewAction extends AbstractTestAction {
	private static final long serialVersionUID = 1L;
	private CategoryDetailPanel detailPanel;

	public CategoryNewAction(CompetentieTesterFacade service){
		super(service, "New");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		getDetailPanel().setCategory(new Category());
		
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
