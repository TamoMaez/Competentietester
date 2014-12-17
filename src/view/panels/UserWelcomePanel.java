/**
 * 
 * @author Ruben Thielemans, Tamo Maes, Georges Petrofski & Sam Hendrickx
 *
 */
package view.panels;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.HistoryOverviewAction;
import controller.StartTestAction;

public class UserWelcomePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private GridBagConstraints constraints = new GridBagConstraints();
	
	public UserWelcomePanel(StartTestAction startTestAction, HistoryOverviewAction historyOverviewAction) {
		setLayout(new GridBagLayout());
		initConstraints();
		initButtons(startTestAction, historyOverviewAction);
		initTitle();
	}
	
	private void initConstraints() {
		constraints.insets = new Insets(10, 10, 0, 10);
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 1.0;
		constraints.weighty = 1.0;
	}
	
	protected GridBagConstraints getConstraints() {
		return constraints;
	}
	
	protected void addToPanel(Component component) {
		add(component, getConstraints());
	}

	protected void changeConstraints(int height, int width, int gridx, int gridy) {
		constraints.gridheight = height;
		constraints.gridwidth = width;
		constraints.gridx = gridx;
		constraints.gridy = gridy;
	}

	private void initButtons(StartTestAction startTestAction, HistoryOverviewAction historyOverviewAction) {
		JButton startTestButton = new JButton(startTestAction);
		JButton historyOverviewButton = new JButton(historyOverviewAction);
		
		changeConstraints(1, 1, 1, 2);
		this.addToPanel(startTestButton);
		changeConstraints(1, 1, 2, 2);
		this.addToPanel(historyOverviewButton);
		
	}
	
	
	private void initTitle() {
		changeConstraints(1, 1, 1, 1);

		JLabel title = new JLabel("<html><h1>Welcome!</h1></html>");
		this.addToPanel(title);
	}
	
}
