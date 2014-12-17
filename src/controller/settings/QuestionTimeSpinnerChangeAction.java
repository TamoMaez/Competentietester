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

public class QuestionTimeSpinnerChangeAction implements ChangeListener {

	private CompetentieTesterFacade service;

	public QuestionTimeSpinnerChangeAction(CompetentieTesterFacade service) {
		this.service = service;
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		SpinnerModel spinnerTijdPerVraag = (SpinnerModel) e.getSource();
		int aantal = (int) spinnerTijdPerVraag.getValue();
		try {
			service.setTimePerQuestion(aantal);
		} catch(Exception ex) {
			spinnerTijdPerVraag.setValue(aantal-1);
		}
	}

}
