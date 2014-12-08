package controller;

import java.awt.event.MouseAdapter;

import javax.swing.JFrame;
import javax.swing.JPanel;

import domain.facade.CompetentieTesterFacade;

public abstract class AbstractTestMouseAdapter extends MouseAdapter {
	private CompetentieTesterFacade service;
	private JFrame view;
	
	public AbstractTestMouseAdapter(CompetentieTesterFacade service){
		super();
		setService(service);
	}

	protected CompetentieTesterFacade getService() {
		return service;
	}

	private void setService(CompetentieTesterFacade service) {
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
