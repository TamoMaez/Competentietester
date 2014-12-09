package controller;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;


import controller.AbstractTestAction;
import view.ViewException;
import view.panels.TestPanel;
import domain.Question;
import domain.facade.CompetentieTesterFacade;
import domain.Option;

public class NextQuestionAction extends AbstractTestAction {
	private static final long serialVersionUID = 1L;
	private TestPanel testPanel;

	private ButtonGroup optionGroup;
	private Question question;
		
	public NextQuestionAction(CompetentieTesterFacade service){
		super(service, "Volgende vraag");
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
		
		
		// Listener to get all categories from service...
		Question question = getService().getNextQuestion();
		getTestPanel().setQuestion(question);
		
		try {
			getTestPanel().update();
		} catch (ViewException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		setPanelAsContentForView(getTestPanel());
	}

	private TestPanel getTestPanel() {
		return testPanel;
	}

	public void setTestPanel(TestPanel testPanel) {
		this.testPanel = testPanel;
	}
	
	public void setOptions(ButtonGroup optionGroup) {
		this.optionGroup = optionGroup;
	}
	
	public void setQuestion(Question question) {
		this.question = question;
	}
}
