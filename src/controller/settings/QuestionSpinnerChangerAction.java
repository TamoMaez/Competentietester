/**
 * 
 * @author Ruben Thielemans, Tamo Maes, Georges Petrofski & Sam Hendrickx
 *
 */
package controller.settings;

import javax.swing.SpinnerModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import domain.facade.CompetentieTesterFacade;

public class QuestionSpinnerChangerAction implements ChangeListener{

	private CompetentieTesterFacade service;

	public QuestionSpinnerChangerAction(CompetentieTesterFacade service) {
		this.service = service;
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		SpinnerModel spinnerAantalVragen = (SpinnerModel) e.getSource();
		int aantal = (int) spinnerAantalVragen.getValue();
		try {
			service.setNumberOfQuestions(aantal);
		} catch(Exception ex) {
			spinnerAantalVragen.setValue(aantal-1);
		}
	}

}
