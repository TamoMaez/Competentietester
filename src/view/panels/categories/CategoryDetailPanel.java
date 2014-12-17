/**
 * 
 * @author Ruben Thielemans, Tamo Maes, Georges Petrofski & Sam Hendrickx
 *
 */
package view.panels.categories;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;
import java.util.Vector;

import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import domain.Category;


public class CategoryDetailPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private GridBagConstraints constraints = new GridBagConstraints();
	private JButton btnOK, btnCancel;
	private JTextField titleField, descriptionField; 
	private JComboBox<Category> categoryField;
	private Category category;
	private List<Category> categories;
	private String initialCategoryTitle;
	
	public CategoryDetailPanel(Action action) {
		setCategory(category);
		setCategories(categories);
		
		setLayout(new GridBagLayout());
		initConstraints();
		int rij = 0;
		initTitle(rij);
		rij++;
		initDescription(rij);
		rij++;
		initMainCategory(rij);
		rij++;
		initButtons(rij, action);
	}

	protected void initTitle(int rij) {
		changeConstraints(1, 1, 0, rij);
		addToPanel(new JLabel("Title: "));

		changeConstraints(1, 1, 1, rij);
		titleField = new JTextField();
		addToPanel(titleField);
	}

	protected void initDescription(int rij) {
		changeConstraints(1, 1, 0, rij);
		addToPanel(new JLabel("Description: "));

		changeConstraints(1, 1, 1, rij);
		descriptionField = new JTextField();
		addToPanel(descriptionField);
	}

	protected void initMainCategory(int rij) {
		changeConstraints(1, 1, 0, rij);
		addToPanel(new JLabel("Main Category: "));

		changeConstraints(1, 1, 1, rij);
		categoryField = new JComboBox<Category>();
		addToPanel(categoryField);
	}

	protected void initButtons(int rij, Action action) {
		btnCancel = new JButton("Cancel");
		changeConstraints(1, 1, 0, rij);		
		btnCancel.setAction(action);
		btnCancel.setActionCommand("Cancel");
		btnCancel.setText("Cancel");
		addToPanel(btnCancel);

		btnOK = new JButton("Save");
		btnOK.isDefaultButton();		
		changeConstraints(1, 1, 1, rij);
		btnOK.setAction(action);
		btnOK.setActionCommand("Save");
		btnOK.setText("Save");
		addToPanel(btnOK);
	}

	private void initConstraints() {
		constraints.insets = new Insets(10, 10, 0, 10);
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 1.0;
		constraints.weighty = 1.0;
	}

	protected GridBagConstraints getConstraints() {
		return constraints;
	}
	
	protected void addToPanel(Component component) {
		add(component, getConstraints());
	}

	protected void changeConstraints(int height, int width, int gridx, int gridy) {
		constraints.gridheight = height;
		constraints.gridwidth = width;
		constraints.gridx = gridx;
		constraints.gridy = gridy;
	}

	private Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		if(category == null) return;
		this.category = category;
		setInitialCategoryTitle(category.getTitle());
		update();
	}
	
	private void setInitialCategoryTitle(String t){
		if(t == null) return;
		this.initialCategoryTitle = t;
	}
	
	public String getInitialCategoryTitle(){
		return this.initialCategoryTitle;
	}
	
	public Category getCreatedCategory(){
		getCategory().setTitle(titleField.getText());
		getCategory().setDescription(descriptionField.getText());		
		getCategory().setMainCategorie((Category)(categoryField.getSelectedItem()));
		return getCategory();
	}

	private List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
		update();
	}

	private void update() {
		if (getCategories() != null) {
			Vector<Category> cats = new Vector<Category>(getCategories());
			DefaultComboBoxModel<Category> categoriesModel = new DefaultComboBoxModel<Category>(cats);
			categoryField.setModel(categoriesModel);
		}

		if (getCategory() != null) {
			titleField.setText(getCategory().getTitle());
			descriptionField.setText(getCategory().getDescription());

			categoryField.setSelectedItem(getCategory().getMainCategorie());
		}
	}

}
