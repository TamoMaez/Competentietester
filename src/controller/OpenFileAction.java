package controller;

import java.awt.event.ActionEvent;

import domain.facade.AdministratorFacade;

public class OpenFileAction extends AbstractTestAction {

	public OpenFileAction(AdministratorFacade service) {
		super(service, "Open");
	}

	private static final long serialVersionUID = 1L;

	@Override
	public void actionPerformed(ActionEvent arg0) {
		this.getService().read();
	}



}
