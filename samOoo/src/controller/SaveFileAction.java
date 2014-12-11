package controller;

import java.awt.event.ActionEvent;

import domain.facade.CompetentieTesterFacade;

public class SaveFileAction extends AbstractTestAction {

	private static final long serialVersionUID = 1L;

	public SaveFileAction(CompetentieTesterFacade service) {
		super(service, "Save");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.getService().writeToCurrentFile();
	}

}
