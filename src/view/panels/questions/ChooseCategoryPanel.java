package view.panels.questions;

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
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import domain.Category;
import domain.Question;

public class ChooseCategoryPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private GridBagConstraints constraints = new GridBagConstraints();
	private List<Category> categories;
	private JComboBox<Category> categoryField;
	private JButton btnAdd, btnCancel;
	private JSpinner numberChooser;
	private Question question;
	
	public ChooseCategoryPanel(Action action) {
		setCategories(categories);
		
		setLayout(new GridBagLayout());
		initConstraints();
		int rij = 0;
		initCategories(rij);
		rij++;
		initPoint(rij);
		rij++;
		initButtons(rij, action);
	}
	
	private void initPoint(int rij) {
		changeConstraints(1, 1, 0, rij);
		addToPanel(new JLabel("Max Points: "));
		
		changeConstraints(1, 1, 1, rij);
		
		SpinnerNumberModel numberModel = new SpinnerNumberModel(
                new Integer(0), // value
                new Integer(0), // min
                new Integer(10), // max
                new Integer(1) // step
                );
        numberChooser = new JSpinner(numberModel);
        ((JSpinner.DefaultEditor) numberChooser.getEditor()).getTextField().setEditable(false);
        addToPanel(numberChooser);
	}

	private void initButtons(int rij, Action action) {
		btnCancel = new JButton("Cancel");
		changeConstraints(1, 1, 0, rij);		
		btnCancel.setAction(action);
		btnCancel.setActionCommand("Cancel");
		btnCancel.setText("Cancel");
		addToPanel(btnCancel);

		btnAdd = new JButton("Add");
		btnAdd.isDefaultButton();		
		changeConstraints(1, 1, 1, rij);
		btnAdd.setAction(action);
		btnAdd.setActionCommand("Add");
		btnAdd.setText("Add");
		addToPanel(btnAdd);
	}

	protected void initCategories(int rij) {
		changeConstraints(1, 1, 0, rij);
		addToPanel(new JLabel("Choose Category: "));

		changeConstraints(1, 1, 1, rij);
		categoryField = new JComboBox<Category>();
		addToPanel(categoryField);
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
	
	protected void changeConstraints(int height, int width, int gridx, int gridy) {
		constraints.gridheight = height;
		constraints.gridwidth = width;
		constraints.gridx = gridx;
		constraints.gridy = gridy;
	}
	
	
	protected void addToPanel(Component component) {
		add(component, getConstraints());
	}
	
	public List<Category> getCategories(){
		return categories;
	}
	
	public void setCategories(List<Category> categories) {
		this.categories = categories;
		update();
	}
	
	private void update() {	
		if (getCategories() != null && getCategories().size() >= 0) {
			Vector<Category> cats = new Vector<Category>(getCategories());
			DefaultComboBoxModel<Category> categoriesModel = new DefaultComboBoxModel<Category>(cats);
			categoryField.setModel(categoriesModel);
		}
	}
	
	public Question getQuestion(){
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public int getMaxPoint() {
		System.out.println(numberChooser.getValue());
		return (int) numberChooser.getValue();
	}
	
	public Category getCategory(){		
		return (Category)(categoryField.getSelectedItem());
	}
}
