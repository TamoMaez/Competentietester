package controller;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JPanel;

import domain.facade.CompetentieTesterFacade;

public abstract class AbstractTestAction extends AbstractAction {
	private static final long serialVersionUID = 1L;
	private CompetentieTesterFacade service;
	private JFrame view;
	
	public AbstractTestAction(CompetentieTesterFacade service){
		setService(service);
	}
	
	public AbstractTestAction(CompetentieTesterFacade service, String caption){
		super(caption);
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
