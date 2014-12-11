package controller;

import java.awt.event.ActionEvent;

import domain.facade.CompetentieTesterFacade;

public class SaveAsFileAction extends AbstractTestAction {

	private static final long serialVersionUID = 1L;

	public SaveAsFileAction(CompetentieTesterFacade service) {
		super(service, "Save as");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.getService().write();
	}

}
