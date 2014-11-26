package controller;

import java.awt.event.MouseAdapter;

import javax.swing.JFrame;
import javax.swing.JPanel;

import domain.facade.AdministratorFacade;

public abstract class AbstractTestMouseAdapter extends MouseAdapter {
	private AdministratorFacade service;
	private JFrame view;
	
	public AbstractTestMouseAdapter(AdministratorFacade service){
		super();
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
		getView().getContentPane().revalidate();
		getView().getContentPane().repaint();
		getView().pack();
	}
}
