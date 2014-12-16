package view.panels.questions;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.IOException;
import java.util.List;

import javax.swing.Action;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import controller.question.AddOptionAction;
import controller.question.DeleteCategoryAction;
import controller.question.DeleteOptionAction;
import controller.question.QuestionStatementAction;
import view.panels.GeneralTable;
import view.panels.options.OptionTableModel;
import domain.Option;
import domain.Question;

public class QuestionDetailPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private Question question;
	private JTextField titleField; 
	private GridBagConstraints constraints = new GridBagConstraints();
	private String initialQuestionTitle;
	private GeneralTable optionTable, categoryTable;


	public QuestionDetailPanel(Action addCategoryAction, Action questionDoneAction) {
		setLayout(new GridBagLayout());
		initConstraints();

		int rij = 0;
		initTitle(rij);
		rij++;
		initTileButtons(rij);
		rij++;
		initCategories(rij);
		rij++;
		initCategoriesButtons(rij, addCategoryAction);
		rij++;
		initAnswers(rij);
		rij++;
		initAnswerButtons(rij);
		rij++;
		initButtons(rij, questionDoneAction);
	}

	private void initTileButtons(int rij) {
		changeConstraints(1, 1, 1, rij);
		JButton titleBtn = new JButton("Save title");
		titleBtn.setActionCommand("Save title");
		titleBtn.setText("Save title");
		titleBtn.setAlignmentX(RIGHT_ALIGNMENT);
		titleBtn.setAction(new QuestionStatementAction(this, titleBtn.getActionCommand()));
		
		JButton resetBtn = new JButton("Reset title");
		resetBtn.setActionCommand("Reset title");
		resetBtn.setText("Reset title");
		resetBtn.setAlignmentX(RIGHT_ALIGNMENT);
		resetBtn.setAction(new QuestionStatementAction(this, resetBtn.getActionCommand()));
		
		JPanel selectButtonsPanel = new JPanel();
		selectButtonsPanel.setLayout(new BoxLayout(selectButtonsPanel, BoxLayout.LINE_AXIS));
		selectButtonsPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		selectButtonsPanel.add(Box.createHorizontalGlue());
		selectButtonsPanel.add(titleBtn);
		selectButtonsPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		selectButtonsPanel.add(resetBtn);

		addToPanel(selectButtonsPanel);
	}

	private void initCategoriesButtons(int rij, Action addCategoryAction) {
		changeConstraints(1, 1, 1, rij);
		JButton addBtn = new JButton("Add category");
		addBtn.setAlignmentX(RIGHT_ALIGNMENT);
		addBtn.setAction(addCategoryAction);
		JPanel selectButtonsPanel = new JPanel();
		selectButtonsPanel.setLayout(new BoxLayout(selectButtonsPanel, BoxLayout.Y_AXIS));
		selectButtonsPanel.add(Box.createHorizontalGlue());
		selectButtonsPanel.add(addBtn);
		selectButtonsPanel.add(Box.createRigidArea(new Dimension(10, 0)));

		addToPanel(selectButtonsPanel);
	}

	private void initCategories(int rij) {
		changeConstraints(1, 1, 0, rij);
		addToPanel(new JLabel("Categories: "));

		categoryTable = new GeneralTable(true);
		categoryTable.addMouseListener(new DeleteCategoryAction(this));
		changeConstraints(1, 2, 1, rij);
		addToPanel(new JScrollPane(categoryTable));
	}

	protected void initAnswers(int rij) {
		changeConstraints(1, 1, 0, rij);
		addToPanel(new JLabel("Answers: "));

		optionTable = new GeneralTable(true);
		optionTable.addMouseListener(new DeleteOptionAction(this));
		changeConstraints(1, 2, 1, rij);
		addToPanel(new JScrollPane(optionTable));
	}

	private void initAnswerButtons(int rij) {
		changeConstraints(1, 1, 1, rij);
		JButton addBtn = new JButton("Add Option");
		addBtn.setAction(new AddOptionAction(this));
		addBtn.setAlignmentX(RIGHT_ALIGNMENT);
		
		JPanel selectButtonsPanel = new JPanel();
		selectButtonsPanel.setLayout(new BoxLayout(selectButtonsPanel, BoxLayout.Y_AXIS));
		selectButtonsPanel.add(Box.createHorizontalGlue());
		selectButtonsPanel.add(addBtn);
		selectButtonsPanel.add(Box.createRigidArea(new Dimension(10, 0)));

		addToPanel(selectButtonsPanel);
	}

	protected void initTitle(int rij) {
		changeConstraints(1, 1, 0, rij);
		addToPanel(new JLabel("Question: "));

		changeConstraints(1, 2, 1, rij);
		titleField = new JTextField();
		addToPanel(titleField);
	}

	protected void initButtons(int rij, Action action) {
		JButton btnCancel = new JButton("Back");
		changeConstraints(1, 1, 0, rij);		
		btnCancel.setAction(action);
		btnCancel.setActionCommand("Back");
		btnCancel.setText("Back");
		
		JButton btnOK = new JButton("Save");
		btnOK.isDefaultButton();		
		btnOK.setAction(action);
		btnOK.setActionCommand("Save");
		btnOK.setText("Save");

		JPanel btnPanel = new JPanel();
		btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.X_AXIS));
		btnPanel.add(btnCancel);
		btnPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		btnPanel.add(btnOK);
		
		addToPanel(btnPanel);
	}

	private void initConstraints() {
		constraints.insets = new Insets(10, 10, 0, 10);
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 1.0;
		constraints.weighty = 1.0;
		constraints.anchor = GridBagConstraints.CENTER;
	}

	protected void changeConstraints(int height, int width, int gridx, int gridy) {
		constraints.gridheight = height;
		constraints.gridwidth = width;
		constraints.gridx = gridx;
		constraints.gridy = gridy;
	}

	protected GridBagConstraints getConstraints() {
		return constraints;
	}
	
	public void setQuestion(Question question) {
		if(question == null) return;
		this.question = question;
		setInitialQuestionTitle(getQuestion().getQuestion());
		update();
	}

	private void setInitialQuestionTitle(String t){
		if(t == null) return;
		this.initialQuestionTitle = t;
	}

	public String getInitialQuestionTitle(){
		return this.initialQuestionTitle;
	}

	public void setAnswers(List<Option> answers) {
		update();
	}

	public void update() {
		if (getQuestion() != null) {
			titleField.setText(getQuestion().getQuestion());
			if(question.getOptions() != null)
				try {
					optionTable.setModel(new OptionTableModel(question.getOptions()));
					optionTable.setColumnWidths(-1, 75, 50);
				} catch (IOException e) {
					e.printStackTrace();
				}
			if(question.getCategories() != null)
				try {
					categoryTable.setModel(new QuestionCategoryTableModel(question));
					categoryTable.setColumnWidths(-1, 75, 50);
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
	
	public GeneralTable getOptionTable() {
		return optionTable;
	}

	public void setOptionTable(GeneralTable optionTable) {
		this.optionTable = optionTable;
	}

	public GeneralTable getCategoryTable() {
		return categoryTable;
	}

	public void setCategoryTable(GeneralTable categoryTable) {
		this.categoryTable = categoryTable;
	}

	public String setQuestionTitle(){
		getQuestion().setQuestion(titleField.getText());
		return titleField.getText();
	}

	public Question getQuestion() {
		return question;
	}

	protected void addToPanel(Component component) {
		add(component, getConstraints());
	}

	public void setCorrectAnswers(List<Option> correctAnswers) {
	}
	
	public JTextField getTitleField(){
		return this.titleField;
	}

}
