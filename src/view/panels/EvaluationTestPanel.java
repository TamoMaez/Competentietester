package view.panels;

import java.awt.Component;
import java.awt.GridBagConstraints;

import javax.swing.JLabel;
import javax.swing.JPanel;

import domain.facade.CompetentieTesterFacade;

public class EvaluationTestPanel extends JPanel {

	private CompetentieTesterFacade service;
	
	private static final long serialVersionUID = 1L;
	private GridBagConstraints constraints = new GridBagConstraints();
	
	public EvaluationTestPanel(CompetentieTesterFacade service) {
		this.service = service;
	}
	
	public double getScore() {
		return service.getTotalScore();
	}	
	
	public double getMaxScore() {
		return service.getTotalMaxScore();
	}
	
	private void initTitle() {
		add(new JLabel("De test is afgelopen."));
	}
	
	private void showScore() {
		add(new JLabel("Uw score is: " + getScore() + " / " + getMaxScore()));
	}
	
	private void addToPanel(Component component) {
		add(component, this);
	}
	
	public void update() {
		this.initTitle();
		this.showScore();
	}
	
}
