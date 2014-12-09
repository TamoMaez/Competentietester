package controller;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;

import controller.AbstractTestAction;
import view.panels.EvaluationTestPanel;
import domain.Option;
import domain.Question;
import domain.facade.CompetentieTesterFacade;

public class TestDoneAction extends AbstractTestAction {
	private static final long serialVersionUID = 1L;
	private EvaluationTestPanel evaluationTestPanel;

	private ButtonGroup optionGroup;
	private Question question;

	public TestDoneAction(CompetentieTesterFacade service){
		super(service, "Complete Test");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// User has answered question
		List<Option> options = new ArrayList<>();
		
		for (Enumeration<AbstractButton> buttons = optionGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                options.add(question.getOption(button.getText()));
            }
	    }
		
		this.getService().answerQuestion(options, question);

	
		setPanelAsContentForView(this.getEvaluationTestPanel());
		this.getEvaluationTestPanel().update();
	}

	private EvaluationTestPanel getEvaluationTestPanel() {
		return evaluationTestPanel;
	}

	public void setEvaluationTestPanel(EvaluationTestPanel evaluationTestPanel) {
		this.evaluationTestPanel = evaluationTestPanel;
	}
	
	public void setOptions(ButtonGroup optionGroup) {
		this.optionGroup = optionGroup;
	}
	
	public void setQuestion(Question question) {
		this.question = question;
	}
}
