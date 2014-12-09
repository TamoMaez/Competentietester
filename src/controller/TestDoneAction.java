package controller;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;

import controller.AbstractTestAction;
import view.panels.EvaluationTestPanel;
import view.panels.TestPanel;
import domain.Option;
import domain.Question;
import domain.facade.CompetentieTesterFacade;

public class TestDoneAction extends AbstractTestAction {
	private static final long serialVersionUID = 1L;
	private EvaluationTestPanel evaluationTestPanel;

	private ButtonGroup optionGroup;
	private Question question;
	private TestPanel testPanel;
	private boolean testDone = false;
	
	public TestDoneAction(CompetentieTesterFacade service){
		super(service, "Complete Test");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		boolean nextQuestion = false;
		
		if (e.getActionCommand() == null) {
			// Timer has passed 1 second
			 testPanel.time--;
			 testPanel.changeTimeLabel();
	       // timerLabel.setText(elapsedSeconds)
	        if(testPanel.time == 0){
	        	testPanel.timer.stop();
	        	testPanel.timer.removeActionListener(this);
	        	nextQuestion = true;
	        }
		} else {
			nextQuestion = true;
        	testPanel.timer.removeActionListener(this);
		}
		
		if (nextQuestion == true) {
			testDone = true;
		  // User has answered question
			List<Option> options = new ArrayList<>();
			
			for (Enumeration<AbstractButton> buttons = optionGroup.getElements(); buttons.hasMoreElements();) {
		        AbstractButton button = buttons.nextElement();
		
		        if (button.isSelected()) {
		            options.add(question.getOption(button.getText()));
		        }
		    }			
			
			if (options.isEmpty()) {
				options.add(question.getWrongOption());
			}
			
			this.getService().answerQuestion(options, question);
		
			this.getService().stop();
		
			this.getEvaluationTestPanel().setEvaluation(this.getService().getLastEvaluation());
			this.getEvaluationTestPanel().generatePanel();
			setPanelAsContentForView(this.getEvaluationTestPanel());

       }
	}

	private EvaluationTestPanel getEvaluationTestPanel() {
		return evaluationTestPanel;
	}

	public void setEvaluationTestPanel(EvaluationTestPanel evaluationTestPanel) {
		this.evaluationTestPanel = evaluationTestPanel;
	}
	
	public void setTestPanel (TestPanel testPanel) {
		this.testPanel = testPanel;
	}
	
	public void setOptions(ButtonGroup optionGroup) {
		this.optionGroup = optionGroup;
	}
	
	public void setQuestion(Question question) {
		this.question = question;
	}
}
