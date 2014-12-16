package controller;

import java.awt.event.ActionEvent;

import org.apache.commons.io.FileExistsException;

import domain.facade.CompetentieTesterFacade;

public class SaveFileAction extends AbstractTestAction {

	private static final long serialVersionUID = 1L;

	public SaveFileAction(CompetentieTesterFacade service) {
		super(service, "Save");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			this.getService().writeToCurrentFile();
		} catch (FileExistsException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
