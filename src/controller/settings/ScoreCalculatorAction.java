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
import domain.scoreCalculatorStrategy.ScoreCalculator;

public class ScoreCalculatorAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	private CompetentieTesterFacade service;

	public ScoreCalculatorAction(CompetentieTesterFacade service) {
		this.service = service;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		@SuppressWarnings("unchecked")
		JComboBox<ScoreCalculator> scoreCalculatorField = (JComboBox<ScoreCalculator>) e.getSource();
		ScoreCalculator scoreCalculator = (ScoreCalculator) scoreCalculatorField.getSelectedItem();
		service.setScoreCalculator(scoreCalculator);
	}

}
