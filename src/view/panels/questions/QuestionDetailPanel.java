package view.panels.questions;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.Action;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import view.panels.options.OptionTableModel;
import domain.Option;
import domain.Question;

public class QuestionDetailPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private Question question;
	private JTextField titleField; 
	private GridBagConstraints constraints = new GridBagConstraints();
	private String initialQuestionTitle;
	private List<Option> answers;
	private JTable optionTable, categoryTable;


	public QuestionDetailPanel(Action addCategoryAction) {
		setLayout(new GridBagLayout());
		initConstraints();

		int rij = 0;
		initTitle(rij);
		rij++;
		initCategories(rij);
		rij++;
		initCategoriesButtons(rij, addCategoryAction);
		rij++;
		initAnswers(rij);
		rij++;
		initAnswerButtons(rij);
		rij++;
		initButtons(rij, null);
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

		categoryTable = new JTable();
		//table.addMouseListener(action);
		changeConstraints(1, 1, 1, rij);
		addToPanel(new JScrollPane(categoryTable));
	}

	protected void initAnswers(int rij) {
		changeConstraints(1, 1, 0, rij);
		addToPanel(new JLabel("Answers: "));

		optionTable = new JTable();
		//table.addMouseListener(action);
		changeConstraints(1, 1, 1, rij);
		addToPanel(new JScrollPane(optionTable));
	}

	private void initAnswerButtons(int rij) {
		changeConstraints(1, 1, 1, rij);
		JButton addBtn = new JButton("Add option");
		addBtn.setAlignmentX(RIGHT_ALIGNMENT);
		//changeBtn.setAction();
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

		changeConstraints(1, 1, 1, rij);
		titleField = new JTextField();
		addToPanel(titleField);
	}

	protected void initButtons(int rij, Action action) {
		JButton btnCancel = new JButton("Cancel");
		changeConstraints(1, 1, 0, rij);		
		btnCancel.setAction(action);
		btnCancel.setActionCommand("Cancel");
		btnCancel.setText("Cancel");
		addToPanel(btnCancel);

		JButton btnOK = new JButton("Save");
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
		this.answers = answers;
		update();
	}

	private void update() {
		if (getQuestion() != null) {
			titleField.setText(getQuestion().getQuestion());

			optionTable.setModel(new OptionTableModel(question.getOptions()));
			categoryTable.setModel(new QuestionCategoryTableModel(question));
		}
	}

	private List<Option> getAnswers() {
		return answers;
	}

	private Question getQuestion() {
		return question;
	}

	protected void addToPanel(Component component) {
		add(component, getConstraints());
	}

	public void setCorrectAnswers(List<Option> correctAnswers) {
	}

}
