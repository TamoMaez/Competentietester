/**
 * 
 * @author Ruben Thielemans, Tamo Maes, Georges Petrofski & Sam Hendrickx
 *
 */
package controller.settings;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JComboBox;

import domain.facade.CompetentieTesterFacade;
import domain.questionSelectorStrategy.QuestionSelector;

public class QuestionSelectorAction extends AbstractAction{
	private static final long serialVersionUID = 5273740187276478111L;
	private CompetentieTesterFacade service;

	public QuestionSelectorAction(CompetentieTesterFacade service) {
		super();
		this.service = service;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		@SuppressWarnings("unchecked")
		JComboBox<QuestionSelector> questionSelectorField = (JComboBox<QuestionSelector>) e.getSource();
		QuestionSelector questionSelector = (QuestionSelector) questionSelectorField.getSelectedItem();
		service.setQuestionSelector(questionSelector);
	}

}
