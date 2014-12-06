package controller;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JPanel;

import domain.facade.AdministratorFacade;

public abstract class AbstractTestAction extends AbstractAction {
	private static final long serialVersionUID = 1L;
	private AdministratorFacade service;
	private JFrame view;
	
	public AbstractTestAction(AdministratorFacade service){
		setService(service);
	}
	
	public AbstractTestAction(AdministratorFacade service, String caption){
		super(caption);
		setService(service);
	}

	protected AdministratorFacade getService() {
		return service;
	}

	private void setService(AdministratorFacade service) {
		this.service = service;
	}

	public JFrame getView() {
		return view;
	}

	public void setView(JFrame view) {
		this.view = view;
	}
	
	public void setPanelAsContentForView(JPanel panel){
		getView().setContentPane(panel);
	}
}
