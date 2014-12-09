package view.panels;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import controller.NextQuestionAction;
import controller.TestDoneAction;
import view.ViewException;
import domain.Option;
import domain.Question;
import domain.facade.CompetentieTesterFacade;


public class TestPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private GridBagConstraints constraints = new GridBagConstraints();
	private Question question;
	private NextQuestionAction nextQuestionAction;
	private TestDoneAction testDoneAction;
	private CompetentieTesterFacade service;
	
	public TestPanel(NextQuestionAction nextQuestionAction, TestDoneAction testDoneAction, CompetentieTesterFacade service) throws ViewException {
		this.nextQuestionAction=nextQuestionAction;
		this.testDoneAction=testDoneAction;
		this.service = service;
		setLayout(new GridBagLayout());
	}

	private void initTitle(int row) {
		changeConstraints(1, 1, 1, row);
		addToPanel(new JLabel("Vraag " + this.service.currentQuestionPosition + ":"));
	}
	
	private void initQuestion(int row) {
		changeConstraints(1, 1, 1, row);
		addToPanel(new JLabel(this.getQuestion().getQuestion()));
	}
	
	private void initOption(int row, Option o, ButtonGroup optionGroup) {
		JRadioButton option = new JRadioButton(o.getStatement());
		optionGroup.add(option);
		changeConstraints(1, 1, 1, row);
		addToPanel(option);
	}

	
	private void initButtons(int row, Action action) {
		JButton btnNew = new JButton(action);
		btnNew.setPreferredSize(new Dimension(40, 40));
		changeConstraints(1, 1, 2, row);
		addToPanel(btnNew);
	}

	public void update() throws ViewException {
		//
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

	public void generatePanel() {
		//clear panel
		this.removeAll();

		initConstraints();
		int row = 0;
		
		// Show title
		initTitle(row);
		row++;
		
		initQuestion(row);
		row++;
		
		
		//Show all options with radio buttons
		List<Option> options = this.getQuestion().getOptions();
		ButtonGroup optionGroup = new ButtonGroup();
		for(Option option : options){
			initOption(row, option, optionGroup);
			row++;
		}
		
		
		if (!this.service.isLastQuestion()) {
			row ++;
			nextQuestionAction.setQuestion(question);
			nextQuestionAction.setOptions(optionGroup);
			initButtons(row, nextQuestionAction);
		} else {
			row ++;
			testDoneAction.setQuestion(question);
			testDoneAction.setOptions(optionGroup);
			initButtons(row, testDoneAction);
		}
		
		
	}
	
	private Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
		generatePanel();
	}

}
