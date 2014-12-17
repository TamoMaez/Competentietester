/**
 * 
 * @author Ruben Thielemans, Tamo Maes, Georges Petrofski & Sam Hendrickx
 *
 */
package view.panels;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.Timer;

import controller.NextQuestionAction;
import controller.TestDoneAction;
import view.ViewException;
import domain.Question;
import domain.facade.CompetentieTesterFacade;


public class TestPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private GridBagConstraints constraints = new GridBagConstraints();
	private Question question;
	private NextQuestionAction nextQuestionAction;
	private TestDoneAction testDoneAction;
	private CompetentieTesterFacade service;
	private JButton nextQuestionButton;
	public int time = 0;
	public JLabel timeLabel;
	public Timer timer;

	
	public TestPanel(NextQuestionAction nextQuestionAction, TestDoneAction testDoneAction, CompetentieTesterFacade service) throws ViewException {
		this.nextQuestionAction=nextQuestionAction;
		this.testDoneAction=testDoneAction;
		this.service = service;
		setLayout(new GridBagLayout());
		this.timeLabel = new JLabel();
	}

	private void initTitle(int row) {
		changeConstraints(1, 1, 1, row);
		addToPanel(new JLabel("Vraag " + this.service.currentQuestionPosition + ":"));
		
		changeConstraints(1, 1, 2, row);
		addToPanel(timeLabel);
	}
	
	public void resetTime() {
		if (timer != null) {
			timer.removeActionListener(nextQuestionAction);
			timer.removeActionListener(testDoneAction);
		}
		this.time = service.getTimePerQuestion();
	}
	
	public void changeTimeLabel() {
		this.timeLabel.setText("<html><h2>Time left: " + String.valueOf(this.time) + "</h2></html>");
	}
	
	private void initQuestion(int row) {
		changeConstraints(1, 1, 1, row);
		addToPanel(new JLabel(this.getQuestion().getQuestion()));
	}
	
	
	private void initButtons(int row, Action action) {
		JButton btnNew = new JButton(action);
		this.nextQuestionButton = btnNew;
		btnNew.setEnabled(false);
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
		
		resetTime();
		
		// Show title
		changeTimeLabel();
		initTitle(row);
		row++;
		
		initQuestion(row);
		row++;
		
		
		//Show all options with radio buttons
		List<String> options = this.getQuestion().getStatementsShuffled();
		ButtonGroup optionGroup = new ButtonGroup();
		for(String option : options){
			initOption(row, option, optionGroup);
			row++;
		}
		
		
		if (!this.service.isLastQuestion()) {
			this.timer = new Timer(1000, nextQuestionAction);

			row ++;
			nextQuestionAction.setQuestion(question);
			nextQuestionAction.setOptions(optionGroup);
			initButtons(row, nextQuestionAction);
		} else {
			this.timer.removeActionListener(nextQuestionAction);
			this.timer = new Timer(1000, testDoneAction);

			row ++;
			testDoneAction.setQuestion(question);
			testDoneAction.setOptions(optionGroup);
			initButtons(row, testDoneAction);
		}
	
		timer.start();
		
	}
	
	private void initOption(int row, String option2, ButtonGroup optionGroup) {
		JRadioButton option = new JRadioButton(option2);
		option.addActionListener(this);
		optionGroup.add(option);
		changeConstraints(1, 1, 1, row);
		addToPanel(option);
	}

	private Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
		generatePanel();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.nextQuestionButton.setEnabled(true);
	}


}
